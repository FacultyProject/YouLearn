package com.ourproject.learningapp.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.MadLetterFragment;

public class MadLetterInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mad_letter_info);
        Intent intent = this.getIntent();

        MadLetterFragment madLetterFragment= new MadLetterFragment();

        Bundle bundle=new Bundle();
        bundle.putString("letterI",intent.getExtras().getString("letter"));
        madLetterFragment.setArguments(bundle);

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fmainMadinfo,madLetterFragment);
        fragmentTransaction.commit();


    }
}
