package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AliGuliManeActivity extends AppCompatActivity {

    private Button[] pits = new Button[14];
    private int[] stones = new int[14];
    private TextView statusText;
    private boolean playerOneTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_guli_mane);

        statusText = findViewById(R.id.statusText);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Initialize buttons and set click listeners
        for (int i = 0; i < 14; i++) {
            String buttonID = "pit" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            pits[i] = findViewById(resID);
            pits[i].setOnClickListener(this::onPitClick);
            stones[i] = 4;
            updatePitText(i);
        }

        statusText.setText("Player 1's Turn");

        // Set up the instruction button
        Button instructionButton = findViewById(R.id.instructionButton);
        instructionButton.setOnClickListener(v -> {
            Intent intent = new Intent(AliGuliManeActivity.this, InstructionsActivity.class);
            startActivity(intent);
        });
    }

    private void onPitClick(View v) {
        Button clickedPit = (Button) v;
        int index = getPitIndex(clickedPit);

        if (stones[index] == 0) {
            return;
        }

        distributeStones(index);
        playerOneTurn = !playerOneTurn;
        statusText.setText(playerOneTurn ? "Player 1's Turn" : "Player 2's Turn");
    }

    private int getPitIndex(Button pit) {
        for (int i = 0; i < pits.length; i++) {
            if (pits[i] == pit) {
                return i;
            }
        }
        return -1;
    }

    private void distributeStones(int index) {
        int count = stones[index];
        stones[index] = 0;
        updatePitText(index);

        for (int i = 1; i <= count; i++) {
            int nextIndex = (index + i) % 14;
            stones[nextIndex]++;
            updatePitText(nextIndex);
        }
    }

    private void updatePitText(int index) {
        pits[index].setText(String.valueOf(stones[index]));
    }
}
