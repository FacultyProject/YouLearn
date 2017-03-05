package com.ourproject.learningapp.models;

import java.io.Serializable;

/**
 * Created by Moetaz on 3/5/2017.
 */

public class LettersModel implements Serializable {
    private char letter;

    private String word1;
    private String word2;
    private String word3;

    private int pic1;
    private int pic2;
    private int pic3;

    private int sound1;
    private int sound2;
    private int sound3;

    private int letterSound;

    public char getLetter() {
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

    public int getPic1() {
        return pic1;
    }

    public int getPic2() {
        return pic2;
    }

    public int getPic3() {
        return pic3;
    }

    public int getSound1() {
        return sound1;
    }

    public int getSound2() {
        return sound2;
    }

    public int getSound3() {
        return sound3;
    }

    public int getLetterSound() {
        return letterSound;
    }

    public void setLetter(char letter) {
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

    public void setPic1(int pic1) {
        this.pic1 = pic1;
    }

    public void setPic2(int pic2) {
        this.pic2 = pic2;
    }

    public void setPic3(int pic3) {
        this.pic3 = pic3;
    }

    public void setSound1(int sound1) {
        this.sound1 = sound1;
    }

    public void setSound2(int sound2) {
        this.sound2 = sound2;
    }

    public void setSound3(int sound3) {
        this.sound3 = sound3;
    }

    public void setLetterSound(int letterSound) {
        this.letterSound = letterSound;
    }
}
