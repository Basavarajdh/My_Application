package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class LudoActivity extends AppCompatActivity {

    private int[][] board = new int[5][5]; // Game board
    private int playerPosition = 0; // Initial position of the player
    private int diceRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ludo);

        GridLayout boardGrid = findViewById(R.id.boardGrid);
        Button rollDiceButton = findViewById(R.id.rollDiceButton);
        TextView diceResult = findViewById(R.id.diceResult);

        // Initialize the board (example setup)
        initializeBoard();

        // Dice roll button
        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceRoll = rollDice();
                diceResult.setText("Dice Result: " + diceRoll);

                // Update player position
                updatePlayerPosition();
                updateBoardUI(boardGrid);
            }
        });
    }

    private void initializeBoard() {
        // Fill the board with default values
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = 0; // 0 indicates an empty cell
            }
        }
        board[0][0] = 1; // Starting position
    }

    private int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1; // Dice rolls between 1 and 6
    }

    private void updatePlayerPosition() {
        // Calculate new position (simplified as linear for demonstration)
        playerPosition += diceRoll;

        // Check if player reached the end
        if (playerPosition >= 24) {
            Toast.makeText(this, "Player Wins!", Toast.LENGTH_SHORT).show();
            playerPosition = 0; // Reset for a new game
        }
    }

    private void updateBoardUI(GridLayout boardGrid) {
        // Clear previous positions
        for (int i = 0; i < boardGrid.getChildCount(); i++) {
            TextView cell = (TextView) boardGrid.getChildAt(i);
            cell.setBackgroundResource(R.drawable.cell_background);
        }

        // Update player position
        int row = playerPosition / 5;
        int col = playerPosition % 5;
        int cellIndex = row * 5 + col;

        TextView currentCell = (TextView) boardGrid.getChildAt(cellIndex);
        currentCell.setBackgroundResource(R.drawable.player_background);
    }
}
