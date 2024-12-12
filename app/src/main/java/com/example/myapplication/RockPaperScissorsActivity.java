package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RockPaperScissorsActivity extends AppCompatActivity {

    private TextView resultText;
    private TextView scoreText;
    private int playerScore = 0;
    private int computerScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scissors);

        resultText = findViewById(R.id.resultText);
        scoreText = findViewById(R.id.scoreText);

        Button rockButton = findViewById(R.id.rockButton);
        Button paperButton = findViewById(R.id.paperButton);
        Button scissorsButton = findViewById(R.id.scissorsButton);

        rockButton.setOnClickListener(this::playGame);
        paperButton.setOnClickListener(this::playGame);
        scissorsButton.setOnClickListener(this::playGame);
    }

    private void playGame(View v) {
        String playerChoice = ((Button) v).getText().toString();
        String computerChoice = getComputerChoice();
        String result = determineWinner(playerChoice, computerChoice);

        if (result.equals("Player Wins!")) {
            playerScore++;
        } else if (result.equals("Computer Wins!")) {
            computerScore++;
        }

        resultText.setText("Computer chose: " + computerChoice + "\n" + result);
        scoreText.setText("Player: " + playerScore + " | Computer: " + computerScore);
    }

    private String getComputerChoice() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        return choices[random.nextInt(choices.length)];
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a Draw!";
        }
        if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            return "Player Wins!";
        } else {
            return "Computer Wins!";
        }
    }
}
