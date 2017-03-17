package com.ourproject.learningapp.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.MadListFragment;




public class MadLettersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_letters);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fmainMad, new MadListFragment())
                .commit();
    }
}
