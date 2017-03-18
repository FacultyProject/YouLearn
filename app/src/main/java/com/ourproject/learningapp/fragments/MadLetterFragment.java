package com.ourproject.learningapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.models.MadModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MadLetterFragment extends Fragment {


    private TextView letterMad,mWord1,mWord2,mWord3,mWord4,mWord5,mWord6;
    private MadModel madModel;
    public MadLetterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        madModel =(MadModel) bundle.getSerializable("madwordslist");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mad_letter, container, false);

        letterMad= (TextView) view.findViewById(R.id.letter_mad);
        mWord1= (TextView) view.findViewById(R.id.MWord1);
        mWord2= (TextView) view.findViewById(R.id.MWord2);
        mWord3= (TextView) view.findViewById(R.id.MWord3);
        mWord4= (TextView) view.findViewById(R.id.MWord4);
        mWord5= (TextView) view.findViewById(R.id.MWord5);
        mWord6= (TextView) view.findViewById(R.id.MWord6);

        letterMad.setText(madModel.getLetter());
        mWord1.setText(madModel.getWord1());
        mWord2.setText(madModel.getWord2());
        mWord3.setText(madModel.getWord3());
        mWord4.setText(madModel.getWord4());
        mWord5.setText(madModel.getWord5());
        mWord6.setText(madModel.getWord6());
        return view;
    }

}
