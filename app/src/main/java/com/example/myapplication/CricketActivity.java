package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class CricketActivity extends AppCompatActivity {
    private VideoView cricketVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket);

        // VideoView setup
        cricketVideo = findViewById(R.id.cricketVideo);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cricket_video);
        cricketVideo.setVideoURI(videoUri);

        // Buttons for controlling the video
        Button btnStart = findViewById(R.id.btnStartCricketVideo);
        Button btnStop = findViewById(R.id.btnStopCricketVideo);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cricketVideo.isPlaying()) {
                    cricketVideo.start();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cricketVideo.isPlaying()) {
                    cricketVideo.pause();
                }
            }
        });
    }
}
