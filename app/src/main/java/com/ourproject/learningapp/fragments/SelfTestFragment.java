package com.ourproject.learningapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalVariables;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelfTestFragment extends Fragment {


    public SelfTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_self_test, container, false);

        if(GlobalVariables.nOfQUESTONS < 16)
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fmainST, new Quiz1Fragment() )
                .commit();
        else
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fmainST, new Q6Fragment() )
                    .commit();


        return view;
    }


}
