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

public class CompitionResultActivity extends AppCompatActivity {

    Firebase mScr;
    String CompititorUserScr = "none";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compition_result);

        mScr = new Firebase("https://youlearn-56a66.firebaseio.com/score");
        mScr.child(new SharedPref(getApplicationContext()).GetItem("Challanger")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CompititorUserScr = (String) dataSnapshot.getValue();


                if(Integer.parseInt(CompititorUserScr) == -1)
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fmainChallangeCase, new ChallangeNotYetAccepted())
                            .commit();
                else if(Integer.parseInt(CompititorUserScr) >= 0)
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fmainChallangeCase, new ChallangeAccepted())
                            .commit();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });





    }
}
