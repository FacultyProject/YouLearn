package com.ourproject.learningapp.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ourproject.learningapp.PositionRespone;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MainActivity;


/**
 * Created by Moetaz on 4/7/2017.
 */

public class Myalert extends DialogFragment {
    TextView textView;
    PositionRespone positionRespone;


    public void setPositionRespone(PositionRespone positionRespone) {
        this.positionRespone = positionRespone;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).create();

        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View ansDialogView = factory.inflate(R.layout.fragment_pop, null);
        alertDialog.setView(ansDialogView);
        textView=(TextView)ansDialogView.findViewById(R.id.textView);
        LinearLayout myLayout = (LinearLayout)ansDialogView. findViewById(R.id.linearLayout);
        if (Q4Fragment.position==28 || Q5Fragment.COUNT==10){
            textView.setTextSize(30);
            textView.setText(" اكتمل الاختبار");
            ansDialogView.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                }
            });
            myLayout.setVisibility(View.VISIBLE);
        }else if (Q4Fragment.ans=="true"){
            ansDialogView.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    positionRespone.postitionPlus(Q4Fragment.position++);
                    alertDialog.dismiss();
                }
            });}
        else if (Q4Fragment.ans=="false"){
            textView.setText("حاول مرة اخري!");
            textView.setTextSize(30);
            ansDialogView.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
            myLayout.setVisibility(View.INVISIBLE);
        }

        alertDialog.show();


        return alertDialog;
    }
}
