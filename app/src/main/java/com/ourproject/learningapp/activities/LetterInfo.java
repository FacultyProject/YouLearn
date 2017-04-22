package com.ourproject.learningapp.activities;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.LetterFragment;

public class LetterInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_info);
        Intent intent = this.getIntent();

        LetterFragment letterFragment=new LetterFragment();
        FragmentManager fragmentManager=getFragmentManager();
        Bundle bundle=new Bundle();
        bundle.putSerializable("wordslist",  intent.getSerializableExtra("wordslist"));
        letterFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fmain2,letterFragment);
        fragmentTransaction.commit();
    }
}
