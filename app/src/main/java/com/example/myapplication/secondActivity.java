package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.io.IOException;

public class secondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent in = getIntent();
        if (in != null && in.getAction() != null && in.getAction().equals(Intent.ACTION_VIEW)) {
            Uri musicUri = in.getData();
            if (musicUri != null) {
                playMusic(musicUri);
            }
        }

    }

    MediaPlayer mediaPlayer;

    private void playMusic(Uri musicUri) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(getApplicationContext(), musicUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
