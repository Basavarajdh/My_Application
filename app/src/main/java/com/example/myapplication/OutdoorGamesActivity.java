package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class OutdoorGamesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outdoor_games);

        // Button for Cricket
        Button btnCricket = findViewById(R.id.btnCricket);
        btnCricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to CricketActivity
                Intent intent = new Intent(OutdoorGamesActivity.this, CricketActivity.class);
                startActivity(intent);
            }
        });
        // Example buttons for outdoor games

        Button btnBasketball = findViewById(R.id.btnBasketball);

        // Set OnClickListener for the Basketball button
        btnBasketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the Basketball game activity when the button is clicked
                Intent intent = new Intent(OutdoorGamesActivity.this, BasketballGameActivity.class);
                startActivity(intent);  // Start the Basketball game activity
            }
        });
    }
}
