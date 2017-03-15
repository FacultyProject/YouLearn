package com.ourproject.learningapp.models;

import java.io.Serializable;

/**
 * Created by Moetaz on 3/5/2017.
 */

public class LettersModel implements Serializable {
    private String letter;

    private String word1;
    private String word2;
    private String word3;

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    private String pic1;

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    private String pic2;

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    private String pic3;

    public String getPicSounds1() {
        return picSounds1;
    }

    public void setPicSounds1(String picSounds1) {
        this.picSounds1 = picSounds1;
    }

    private String picSounds1;

    public String getPicSounds3() {
        return picSounds3;
    }

    public void setPicSounds3(String picSounds3) {
        this.picSounds3 = picSounds3;
    }

    public String getPicSounds2() {
        return picSounds2;
    }

    public void setPicSounds2(String picSounds2) {
        this.picSounds2 = picSounds2;
    }

    private String picSounds2;
    private String picSounds3;

    public String getLetterSound() {
        return letterSound;
    }

    public void setLetterSound(String letterSound) {
        this.letterSound = letterSound;
    }

    private String letterSound;

    public String getLetter() {
        return letter;
    }

    public String getWord1() {
        return word1;
    }

    public String getWord2() {
        return word2;
    }

    public String getWord3() {
        return word3;
    }


    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public void setWord3(String word3) {
        this.word3 = word3;
    }

   }
