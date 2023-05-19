package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    private GridLayout grid;
    private TextView counterLabel;
    private boolean[][] cellState;

    View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int buttonIndex = grid.indexOfChild(v);
            int row = buttonIndex / GRID_SIZE;
            int col = buttonIndex % GRID_SIZE;

            cellState[row][col] = !cellState[row][col];
            if(cellState[row][col] == true){
                v.setBackgroundColor(getColor(R.color.blue_500));
            }else{
                v.setBackgroundColor(getColor(R.color.black));
            }
            updateCounterLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.lightGrid);
        counterLabel = findViewById(R.id.countLabel);

        for(int i =0; i < grid.getChildCount(); i++){
            grid.getChildAt(i).setOnClickListener(buttonClick);
        }
        randomize();
        recolor();
    }

    /**
     * Method that sets the color or all the buttons based on their corresponding value in the cellState matrix
     */
    public void recolor(){
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col] == true) {
                gridButton.setBackgroundColor(getColor(R.color.blue_500));
            } else {
                gridButton.setBackgroundColor(getColor(R.color.black));
            }
        }
    }

    /**
     * Method to randomly set the values of each item
     * in the cellState matrix indicating the color for each button
     */
    public void randomize(){
        Random random = new Random();
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                cellState[i][j] = random.nextBoolean();
            }
        }
        updateCounterLabel();
    }

    public void reset(View v){
//        Log.d("A", grid.toString());
//        if(grid != null) {
            randomize();
            recolor();
//        }else{
//            Log.d("A", "null grid");
//        }
    }

    public void updateCounterLabel(){
        String text = String.format(getString(R.string.score), counter());
        counterLabel.setText(text);
    }
    public int counter(){
        int count = 0;
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                if(cellState[i][j] == true){
                    count++;
                }
            }
        }
        return count;
    }


}