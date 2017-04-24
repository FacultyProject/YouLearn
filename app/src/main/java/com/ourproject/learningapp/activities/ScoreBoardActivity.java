package com.ourproject.learningapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalLetter;

public class ScoreBoardActivity extends Activity {

    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        imageView= (ImageView) findViewById(R.id.home_ic);
        textView= (TextView) findViewById(R.id.scr);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalLetter.SelfTestMode=false;
                GlobalLetter.scr=0;
                GlobalLetter.nOfQUESTONS=0;
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        textView.setText(String.valueOf(GlobalLetter.scr));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
