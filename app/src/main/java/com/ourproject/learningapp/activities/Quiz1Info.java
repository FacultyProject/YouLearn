package com.ourproject.learningapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.MadLetterFragment;
import com.ourproject.learningapp.fragments.Quiz1FragmentInfo;
import com.ourproject.learningapp.models.QuizModel1;

public class Quiz1Info extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1_info);

        Intent intent = this.getIntent();

        Quiz1FragmentInfo quiz1FragmentInfo= new Quiz1FragmentInfo();

        Bundle bundle=new Bundle();
        bundle.putSerializable("qlist",  intent.getSerializableExtra("qlist"));
        quiz1FragmentInfo.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fQ1info, quiz1FragmentInfo)
                .commit();
    }
}
