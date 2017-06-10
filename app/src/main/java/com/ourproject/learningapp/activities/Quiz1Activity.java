package com.ourproject.learningapp.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.LettersQuiz;
import com.ourproject.learningapp.fragments.Q4Fragment;
import com.ourproject.learningapp.fragments.Q5Fragment;
import com.ourproject.learningapp.fragments.Q6Fragment;
import com.ourproject.learningapp.fragments.Q7Fragment;
import com.ourproject.learningapp.fragments.Quiz1Fragment;
import com.ourproject.learningapp.globals.GlobalVariables;

public class Quiz1Activity extends AppCompatActivity {
    public static Quiz1Activity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        instance=this;

        if ( GlobalVariables.QUIZID=="qIamge0"){
            GoToFragment(new LettersQuiz());
        }
        else if ( GlobalVariables.QUIZID=="qIamge4"){
            GoToFragment(new Q4Fragment());
        }
        else if(GlobalVariables.QUIZID=="qIamge5"){
            GoToFragment(new Q5Fragment());
        }
        else if(GlobalVariables.QUIZID=="qIamge6"){

            GoToFragment(new Q6Fragment());
        }
        else if(GlobalVariables.QUIZID=="qIamge7"){
            GoToFragment(new Q7Fragment());
        }
        else {
            GoToFragment(new Quiz1Fragment());

        }
    }

    public void GoToFragment(Fragment fragment){
        GlobalVariables.TAG = "none";
        GlobalVariables.G1.clear();
        GlobalVariables.G2.clear();
        GlobalVariables.G3.clear();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fQ1mian, fragment)
                .commit();
    }
}
