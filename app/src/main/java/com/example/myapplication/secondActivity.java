package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class secondActivity extends Activity {
    private static final int PERMISSION_REQUEST_CODE = 101;
    private VideoView videoView;
    private Button playButton, pauseButton, stopButton;

    Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        stopButton = findViewById(R.id.stopButton);


        Intent in = getIntent();
        if (in != null && in.getAction() != null && in.getAction().equals(Intent.ACTION_VIEW)) {
            videoUri = in.getData();
            if (videoUri != null) {
                intentPlay(videoUri);
            }
        }


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseVideo();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVideo();
            }
        });
    }


    private void intentPlay(Uri video) {
        playVideouri();
    }

    private void loadVideoFile() {
        File videoDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).getPath());
        File[] videoFiles = videoDirectory.listFiles();

        if (videoFiles != null && videoFiles.length > 0) {
            String selectedVideoPath = videoFiles[0].getAbsolutePath();
//            playVideouri(selectedVideoPath);
        } else {
            Toast.makeText(this, "No video files found", Toast.LENGTH_SHORT).show();
        }
    }

    private void playVideouri() {
        videoView.setVideoURI(videoUri);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
    }

    private void playVideo() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    private void pauseVideo() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    private void stopVideo() {
        videoView.stopPlayback();
    }

}