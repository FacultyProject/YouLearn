package com.ourproject.learningapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.SelfTestActivity;
import com.ourproject.learningapp.globals.GlobalLetter;

/**
 * Created by Moetaz on 4/23/2017.
 */

public class SelfTestAlert extends DialogFragment {
    TextView textView;
    ImageView imageView;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.fragment_pop,null);
        imageView= (ImageView) view.findViewById(R.id.next);
        textView= (TextView) view.findViewById(R.id.textView);
        if(!GlobalLetter.rAnswer)
        textView.setText("اجابة خاطئه");
        else if(SelfTestActivity.isTimeReachedZero )
            textView.setText(" الوقت انتهي");

        builder.setView(view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalLetter.rAnswer=false;
                startActivity(new Intent(getActivity(), SelfTestActivity.class));
                dismiss();

            }
        });


        Dialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
