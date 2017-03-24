package com.ourproject.learningapp.globals;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Moetaz on 3/23/2017.
 */

public class GlobalLetter {
    private static MediaPlayer soundFile=new MediaPlayer();
    public static String LETTERTYPE="none";
    public static void pMusic(String url){
        try {
            soundFile.stop();
            soundFile = new MediaPlayer();
            soundFile.setDataSource(url);
            soundFile.prepare();
            soundFile.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
