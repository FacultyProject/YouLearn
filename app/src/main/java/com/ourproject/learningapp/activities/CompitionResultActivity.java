package com.ourproject.learningapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.ourproject.learningapp.fragments.ChallangeAccepted;
import com.ourproject.learningapp.fragments.ChallangeNotYetAccepted;
import com.ourproject.learningapp.fragments.Q4Fragment;
import com.ourproject.learningapp.globals.GlobalVariables;

public class CompitionResultActivity extends AppCompatActivity {

    Firebase mScr,mCompititors;
    String CompititorUserScr = "-1";
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compition_result);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        String USER = user.getEmail().substring(0,user.getEmail().indexOf('@'));
        mCompititors = new Firebase("https://youlearn-56a66.firebaseio.com/compititors");

        mScr = new Firebase("https://youlearn-56a66.firebaseio.com/score");

        GlobalVariables.onDataChange = true;


        mCompititors.child(USER).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String usercom =dataSnapshot.getValue(String.class);

                mScr.child(usercom).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (GlobalVariables.onDataChange){
                            GlobalVariables.onDataChange = false;

                            CompititorUserScr = (String) dataSnapshot.getValue();
                            Toast.makeText(getApplicationContext(),CompititorUserScr,Toast.LENGTH_LONG).show();

                            if(GlobalVariables.Is2ndPlayerFinish){
                                getSupportFragmentManager().beginTransaction()
                                        .add(R.id.fmainChallangeCase, new ChallangeNotYetAccepted())
                                        .commit();
                            }

                            else if (Integer.parseInt(CompititorUserScr) >= 0 || GlobalVariables.Is2ndPlayerPlay) {
                                if(!GlobalVariables.Is2ndPlayerPlay)
                                    GlobalVariables.Is2ndPlayerPlay = false;
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

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }
}
