package com.ourproject.learningapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.globals.GlobalVariables;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChallangeAccepted extends Fragment {
    TextView textView1,textView2;
    ImageView imageView;
    FirebaseAuth firebaseAuth;
    Firebase mScr,mCompititors;
    boolean Enter = false;

    public ChallangeAccepted() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_challange_accepted, container, false);
        textView1 = (TextView) view.findViewById(R.id.t1);
        textView2 = (TextView) view.findViewById(R.id.t2);
        imageView = (ImageView) view.findViewById(R.id.home2);

        firebaseAuth = FirebaseAuth.getInstance();
        mScr = new Firebase("https://youlearn-56a66.firebaseio.com/score");
        mCompititors = new Firebase("https://youlearn-56a66.firebaseio.com/compititors");



          FirebaseUser user = firebaseAuth.getCurrentUser();
          final String USER = user.getEmail().substring(0,user.getEmail().indexOf('@'));

        if(!GlobalVariables.Is2ndPlayerPlay){

            mScr.child(USER).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String UserScr2 = dataSnapshot.getValue(String.class);
                    if(Integer.parseInt(UserScr2) >= 0){
                        textView1.setText(UserScr2);
                        mScr.child(new SharedPref(getActivity()).GetItem("Challanger")).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String US2 = dataSnapshot.getValue(String.class);
                                textView2.setText(US2);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                    else
                        Enter = true;

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });


            if(Enter) {

                mScr.child(USER).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String UserScr2 = dataSnapshot.getValue(String.class);
                        textView1.setText(UserScr2);


                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                });

                mCompititors.child(USER).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String User2 = dataSnapshot.getValue(String.class);
                        Firebase Ref1 = mScr.child(User2);
                        Ref1.setValue("-2");

                        mScr.child(User2).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String Userscr2 = dataSnapshot.getValue(String.class);
                                textView2.setText(Userscr2);
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


        }else {
            textView1.setText(String.valueOf(GlobalVariables.scr));
            //changed here
            mScr.child(new SharedPref(getActivity()).GetItem("Challanger")).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String UserScr2 = dataSnapshot.getValue(String.class);
                    textView2.setText(UserScr2);


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
                }
            });
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !GlobalVariables.Is2ndPlayerPlay){
                    Firebase Ref1 = mScr.child(USER);
                    Ref1.setValue("-2");
                    Firebase Ref2 = mScr.child(new SharedPref(getActivity()).GetItem("Challanger"));
                    Ref2.setValue("-2");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }else {
                    GlobalVariables.Is2ndPlayerFinish = true;
                     //GlobalVariables.Is2ndPlayerPlay = false;
                    Firebase Ref1 = mScr.child(USER);
                    Ref1.setValue(String.valueOf(GlobalVariables.scr));
                    GlobalVariables.ChallangeMode = false;
                    GlobalVariables.SelfTestMode = false;
                    GlobalVariables.scr = 0;
                    GlobalVariables.nOfQUESTONS = 0;
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }

            }
        });


        return view;
    }

}
