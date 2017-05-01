package com.ourproject.learningapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MadLetterInfo;
import com.ourproject.learningapp.globals.GlobalVariables;

/**
 * A simple {@link Fragment} subclass.
 */
public class MadListFragment extends Fragment implements View.OnClickListener {

    TextView letter1, letter2, letter3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_mad_list, container, false);
        letter1 = (TextView) view.findViewById(R.id.mad1);
        letter2 = (TextView) view.findViewById(R.id.mad2);
        letter3 = (TextView) view.findViewById(R.id.mad3);
        letter1.setOnClickListener(this);
        letter2.setOnClickListener(this);
        letter3.setOnClickListener(this);
        return view;
    }
    private void setLettersMad(String letter,String letterRef){
        GlobalVariables.MAD_LETTER=letterRef;
        Intent intent= new Intent(getActivity(),MadLetterInfo.class);
        intent.putExtra("letter",letter);
        getActivity().startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mad1:
                setLettersMad("ا","Alf");
                break;
            case R.id.mad2:
                setLettersMad("و","Waw");
                break;
            case R.id.mad3:
                setLettersMad("ي","Yaa");
                break;
        }
    }
}