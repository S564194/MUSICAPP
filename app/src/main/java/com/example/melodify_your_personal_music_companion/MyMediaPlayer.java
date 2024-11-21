package com.example.melodify_your_personal_music_companion;

import android.media.MediaPlayer;

public class MyMediaPlayer {
    private static MediaPlayer mediaPlayerInstance;
    public static int currentIndex = -1;

    // Private constructor to prevent instantiation
    private MyMediaPlayer() {
        // No need to initialize MediaPlayer here as we are using the getInstance method
    }

    // Returns the single instance of MediaPlayer, creating it if necessary
    public static MediaPlayer getInstance() {
        if (mediaPlayerInstance == null) {
            mediaPlayerInstance = new MediaPlayer();
        }
        return mediaPlayerInstance;
    }
}
