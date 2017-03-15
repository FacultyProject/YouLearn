package com.ourproject.learningapp.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class OnlineServiceClass extends Service {
    MediaPlayer mediaPlayer;
    Thread sound;
    public OnlineServiceClass() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = intent.getExtras().getString("url");
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }        sound=new Thread(){
            public void run(){
                mediaPlayer.start();
            }
        };
        sound.start();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
