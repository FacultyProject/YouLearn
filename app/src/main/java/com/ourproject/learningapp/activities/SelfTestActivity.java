package com.ourproject.learningapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.Q4Fragment;
import com.ourproject.learningapp.fragments.SelfTestAlert;
import com.ourproject.learningapp.fragments.SelfTestFragment;
import com.ourproject.learningapp.fragments.WrongAnsAlert;
import com.ourproject.learningapp.globals.GlobalLetter;
import com.ourproject.learningapp.tabs_fragments.Fragment3;

import static com.ourproject.learningapp.globals.GlobalLetter.QUIZID;


public class SelfTestActivity extends AppCompatActivity {
    private final int EndPoint=0;
    public static boolean TimerIsRunning;
     int StartTimer=60;
    public static boolean isTimeReachedZero;
    ImageView HomeImg;
    TextView Score;
    TextView Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test);
        TimerIsRunning=true;
        isTimeReachedZero=false;
        Timer= (TextView) findViewById(R.id.timer);
        if(GlobalLetter.nOfQUESTONS == 21){
            TimerIsRunning=false;
            startActivity(new Intent(getApplicationContext(),ScoreBoardActivity.class));
        }


        GlobalLetter.nOfQUESTONS++;
        HomeImg= (ImageView) findViewById(R.id.home);
        Score= (TextView) findViewById(R.id.score);
        HomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimerIsRunning=false;
                StartTimer=60;
                GlobalLetter.SelfTestMode=false;
                GlobalLetter.scr=0;
                GlobalLetter.nOfQUESTONS=0;
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

         Score.setText( String.valueOf(GlobalLetter.scr));
        try {
            mythread t=new mythread();
            t.start();
        }catch (Exception e){
            Toast.makeText(SelfTestActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
        }


        GlobalLetter.SelfTestMode=true;
        if(GlobalLetter.nOfQUESTONS < 6)
        GlobalLetter.QUIZID="qIamge1";
        else if (GlobalLetter.nOfQUESTONS < 11 && GlobalLetter.nOfQUESTONS >= 6 )
            GlobalLetter.QUIZID="qIamge2";
        else
            GlobalLetter.QUIZID="qIamge3";


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fmainST, new SelfTestFragment())
                .commit();

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
                if(StartTimer == EndPoint){
                    GlobalLetter.rAnswer=true;
                    isTimeReachedZero=true;

                        TimerIsRunning=false;
                        SelfTestAlert selfTestAlert=new SelfTestAlert();
                        selfTestAlert.show(getSupportFragmentManager(),"anotheralert");


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
