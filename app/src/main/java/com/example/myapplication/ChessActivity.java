package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class ChessActivity extends AppCompatActivity {
    private VideoView chessVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);

        // VideoView setup
        chessVideo = findViewById(R.id.chessVideo);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.chess_video);
        chessVideo.setVideoURI(videoUri);

        // Buttons for controlling the video
        Button btnStart = findViewById(R.id.btnStartChessVideo);
        Button btnStop = findViewById(R.id.btnStopChessVideo);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chessVideo.isPlaying()) {
                    chessVideo.start();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chessVideo.isPlaying()) {
                    chessVideo.pause();
                }
            }
        });
    }
}
