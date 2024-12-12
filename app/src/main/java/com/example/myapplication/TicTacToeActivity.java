package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeActivity extends AppCompatActivity {

    private Button[][] buttons = new Button[3][3];
    private boolean playerXTurn = true;
    private int roundCount = 0;
    private int playerXScore = 0;
    private int playerOScore = 0;

    private TextView textTurn;
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        textTurn = findViewById(R.id.textTurn);
        scoreText = findViewById(R.id.scoreText);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        Button resetButton = findViewById(R.id.resetButton);

        // Initialize buttons and set click listeners
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this::onButtonClick);
            }
        }

        resetButton.setOnClickListener(v -> resetGame());
    }

    private void onButtonClick(View v) {
        Button clickedButton = (Button) v;

        if (!clickedButton.getText().toString().equals("")) {
            return; // If button is already filled, do nothing
        }

        clickedButton.setText(playerXTurn ? "X" : "O");

        roundCount++;

        if (checkForWin()) {
            if (playerXTurn) {
                playerXScore++;
                updateScore();
                showWinner("Player X Wins!");
            } else {
                playerOScore++;
                updateScore();
                showWinner("Player O Wins!");
            }
        } else if (roundCount == 9) {
            showWinner("Draw!");
        } else {
            playerXTurn = !playerXTurn;
            textTurn.setText(playerXTurn ? "Player X's Turn" : "Player O's Turn");
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        // Check diagonals
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void showWinner(String message) {
        textTurn.setText(message);
        disableAllButtons();
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void updateScore() {
        scoreText.setText("Score - X: " + playerXScore + " | O: " + playerOScore);
    }

    private void resetGame() {
        roundCount = 0;
        playerXTurn = true;
        textTurn.setText("Player X's Turn");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }
}

