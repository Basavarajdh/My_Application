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
    }
}
