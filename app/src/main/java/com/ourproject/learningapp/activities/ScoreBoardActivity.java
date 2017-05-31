package com.ourproject.learningapp.activities;

import android.app.Activity;
import android.content.Intent;
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
import com.ourproject.learningapp.globals.GlobalVariables;

public class ScoreBoardActivity extends Activity {

    ImageView imageView;
    TextView textView;
    Firebase mScr;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        imageView= (ImageView) findViewById(R.id.home_ic);
        textView= (TextView) findViewById(R.id.scr);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String USER = user.getEmail().substring(0,user.getEmail().indexOf('@'));
        mScr = new Firebase("https://youlearn-56a66.firebaseio.com/score");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GlobalVariables.ChallangeMode){
                    GlobalVariables.ChallangeMode = false;
                    mScr.child(USER).setValue(String.valueOf(GlobalVariables.scr));

                }
                GlobalVariables.SelfTestMode=false;
                GlobalVariables.scr=0;
                GlobalVariables.nOfQUESTONS=0;
                GlobalVariables.BackFrom3rdTab = true;
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        textView.setText(String.valueOf(GlobalVariables.scr));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        finish();
    }
}
