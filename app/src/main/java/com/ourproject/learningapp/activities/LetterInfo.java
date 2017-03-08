package com.ourproject.learningapp.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.FragmentList;
import com.ourproject.learningapp.fragments.LetterFragment;
import com.ourproject.learningapp.fragments.MainFragment;

public class LetterInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_info);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fLetter, new LetterFragment())
                    .commit();
        }
    }
}
