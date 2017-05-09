package com.ourproject.learningapp.activities;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.fragments.SelfTestAlert;
import com.ourproject.learningapp.fragments.SelfTestFragment;
import com.ourproject.learningapp.globals.GlobalVariables;


public class SelfTestActivity extends AppCompatActivity {
    private final int EndPoint=0;
    public static boolean TimerIsRunning;
     int StartTimer=15;
    public static boolean isTimeReachedZero;
    ImageView HomeImg;
    TextView Score,Questions;
    TextView Timer;

    FirebaseAuth firebaseAuth;
    Firebase mCompititors,mScr;


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test);
        TimerIsRunning=true;
        isTimeReachedZero=false;
        Timer= (TextView) findViewById(R.id.timer);
        Questions= (TextView) findViewById(R.id.nofquietions);
        Questions.setText(String.valueOf(GlobalVariables.nOfQUESTONS));

        if(GlobalVariables.nOfQUESTONS == 7){
            TimerIsRunning=false;

            if(GlobalVariables.Is2ndPlayerPlay) {
               // Toast.makeText(getApplicationContext(),String.valueOf(GlobalVariables.Is2ndPlayerPlay)
                        //.concat(" one"),Toast.LENGTH_LONG).show();
                Save2ndUser();
               // mScr = new Firebase("https://youlearn-56a66.firebaseio.com/score");

                //Toast.makeText(getApplicationContext(), new SharedPref(getApplicationContext()).GetItem("User2"), Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), CompitionResultActivity.class));
            }
            else {
                Log.e("to score",String.valueOf(GlobalVariables.Is2ndPlayerPlay));
                Toast.makeText(getApplicationContext(),String.valueOf(GlobalVariables.Is2ndPlayerPlay)
                        .concat(" two"),Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), ScoreBoardActivity.class));
            }
        }



        GlobalVariables.nOfQUESTONS++;
        HomeImg= (ImageView) findViewById(R.id.home);
        Score= (TextView) findViewById(R.id.score);
        HomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimerIsRunning=false;
                StartTimer=60;
                GlobalVariables.SelfTestMode=false;
                GlobalVariables.scr=0;
                GlobalVariables.nOfQUESTONS=0;
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

         Score.setText( String.valueOf(GlobalVariables.scr));
        try {
            mythread t=new mythread();
            t.start();
        }catch (Exception e){
            Toast.makeText(SelfTestActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
        }


        GlobalVariables.SelfTestMode=true;
        if(GlobalVariables.nOfQUESTONS < 2)
        GlobalVariables.QUIZID="qIamge1";
        else if (GlobalVariables.nOfQUESTONS < 4 && GlobalVariables.nOfQUESTONS >= 2 )
            GlobalVariables.QUIZID="qIamge2";
        else
            GlobalVariables.QUIZID="qIamge3";


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fmainST, new SelfTestFragment())
                .commit();

    }

    public void Save2ndUser(){

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String USER = user.getEmail().substring(0,user.getEmail().indexOf('@'));
        mCompititors = new Firebase("https://youlearn-56a66.firebaseio.com/compititors");
        mCompititors.child(USER).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String user =dataSnapshot.getValue(String.class);
                new SharedPref(getApplicationContext()).SaveItem("User2",user);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }



    class mythread extends Thread{


        @Override
        public void run() {

            while (TimerIsRunning ) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    Timer.setText(String.valueOf(StartTimer));
                    }
                });
                 StartTimer--;
                //if(!isFinishing())
                if(StartTimer == EndPoint){
                    GlobalVariables.rAnswer=true;
                    isTimeReachedZero=true;

                        TimerIsRunning=false;
                        SelfTestAlert selfTestAlert=new SelfTestAlert();
                    try {


                        selfTestAlert.show(getSupportFragmentManager(), "anotheralert");
                    }catch (IllegalStateException e){
                        Log.e("Error","Erroe");
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
