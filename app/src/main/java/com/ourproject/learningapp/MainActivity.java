
package com.ourproject.learningapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static Typeface font;
    public static boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        font=Typeface.createFromAsset(getAssets(),"fonts/andlso.ttf");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fmain, new  MainFragment())
                    .commit();
        }
        boolean tabletSize =getResources().getBoolean(R.bool.isTablet);
        if (tabletSize){
            mTwoPane=true;
        }
    }
}
