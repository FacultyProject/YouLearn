package com.ourproject.learningapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ourproject.learningapp.Interface.PositionRespone;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalVariables;


/**
 * Created by Mohamed Ali on 4/7/2017.
 */

public class Myalert extends DialogFragment {
    TextView textView;
    ImageView retry;
    PositionRespone positionRespone;


    public void setPositionRespone(PositionRespone positionRespone) {
        this.positionRespone = positionRespone;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).create();

        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View ansDialogView = factory.inflate(R.layout.fragment_alert, null);
        alertDialog.setView(ansDialogView);
        textView = (TextView) ansDialogView.findViewById(R.id.textView);
        retry = (ImageView) ansDialogView.findViewById(R.id.retry);
        retry.setVisibility(View.INVISIBLE);
        LinearLayout myLayout = (LinearLayout) ansDialogView.findViewById(R.id.linearLayout);
        if (Q4Fragment.position == 28 || LettersQuiz.position == 28) {
            textView.setTextSize(30);
            textView.setText(" اكتمل الاختبار");
            ansDialogView.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    (getActivity()).finish();
                    if (GlobalVariables.QUIZID == "qIamge0") {
                        positionRespone.postitionPlus(LettersQuiz.position = 0);
                    }
                    else {
                        positionRespone.postitionPlus(Q4Fragment.position = 0);
                    }


                }
            });
            myLayout.setVisibility(View.INVISIBLE);
        } else if (Q4Fragment.ans == "true" || LettersQuiz.ans == "true") {
            ansDialogView.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GlobalVariables.QUIZID == "qIamge0") {
                        positionRespone.postitionPlus(LettersQuiz.position++);
                        positionRespone.animation(LettersQuiz.textView1,LettersQuiz.textView2,LettersQuiz.textView3);

                    }
                    else {
                        positionRespone.postitionPlus(Q4Fragment.position++);
                        positionRespone.animation(Q4Fragment.imageView,Q4Fragment.imageView2,Q4Fragment.imageView3);

                    }
                    alertDialog.dismiss();
                }
            });
        } else if (Q4Fragment.ans == "false" || LettersQuiz.ans == "false") {
            retry.setVisibility(View.VISIBLE);
            textView.setText("حاول مرة اخري!");
            textView.setTextSize(30);
            ansDialogView.findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
            myLayout.setVisibility(View.INVISIBLE);
        } else {

        }

        alertDialog.show();


        return alertDialog;
    }
}
