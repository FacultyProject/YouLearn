package com.ourproject.learningapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.fragments.ChallangeAccepted;
import com.ourproject.learningapp.fragments.ChallangeNotYetAccepted;
import com.ourproject.learningapp.fragments.Q4Fragment;
import com.ourproject.learningapp.globals.GlobalVariables;

public class CompitionResultActivity extends AppCompatActivity {

    Firebase mScr;
    String CompititorUserScr = "-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compition_result);

        mScr = new Firebase("https://youlearn-56a66.firebaseio.com/score");

        GlobalVariables.onDataChange = true;
        mScr.child(new SharedPref(getApplicationContext()).GetItem("Challanger")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (GlobalVariables.onDataChange){
                    GlobalVariables.onDataChange = false;
                    CompititorUserScr = (String) dataSnapshot.getValue();


                if (Integer.parseInt(CompititorUserScr) >= 0 || GlobalVariables.Is2ndPlayerPlay) {
                        if(!GlobalVariables.Is2ndPlayerPlay)
                            GlobalVariables.Is1stPlayerPlay = true;
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fmainChallangeCase, new ChallangeAccepted())
                            .commit();
                } else if (Integer.parseInt(CompititorUserScr) == -1)
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fmainChallangeCase, new ChallangeNotYetAccepted())
                            .commit();
            }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });





    }
}
