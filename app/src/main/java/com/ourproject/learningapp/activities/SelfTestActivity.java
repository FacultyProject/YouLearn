package com.ourproject.learningapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.Q4Fragment;
import com.ourproject.learningapp.fragments.SelfTestFragment;
import com.ourproject.learningapp.globals.GlobalLetter;
import com.ourproject.learningapp.tabs_fragments.Fragment3;

import static com.ourproject.learningapp.globals.GlobalLetter.QUIZID;


public class SelfTestActivity extends AppCompatActivity {
    ImageView HomeImg;
    TextView Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test);
        if(GlobalLetter.nOfQUESTONS == 20)
            startActivity(new Intent(getApplicationContext(),ScoreBoardActivity.class));

        GlobalLetter.nOfQUESTONS++;
        HomeImg= (ImageView) findViewById(R.id.home);
        Score= (TextView) findViewById(R.id.score);
        HomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalLetter.SelfTestMode=false;
                GlobalLetter.scr=0;
                GlobalLetter.nOfQUESTONS=0;
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

         Score.setText( String.valueOf(GlobalLetter.scr));

        GlobalLetter.SelfTestMode=true;
        if(GlobalLetter.nOfQUESTONS < 5)
        GlobalLetter.QUIZID="qIamge1";
        else if (GlobalLetter.nOfQUESTONS < 10 && GlobalLetter.nOfQUESTONS >= 5 )
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
}
