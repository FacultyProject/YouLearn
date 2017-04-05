package com.ourproject.learningapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.adapters.MadLettersAdapter;
import com.ourproject.learningapp.globals.GlobalLetter;
import com.ourproject.learningapp.models.MadModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MadListFragment extends Fragment {
    private String lettersMad [], WordMad1[], WordMad2[], WordMad3[], WordMad4[], WordMad5[], WordMad6[];
    private List<MadModel> listMad=new ArrayList<>();
    public MadListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_mad_list, container, false);

            lettersMad = getActivity().getResources().getStringArray(R.array.madletters);
            WordMad1 = getActivity().getResources().getStringArray(R.array.mad1);
            WordMad2 = getActivity().getResources().getStringArray(R.array.mad2);
            WordMad3 = getActivity().getResources().getStringArray(R.array.mad3);
            WordMad4 = getActivity().getResources().getStringArray(R.array.mad4);
            WordMad5 = getActivity().getResources().getStringArray(R.array.mad5);
            WordMad6 = getActivity().getResources().getStringArray(R.array.mad6);



        for(int i = 0; i < lettersMad.length; i++){
            MadModel madModel=new MadModel();
            madModel.setLetter(lettersMad[i]);
            madModel.setWord1(WordMad1[i]);
            madModel.setWord2(WordMad2[i]);
            madModel.setWord3(WordMad3[i]);
            madModel.setWord4(WordMad4[i]);
            madModel.setWord5(WordMad5[i]);
            madModel.setWord6(WordMad6[i]);
            listMad.add(madModel);
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.Madletters_recyvlerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        MadLettersAdapter madLettersAdapter=new MadLettersAdapter(listMad,getActivity());
        recyclerView.setAdapter(madLettersAdapter);
        return view;
    }

}
