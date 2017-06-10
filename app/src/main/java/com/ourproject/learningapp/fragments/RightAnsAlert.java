package com.ourproject.learningapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalVariables;

/**
 * Created by Moetaz on 4/7/2017.
 */

public class RightAnsAlert extends DialogFragment {

    ImageView imageView,Retry;
    TextView textView1,textView2;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.fragment_pop, null);
        imageView = (ImageView) view.findViewById(R.id.next);
        textView1= (TextView) view.findViewById(R.id.textView);
        textView2= (TextView) view.findViewById(R.id.textv2);
        Retry = (ImageView) view.findViewById(R.id.retry);
        Retry.setVisibility(View.INVISIBLE);
        builder.setView(view);

        if (GlobalVariables.QuizCompleted) {
            Q5Fragment.COUNT = 0;
            GlobalVariables.QuizCompleted=false;
            textView1.setText("أكتمل الاختبار");
            textView2.setText("");
            imageView.setVisibility(View.INVISIBLE);

        } else{
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GlobalVariables.TAG.equals("Q6Fragment")) {

                        Q6Fragment q6Fragment = new Q6Fragment();
                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fQ1mian, q6Fragment)
                                .commit();

                        dismiss();
                    } else if(GlobalVariables.TAG.equals("Q7Fragment")){

                        Q7Fragment q7Fragment = new Q7Fragment();
                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fQ1mian, q7Fragment)
                                .commit();

                        dismiss();
                    }
                    else {
                        Quiz1Fragment quiz1Fragment = new Quiz1Fragment();
                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fQ1mian, quiz1Fragment)
                                .commit();

                        dismiss();
                    }

                }
            });
    }
        Dialog dialog=builder.create();

        return dialog;
    }
}
