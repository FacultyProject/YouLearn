package com.ourproject.learningapp.models;

import java.io.Serializable;

/**
 * Created by Moetaz on 3/16/2017.
 */

public class MadModel implements Serializable {
    private String letterSection;
    private String sectionSound;

    public String getLetterSection() {
        return letterSection;
    }

    public void setLetterSection(String letterSection) {
        this.letterSection = letterSection;
    }

    public String getSectionSound() {
        return sectionSound;
    }

    public void setSectionSound(String sectionSound) {
        this.sectionSound = sectionSound;
    }



}