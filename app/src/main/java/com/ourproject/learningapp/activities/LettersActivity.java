package com.ourproject.learningapp.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ourproject.learningapp.fragments.ListFragment;
import com.ourproject.learningapp.R;

public class LettersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letters);
        //Toolbar toolbar= (Toolbar) findViewById(R.id.app_bar);
        //setSupportActionBar(toolbar);
        Fragment fragment = new ListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fmain1, fragment, "s");
        fragmentTransaction.commit();
    }
}
