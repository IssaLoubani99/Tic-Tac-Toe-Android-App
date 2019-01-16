package com.example.xp.tests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    // 0 = yellow, 1 = red
    int activePlayer = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winingPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameIsActive = true;

    public void onClick(View view) {
        ImageView button = (ImageView) view;


        int buttonIndex = Integer.parseInt(button.getTag().toString());

        if (gameState[buttonIndex] == 2 && gameIsActive) {

            if (activePlayer == 0) {
                button.setImageResource(R.drawable.yellow);
                activePlayer = 1;
                gameState[buttonIndex] = 0;
            } else {
                button.setImageResource(R.drawable.red);
                activePlayer = 0;
                gameState[buttonIndex] = 1;
            }


            // animation
            button.setScaleY(0.5f);
            button.setScaleX(0.5f);
            button.animate().alpha(1f).rotationBy(360).scaleY(1f).scaleX(1f).setDuration(500);

            for (int[] winningPosition : winingPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[1]] != 2) {


                    // Someone Win !
                    String winner = "";
                    if (gameState[winningPosition[1]] == 0) {

                        winner = "Yellow";
                    } else {

                        winner = "Red";
                    }
                    gameIsActive = false;
                    TextView winText = (TextView) findViewById(R.id.winText);
                    winText.setText(winner + " has won !");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.winLayout);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;

                    for (int state : gameState) {
                        if (state == 2) gameIsOver = false;


                    }

                    if (gameIsOver) {
                        TextView winText = (TextView) findViewById(R.id.winText);
                        winText.setText("It's a Draw !");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.winLayout);
                        layout.setVisibility(View.VISIBLE);
                    }

                }
            }


        }
    }

    public void playAgain(View view) {


        LinearLayout layout = (LinearLayout) findViewById(R.id.winLayout);
        layout.setVisibility(View.INVISIBLE);
        GridLayout mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        activePlayer = 0;
        int index = 0;
        for (int state : gameState) {

            gameState[index] = 2;
            ((ImageView) mainGrid.getChildAt(index)).setImageResource(0);
            index++;
        }
        gameIsActive = true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
