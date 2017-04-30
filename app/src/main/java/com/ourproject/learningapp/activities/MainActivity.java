
package com.ourproject.learningapp.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.ourproject.learningapp.fragments.MainFragment;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.services.ServiceClass;

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
                    .add(R.id.fmain, new MainFragment())
                    .commit();
        }
        boolean tabletSize =getResources().getBoolean(R.bool.isTablet);
        if (tabletSize){
            mTwoPane=true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, ServiceClass.class));

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
