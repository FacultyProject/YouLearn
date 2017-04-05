package com.ourproject.learningapp.models;

import java.io.Serializable;

/**
 * Created by Moetaz on 3/26/2017.
 */

public class QuizModel1 implements Serializable {
    private String SoundUrl;
    private String PicUrl;
    private String PicWord;



    public void setSoundUrl(String soundUrl) {
        SoundUrl = soundUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public void setPicWord(String picWord) {
        PicWord = picWord;
    }





    public String getSoundUrl() {
        return SoundUrl;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public String getPicWord() {
        return PicWord;
    }



}

