package com.ourproject.learningapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class ServiceClass extends Service {
    MediaPlayer soundFile;
    Thread sound;
    public ServiceClass() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        soundFile.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int id = intent.getExtras().getInt("id");
        soundFile= MediaPlayer.create(this,id);
        sound=new Thread(){
            public void run(){
                soundFile.start();
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
