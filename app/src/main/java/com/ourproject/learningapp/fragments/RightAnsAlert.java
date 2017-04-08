package com.ourproject.learningapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.Quiz1Fragment;

/**
 * Created by Moetaz on 4/7/2017.
 */

public class RightAnsAlert extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("My Alert");
        builder.setMessage("أحسنت تجرب مرة أخري؟");
        builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"Cancel Clicked", Toast.LENGTH_LONG);
            }
        });

        builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Quiz1Fragment mainFragment=new Quiz1Fragment();
                ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fQ1mian,mainFragment)
                        .commit();
            }
        });

        Dialog dialog=builder.create();

        return dialog;
    }
}
