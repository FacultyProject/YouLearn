package com.ourproject.learningapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ourproject.learningapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChallangeNotYetAccepted extends Fragment {


    public ChallangeNotYetAccepted() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_challange_not_yet_accepted, container, false);
    }

}