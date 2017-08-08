package com.ourproject.learningapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.globals.GlobalVariables;

public class ScoreBoardActivity extends Activity {

    ImageView imageView;
    TextView textView;
    Firebase mScr;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!MainActivity.mTwoPane){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        }
        setContentView(R.layout.activity_score_board);
        imageView= (ImageView) findViewById(R.id.home_ic);
        textView= (TextView) findViewById(R.id.scr);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String USER = user.getEmail().substring(0,user.getEmail().indexOf('@'));
        mScr = new Firebase("https://youlearn-56a66.firebaseio.com/usersinfo");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToHome(USER);
            }
        });

        textView.setText(String.valueOf(GlobalVariables.scr));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user = firebaseAuth.getCurrentUser();
            final String USER = user.getEmail().substring(0,user.getEmail().indexOf('@'));
            GoToHome(USER);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void GoToHome(String user){
        if(GlobalVariables.ChallangeMode){
            GlobalVariables.ChallangeMode = false;
            mScr.child(GlobalVariables.getUserId()).child("ScoreInfo")
                    .child(new SharedPref(getApplicationContext()).GetItem("ChallengeKey"))
                    .child("UserScore").setValue(String.valueOf(GlobalVariables.scr));

            GlobalVariables.message(getApplicationContext(),new SharedPref(getApplicationContext()).GetItem("CompetitorId"));

            mScr.child(new SharedPref(getApplicationContext()).GetItem("CompetitorId")).child("ScoreInfo")
                    .child(new SharedPref(getApplicationContext()).GetItem("ChallengeKey"))
                    .child("CompetitorScore").setValue(String.valueOf(GlobalVariables.scr));


        }
        GlobalVariables.SelfTestMode=false;
        GlobalVariables.scr=0;
        GlobalVariables.nOfQUESTONS=0;
        GlobalVariables.BackFrom3rdTab = true;
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        finish();
    }
}
