package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class IndoorGamesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor_games);

        // Button for Chess
        Button btnChess = findViewById(R.id.btnChess);
        btnChess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ChessActivity
                Intent intent = new Intent(IndoorGamesActivity.this, ChessActivity.class);
                startActivity(intent);
            }
        });

        Button ticTacToeButton = findViewById(R.id.buttonTicTacToe);

        // Set an OnClickListener to handle the button click
        ticTacToeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Tic Tac Toe Activity
                Intent intent = new Intent(IndoorGamesActivity.this, TicTacToeActivity.class);
                startActivity(intent);
            }
        });
// Button to go to Rock-Paper-Scissors Game
        Button rockPaperScissorsButton = findViewById(R.id.buttonRockPaperScissors);
        rockPaperScissorsButton.setOnClickListener(v -> {
            // Start the Rock-Paper-Scissors Game Activity
            Intent intent = new Intent(IndoorGamesActivity.this, RockPaperScissorsActivity.class);
            startActivity(intent);
        });
        Button aliGuliManeButton = findViewById(R.id.buttonAliGuliMane);

// Set OnClickListener to handle navigation
        aliGuliManeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndoorGamesActivity.this, AliGuliManeActivity.class);
                startActivity(intent);
            }
        });


    }
}
