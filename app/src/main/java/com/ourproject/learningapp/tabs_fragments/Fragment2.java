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
import com.ourproject.learningapp.activities.Quiz1Activity;
import com.ourproject.learningapp.globals.GlobalVariables;

/**
 * Created by Moetaz on 2/24/2017.
 */
public class Fragment2 extends Fragment {
    private ImageView QImaeg0,QImaeg1,QImaeg2,QImaeg3,QImaeg4,QImaeg5,QImaeg6,QImaeg7;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag2,container,false);
        QImaeg0 = (ImageView) view.findViewById(R.id.qimage0);
      //  QImaeg1 = (ImageView) view.findViewById(R.id.qimage1);
        QImaeg2 = (ImageView) view.findViewById(R.id.qimage2);
        QImaeg3 = (ImageView) view.findViewById(R.id.qimage3);
        QImaeg4 = (ImageView) view.findViewById(R.id.qimage4);
        QImaeg5 = (ImageView) view.findViewById(R.id.qimage5);
        QImaeg6 = (ImageView) view.findViewById(R.id.qimage6);
        QImaeg7 = (ImageView) view.findViewById(R.id.qimage7);

        QImaeg0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageonClick("qIamge0");

            }
        });




        QImaeg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageonClick("qIamge4");


            }
        });


        QImaeg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageonClick("qIamge1");


            }
        });

        QImaeg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageonClick("qIamge2");

            }
        });
        QImaeg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageonClick("qIamge3");

            }
        });

        QImaeg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageonClick("qIamge6");

            }
        });

        QImaeg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageonClick("qIamge7");
            }
        });
        return view;
    }

    public void ImageonClick(String id){
        GlobalVariables.nOfRightAns=0;
        GlobalVariables.QUIZID=id;
        Intent intent = new Intent(getActivity(), Quiz1Activity.class);
        getActivity().startActivity(intent);
    }
}
