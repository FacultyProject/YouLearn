package com.ourproject.learningapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ourproject.learningapp.R;

/**
 * Created by Moetaz on 4/8/2017.
 */

public class WrongAnsAlert extends DialogFragment {

    ImageView imageView;
    TextView textView;
    TextView textView2;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).create();

        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.fragment_pop,null);
        imageView= (ImageView) view.findViewById(R.id.next);
        textView= (TextView) view.findViewById(R.id.textView);
        textView2= (TextView) view.findViewById(R.id.textv2);
        textView.setText("حاول مرة اخري!");
        alertDialog.setView(view);
        view.findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        textView2.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);

        alertDialog.show();

        return alertDialog;
    }
}
