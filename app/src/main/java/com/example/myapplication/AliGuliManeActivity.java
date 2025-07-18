package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AliGuliManeActivity extends AppCompatActivity {

    private Button[] pits = new Button[14];
    private int[] stones = new int[14];
    private TextView statusText;
    private boolean playerOneTurn = true;
    // Changed from GridLayout to LinearLayout
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_guli_mane);

        statusText = findViewById(R.id.statusText);
        // Changed to find the root LinearLayout instead of GridLayout
        mainLayout = findViewById(R.id.mainLayout);

        for (int i = 0; i < 14; i++) {
            String buttonID = "pit" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            pits[i] = findViewById(resID);
            pits[i].setOnClickListener(this::onPitClick);
            stones[i] = (i == 6 || i == 13) ? 0 : 4;
            updatePitText(i);
        }

        statusText.setText("Player 1's Turn");

        Button instructionButton = findViewById(R.id.instructionButton);
        instructionButton.setOnClickListener(v -> {
            Intent intent = new Intent(AliGuliManeActivity.this, InstructionsActivity.class);
            startActivity(intent);
        });
    }

    private void onPitClick(View v) {
        Button clickedPit = (Button) v;
        int index = getPitIndex(clickedPit);

        if (index == 6 || index == 13 || stones[index] == 0 || !isValidPit(index)) {
            return;
        }

        int finalIndex = distributeStones(index);

        if (isGameOver()) {
            displayWinner();
        } else {
            if (finalIndex != 6 && finalIndex != 13) {
                playerOneTurn = !playerOneTurn;
                statusText.setText(playerOneTurn ? "Player 1's Turn" : "Player 2's Turn");
            } else {
                statusText.setText(playerOneTurn ? "Player 1's Turn (Extra Turn)" : "Player 2's Turn (Extra Turn)");
            }
        }
    }

    private int getPitIndex(Button pit) {
        for (int i = 0; i < pits.length; i++) {
            if (pits[i] == pit) {
                return i;
            }
        }
        return -1;
    }

    private int distributeStones(int index) {
        int count = stones[index];
        stones[index] = 0;
        updatePitText(index);

        int currentIndex = index;
        while (count > 0) {
            currentIndex = (currentIndex + 1) % 14;

            if ((playerOneTurn && currentIndex == 13) || (!playerOneTurn && currentIndex == 6)) {
                continue;
            }

            stones[currentIndex]++;
            count--;
            updatePitText(currentIndex);
        }

        return currentIndex;
    }

    private void updatePitText(int index) {
        pits[index].setText(String.valueOf(stones[index]));
    }

    private boolean isValidPit(int index) {
        return (playerOneTurn && index >= 0 && index < 6) || (!playerOneTurn && index >= 7 && index < 13);
    }

    private boolean isGameOver() {
        boolean playerOneEmpty = true;
        boolean playerTwoEmpty = true;

        for (int i = 0; i < 6; i++) {
            if (stones[i] > 0) {
                playerOneEmpty = false;
                break;
            }
        }

        for (int i = 7; i < 13; i++) {
            if (stones[i] > 0) {
                playerTwoEmpty = false;
                break;
            }
        }

        return playerOneEmpty || playerTwoEmpty;
    }

    private void displayWinner() {
        for (int i = 0; i < 6; i++) {
            stones[6] += stones[i];
            stones[i] = 0;
            updatePitText(i);
        }

        for (int i = 7; i < 13; i++) {
            stones[13] += stones[i];
            stones[i] = 0;
            updatePitText(i);
        }

        updatePitText(6);
        updatePitText(13);

        String result = "Final Score:\nPlayer 1: " + stones[6] + " stones\n" +
                "Player 2: " + stones[13] + " stones\n";
        String winner = stones[6] > stones[13] ? "Player 1 Wins!" : stones[13] > stones[6] ? "Player 2 Wins!" : "It's a Tie!";
        statusText.setText(result + winner);

        for (Button pit : pits) {
            pit.setEnabled(false);
        }
    }
}