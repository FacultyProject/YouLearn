package com.ourproject.learningapp.models;

import com.ourproject.learningapp.fragments.UsersListFragment;

import java.io.Serializable;

/**
 * Created by Moetaz on 8/5/2017.
 */

public class usersinfo {

    private String check ;
    private String email ;
    private String score;
    private String userName;
    private String userid;

    public usersinfo(){

    }

    public usersinfo(String check, String email, String score,String userName,String userid) {
        this.check = check;
        this.email = email;
        this.score = score;
        this.userName = userName;
        this.userid = userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {

        return userid;
    }

    public String getUserName() {
        return userName;
    }

    public String getCheck() {
        return check;
    }

    public String getEmail() {
        return email;
    }

    public String getScore() {
        return score;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
