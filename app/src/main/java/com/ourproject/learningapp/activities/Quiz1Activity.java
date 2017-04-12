package com.ourproject.learningapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.Q4Fragment;
import com.ourproject.learningapp.fragments.Q5Fragment;
import com.ourproject.learningapp.fragments.Q6Fragment;
import com.ourproject.learningapp.fragments.Quiz1Fragment;
import com.ourproject.learningapp.globals.GlobalLetter;

public class Quiz1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);


        if ( GlobalLetter.QUIZID=="qIamge4"){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fQ1mian, new Q4Fragment())
                    .commit();
        }else if(GlobalLetter.QUIZID=="qIamge5"){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fQ1mian, new Q5Fragment())
                    .commit();
        }
        else if(GlobalLetter.QUIZID=="qIamge6"){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fQ1mian, new Q6Fragment())
                    .commit();
        }
        else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fQ1mian, new Quiz1Fragment())
                    .commit();
        }
    }
}
