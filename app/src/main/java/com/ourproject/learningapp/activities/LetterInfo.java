package com.ourproject.learningapp.activities;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.LetterFragment;
import com.ourproject.learningapp.fragments.LettersAnim;
import com.ourproject.learningapp.globals.GlobalVariables;

public class LetterInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_info);
        Intent intent = this.getIntent();

        if (GlobalVariables.LETTERTYPE.equals("NORMAL_MOVEMENT")||GlobalVariables.LETTERTYPE.equals("ShortMovement")) {

            LetterFragment letterFragment = new LetterFragment();
            FragmentManager fragmentManager = getFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putSerializable("wordslist", intent.getSerializableExtra("wordslist"));
            letterFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fmain2, letterFragment);
            fragmentTransaction.commit();
        }else
        {
            LettersAnim lettersAnim = new LettersAnim();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fmain2, lettersAnim);
            fragmentTransaction.commit();
        }
    }
}
