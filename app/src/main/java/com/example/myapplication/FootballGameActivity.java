package com.example.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FootballGameActivity extends AppCompatActivity {

    private ImageView goalPostImage, goalkeeperImage, ballImage, arrowImage;
    private Button shootButton;
    private TextView scoreText;
    private int score = 0;
    private float arrowAngle = 0f; // Initial arrow angle
    private final float MAX_ANGLE = 30f; // Maximum left/right angle in degrees

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_game);

        goalPostImage = findViewById(R.id.goalPostImage);
        goalkeeperImage = findViewById(R.id.goalkeeperImage);
        ballImage = findViewById(R.id.ballImage);
        arrowImage = findViewById(R.id.arrowImage);
        shootButton = findViewById(R.id.btnShoot);
        scoreText = findViewById(R.id.scoreText);

        // Touch listener to control arrow direction
        arrowImage.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                float x = event.getX();
                updateArrowAngle(x);
            }
            return true;
        });

        // Shoot button logic
        shootButton.setOnClickListener(v -> shootFootball());
    }

    private void updateArrowAngle(float touchX) {
        // Calculate angle based on touch position
        float arrowCenterX = arrowImage.getWidth() / 2f;
        arrowAngle = ((touchX - arrowCenterX) / arrowCenterX) * MAX_ANGLE;

        // Clamp the angle to the MAX_ANGLE range
        if (arrowAngle > MAX_ANGLE) arrowAngle = MAX_ANGLE;
        if (arrowAngle < -MAX_ANGLE) arrowAngle = -MAX_ANGLE;

        // Rotate the arrow to the new angle
        arrowImage.setRotation(arrowAngle);
    }

    private void shootFootball() {
        // Calculate horizontal movement based on arrow angle
        float distance = -900f; // Vertical distance (upward)
        float horizontalDistance = (float) (distance * Math.tan(Math.toRadians(arrowAngle)));

        // Animate the ball towards the goal
        ballImage.animate()
                .translationYBy(distance)          // Move upwards
                .translationXBy(horizontalDistance) // Move left/right based on arrow angle
                .setDuration(1000)
                .withEndAction(this::checkIfGoalIsScored);
    }

    private void checkIfGoalIsScored() {
        // Get positions of the ball and goalpost
        int goalX = goalPostImage.getLeft();
        int goalWidth = goalPostImage.getWidth();
        int ballX = ballImage.getLeft();
        int ballWidth = ballImage.getWidth();

        // Check if the ball is within the goal's horizontal range
        if (ballX + ballWidth / 2 > goalX && ballX + ballWidth / 2 < goalX + goalWidth) {
            score++;
            scoreText.setText("Score: " + score);
            Toast.makeText(this, "Goal! Score: " + score, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Missed! Try Again", Toast.LENGTH_SHORT).show();
        }

        resetBallPosition();
    }

    private void resetBallPosition() {
        // Reset ball and arrow position
        ballImage.setTranslationX(0);
        ballImage.setTranslationY(0);
        arrowImage.setRotation(0);
        arrowAngle = 0f;
    }
}
