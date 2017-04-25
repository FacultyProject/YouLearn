package com.ourproject.learningapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        finish();
    }
}
