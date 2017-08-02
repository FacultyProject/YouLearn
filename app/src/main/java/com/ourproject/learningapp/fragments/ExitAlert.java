package com.ourproject.learningapp.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.firebase.client.Firebase;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.globals.ConstantVariables;
import com.ourproject.learningapp.globals.GlobalVariables;

/**
 * Created by Mohamed Ali on 6/12/2017.
 */
public class ExitAlert extends DialogFragment {

    private Firebase mScr ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScr = new Firebase(ConstantVariables.fScore);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).create();
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View ansDialogView = factory.inflate(R.layout.fragment_exit_quiz, null);
        alertDialog.setView(ansDialogView);
        ansDialogView.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Challanger =new  SharedPref(getActivity()).GetItem("Challanger");
                if(GlobalVariables.ChallangeMode){
                    if(Challanger != null || Challanger != "none"){

                        Firebase firebasechild = mScr.child(Challanger);
                        firebasechild.setValue("-2");
                    }
                }

               getActivity().finish();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        ansDialogView.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        return alertDialog;

    }
}
