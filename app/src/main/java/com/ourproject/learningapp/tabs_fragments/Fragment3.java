package com.ourproject.learningapp.tabs_fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.ChallangeActivity;
import com.ourproject.learningapp.activities.CompitionResultActivity;
import com.ourproject.learningapp.activities.SelfTestActivity;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.globals.ConstantVariables;
import com.ourproject.learningapp.globals.GlobalVariables;

/**
 * Created by Moetaz on 2/27/2017.
 */
public class Fragment3 extends Fragment {

    private CardView SelfTestCard ,cardView2;
    private Firebase mScr;
    AlertDialog.Builder alert;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag3, container, false);
        SelfTestCard = (CardView) view.findViewById(R.id.selftest);
        cardView2 = (CardView) view.findViewById(R.id.card2);


        mScr = new Firebase(ConstantVariables.fScore);
        alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("تأكيد الاختبار");
        alert.setMessage("هل تريد اكمال التحدي");


        SelfTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), SelfTestActivity.class));
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String USER = GlobalVariables.getUserName();
                    GlobalVariables.onDataChange = true;

                 mScr.child(USER).addValueEventListener(new ValueEventListener() {


                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         if(GlobalVariables.onDataChange) {
                             GlobalVariables.onDataChange = false;
                             String UserScr = (String) dataSnapshot.getValue();

                             if ((Integer.parseInt(UserScr) *  1) == -2) {

                                 startActivity(new Intent(getActivity(), ChallangeActivity.class));
                             } else if ((Integer.parseInt(UserScr) *  1) == -1) {
                                 alert.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialogInterface, int i) {
                                         GlobalVariables.ChallangeMode = true;
                                         GlobalVariables.Is2ndPlayerPlay = true;
                                         startActivity(new Intent(getActivity(), SelfTestActivity.class));
                                     }
                                 });

                                 alert.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                                     @Override
                                     public void onClick(DialogInterface dialogInterface, int i) {

                                         Firebase childRef =mScr.child(GlobalVariables.getUserName());
                                         childRef.setValue("-2");
                                     }
                                 });
                                 AlertDialog alertDialog = alert.create();
                                 alertDialog.show();


                             } else if (Integer.parseInt(UserScr) >= 0) {

                                 startActivity(new Intent(getActivity(), CompitionResultActivity.class));
                             }
                             else
                                 Log.e("else", UserScr );
                         }

                     }

                     @Override
                     public void onCancelled(FirebaseError firebaseError) {

                     }

                 });


            }
        });


            return view;
    }
}
