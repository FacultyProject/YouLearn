package com.ourproject.learningapp.models;

import java.io.Serializable;

/**
 * Created by Moetaz on 3/16/2017.
 */

public class MadModel implements Serializable {
    private String letter;
    private String word1;
    private String word2;
    private String word3;
    private String word4;
    private String word5;
    private String word6;

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

    public void setWord4(String word4) {
        this.word4 = word4;
    }

    public void setWord5(String word5) {
        this.word5 = word5;
    }

    public void setWord6(String word6) {
        this.word6 = word6;
    }

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

    public String getWord4() {
        return word4;
    }

    public String getWord5() {
        return word5;
    }

    public String getWord6() {
        return word6;
    }
}