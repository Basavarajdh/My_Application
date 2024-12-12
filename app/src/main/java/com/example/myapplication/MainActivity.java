package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        Button btnIndoorGames = findViewById(R.id.btnIndoorGames);
        Button btnOutdoorGames = findViewById(R.id.btnOutdoorGames);

        // Set OnClickListener for Indoor Games button
        btnIndoorGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IndoorGamesActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for Outdoor Games button
        btnOutdoorGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OutdoorGamesActivity.class);
                startActivity(intent);
            }
        });


    }
}
