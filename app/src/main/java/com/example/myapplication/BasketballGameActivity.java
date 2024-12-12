package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BasketballGameActivity extends AppCompatActivity {

    private ImageView hoopImage, ballImage;
    private Button shootButton;
    private TextView scoreText;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball_game);  // Layout for the basketball game

        hoopImage = findViewById(R.id.hoopImage);
        ballImage = findViewById(R.id.ballImage);
        shootButton = findViewById(R.id.btnShoot);
        scoreText = findViewById(R.id.scoreText);

        shootButton.setOnClickListener(v -> shootBasketball());
    }

    private void shootBasketball() {
        // Simulate the basketball shoot
        ballImage.setVisibility(View.VISIBLE);

        // Simple animation for ball movement
        ballImage.animate()
                .translationYBy(-500f)  // Move the ball upwards
                .setDuration(1000)       // 1 second for shooting
                .withEndAction(() -> checkIfShotIsMade());
    }

    private void checkIfShotIsMade() {
        // This is a simple simulation. If the ball reaches the hoop, it's considered a successful shot.
        int hoopYPosition = hoopImage.getTop();
        int ballYPosition = ballImage.getTop();

        // Check if the ball is within the hoop's vertical range (basic check for demo purposes)
        if (ballYPosition < hoopYPosition + 150 && ballYPosition > hoopYPosition) {
            score++;
            scoreText.setText("Score: " + score);
            Toast.makeText(this, "Shot Made! Score: " + score, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Missed! Try Again", Toast.LENGTH_SHORT).show();
        }

        // Hide the ball again for the next shot
        ballImage.setVisibility(View.GONE);
    }
}
