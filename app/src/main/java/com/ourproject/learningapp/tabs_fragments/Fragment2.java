package com.ourproject.learningapp.tabs_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.LettersActivity;
import com.ourproject.learningapp.activities.Quiz1Activity;
import com.ourproject.learningapp.activities.Quiz4Activity;
import com.ourproject.learningapp.globals.GlobalLetter;

/**
 * Created by Moetaz on 2/24/2017.
 */
public class Fragment2 extends Fragment {
    ImageView QImaeg1;
    ImageView QImaeg2;
    ImageView QImaeg3;
    ImageView QImaeg4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag2,container,false);
        QImaeg1 = (ImageView) view.findViewById(R.id.qimage1);
        QImaeg2 = (ImageView) view.findViewById(R.id.qimage2);
        QImaeg3 = (ImageView) view.findViewById(R.id.qimage3);
        QImaeg4 = (ImageView) view.findViewById(R.id.qimage4);
        QImaeg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalLetter.QUIZID="qIamge1";
                Intent intent = new Intent(getActivity(), Quiz1Activity.class);
                getActivity().startActivity(intent);
            }
        });



        QImaeg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalLetter.QUIZID="qIamge2";
                Intent intent = new Intent(getActivity(), Quiz1Activity.class);
                getActivity().startActivity(intent);
            }
        });



        QImaeg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalLetter.QUIZID="qIamge3";
                Intent intent = new Intent(getActivity(), Quiz1Activity.class);
                getActivity().startActivity(intent);
            }
        });

        QImaeg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Quiz4Activity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}
