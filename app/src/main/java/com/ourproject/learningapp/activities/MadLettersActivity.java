package com.ourproject.learningapp.activities;


import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.MadListFragment;




public class MadLettersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_letters);
        if (!MainActivity.mTwoPane){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fmainMad, new MadListFragment())
                .commit();
    }
}
