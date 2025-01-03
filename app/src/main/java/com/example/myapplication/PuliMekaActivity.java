// src/com/example/yourapp/PuliMekaActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class PuliMekaActivity extends AppCompatActivity {

    private GridLayout gameBoard;
    private TextView gameStatus;
    private HashMap<String, Button> cells = new HashMap<>();
    private boolean isGoatTurn = true;
    private int goatCount = 15; // Total number of goats to place
    private int tigersCaptured = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puli_meka);

        gameBoard = findViewById(R.id.gameBoard);
        gameStatus = findViewById(R.id.gameStatus);

        initializeBoard();

        Button instructionButton = findViewById(R.id.showInfoButton);
        instructionButton.setOnClickListener(v -> {
            Intent intent = new Intent(PuliMekaActivity.this, showinfo.class);
            startActivity(intent);
        });
    }

    private void initializeBoard() {
        // Initialize each button on the grid and set click listeners
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                String buttonId = "cell_" + row + "_" + col;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                Button button = findViewById(resId);
                button.setOnClickListener(this::onCellClicked);
                cells.put(row + "_" + col, button);
            }
        }
    }

    public void onCellClicked(View view) {
        Button clickedButton = (Button) view;

        if (isGoatTurn && goatCount > 0) {
            placeGoat(clickedButton);
        } else if (!isGoatTurn) {
            moveTiger(clickedButton);
        }
    }

    private void placeGoat(Button button) {
        if (button.getText().equals("")) {
            button.setText("🐐");
            goatCount--;
            isGoatTurn = false;
            updateStatus("Tiger's Turn");
        } else {
            Toast.makeText(this, "Cell already occupied!", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveTiger(Button button) {
        if (button.getText().equals("")) {
            button.setText("🐅");
            isGoatTurn = true;
            updateStatus("Goat's Turn");
        } else {
            Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStatus(String message) {
        gameStatus.setText(message);
    }
}
