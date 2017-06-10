package com.ourproject.learningapp.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.fragments.SelfTestAlert;
import com.ourproject.learningapp.fragments.SelfTestFragment;
import com.ourproject.learningapp.globals.ConstantVariables;
import com.ourproject.learningapp.globals.GlobalVariables;


public class SelfTestActivity extends AppCompatActivity {
    private final int EndPoint = 0;
    public static boolean TimerIsRunning;
    private int StartTimer = 15;
    public static boolean isTimeReachedZero;
    private ImageView HomeImg;
    private TextView Score, Questions, Timer;
    private Firebase mCompititors ;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //super.onSaveInstanceState(outState, outPersistentState);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test);
        if (!MainActivity.mTwoPane){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        }
        TimerIsRunning = true;
        isTimeReachedZero = false;
        Timer = (TextView) findViewById(R.id.timer);
        Questions = (TextView) findViewById(R.id.nofquietions);
        Questions.setText(String.valueOf(GlobalVariables.nOfQUESTONS));

        if (GlobalVariables.nOfQUESTONS == 7) {
            TimerIsRunning = false;

            if (GlobalVariables.Is2ndPlayerPlay) {

                Save2ndUser();

                startActivity(new Intent(getApplicationContext(), CompitionResultActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), String.valueOf(GlobalVariables.Is2ndPlayerPlay)
                        .concat(" two"), Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), ScoreBoardActivity.class));
            }
        }

        GlobalVariables.nOfQUESTONS++;
        HomeImg = (ImageView) findViewById(R.id.home);
        Score = (TextView) findViewById(R.id.score);
        HomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToHome();
            }
        });

        Score.setText(String.valueOf(GlobalVariables.scr));
        try {
            MyThread t = new MyThread();
            t.start();
        } catch (Exception e) {
            GlobalVariables.message(getApplicationContext(),"Error");
        }


        GlobalVariables.SelfTestMode = true;
        if (GlobalVariables.nOfQUESTONS < 2)
            GlobalVariables.QUIZID = "qIamge1";
        else if (GlobalVariables.nOfQUESTONS < 4 && GlobalVariables.nOfQUESTONS >= 2)
            GlobalVariables.QUIZID = "qIamge2";
        else
            GlobalVariables.QUIZID = "qIamge3";


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fmainST, new SelfTestFragment())
                .commit();

    }

    public void GoToHome() {
        GlobalVariables.BackFrom3rdTab = true;
        TimerIsRunning = false;
        StartTimer = 60;
        GlobalVariables.SelfTestMode = false;
        GlobalVariables.scr = 0;
        GlobalVariables.nOfQUESTONS = 0;
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    public void Save2ndUser() {

        String USER = GlobalVariables.getUserName();

        mCompititors = new Firebase(ConstantVariables.fCompititors);
        mCompititors.child(USER).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String usercom = dataSnapshot.getValue(String.class);

                new SharedPref(getApplicationContext()).SaveItem("Challanger", usercom);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            GoToHome();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    class MyThread extends Thread {


        @Override
        public void run() {

            while (TimerIsRunning) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Timer.setText(String.valueOf(StartTimer));
                    }
                });
                StartTimer--;
                //if(!isFinishing())
                if (StartTimer == EndPoint) {
                    GlobalVariables.rAnswer = true;
                    isTimeReachedZero = true;

                    TimerIsRunning = false;
                    SelfTestAlert selfTestAlert = new SelfTestAlert();
                    try {

                        selfTestAlert.show(getSupportFragmentManager(), "anotheralert");
                    } catch (IllegalStateException e) {
                        Log.e("Error", "Erroe");
                    }

                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
