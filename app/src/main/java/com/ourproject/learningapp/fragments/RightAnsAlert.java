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
import android.widget.Toast;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.fragments.Quiz1Fragment;

/**
 * Created by Moetaz on 4/7/2017.
 */

public class RightAnsAlert extends DialogFragment {

    ImageView imageView;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.fragment_pop,null);
        imageView= (ImageView) view.findViewById(R.id.next);
        builder.setView(view);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Q6Fragment.TAG == "Q5"){
                        Q6Fragment q6Fragment = new Q6Fragment();
                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fQ1mian, q6Fragment)
                                .commit();
                    }
                    else {
                        Quiz1Fragment quiz1Fragment = new Quiz1Fragment();
                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fQ1mian, quiz1Fragment)
                                .commit();
                    }
                    dismiss();
                }
            });

        Dialog dialog=builder.create();

        return dialog;
    }
}
