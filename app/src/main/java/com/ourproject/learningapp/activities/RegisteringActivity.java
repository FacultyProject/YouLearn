package com.ourproject.learningapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.LoginFragment;

public class RegisteringActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registering);

        getSupportFragmentManager().beginTransaction().add(R.id.Register,new LoginFragment())
                .commit();

    }
}
