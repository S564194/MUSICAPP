package com.example.my_music;

import android.media.MediaPlayer;

public class MyMediaPlayer {
    private static MediaPlayer instance;
    private static int currentIndex = -1;

    private MyMediaPlayer() {
        
    }

    public static MediaPlayer getInstance() {
        if (instance == null) {
            instance = new MediaPlayer();
        }
        return instance;
    }

    public static int getCurrentIndex() {
        return currentIndex;
    }

    public static void setCurrentIndex(int index) {
        currentIndex = index;
    }
}