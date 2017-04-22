package com.ourproject.learningapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.adapters.LettersAdapter;
import com.ourproject.learningapp.adapters.MadLettersAdapter;
import com.ourproject.learningapp.models.MadModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MadLetterFragment extends Fragment {

    Bundle bundle;
    Boolean aBoolean=false;
    private String letterSection[],letter1Sounds[],letter2Sounds[],letter3Sounds[];
    String letters;
    private List<MadModel> listMad = new ArrayList<>();
    public MadLetterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mad_letter, container, false);
        bundle=getArguments();
        letters=bundle.getString("letter");
        letterSection = getActivity().getResources().getStringArray(R.array.letters);
        letter1Sounds = getActivity().getResources().getStringArray(R.array.MadBelAlfSounds);
        letter2Sounds = getActivity().getResources().getStringArray(R.array.MadBelWawSounds);
        letter3Sounds = getActivity().getResources().getStringArray(R.array.MadBelYaaSounds);
        for (int i = 0; i< letterSection.length; i++) {
            MadModel madModel=new MadModel();
            madModel.setLetterSection(letterSection[i]+bundle.get("letterI"));
            if (bundle.get("letterI").toString()=="ا") {
                madModel.setSectionSound(letter1Sounds[i]);
            }
            else if (bundle.get("letterI").toString()=="و") {
                madModel.setSectionSound(letter2Sounds[i]);
            }
            else {
                madModel.setSectionSound(letter3Sounds[i]);
            }
            listMad.add(madModel);
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.Madletters_recyvlerView);
        RtlGridLayoutManager gridLayoutManager = new RtlGridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        MadLettersAdapter lettersAdapter = new MadLettersAdapter(listMad, getActivity());
        recyclerView.setAdapter(lettersAdapter);
            return view;
    }
    public class RtlGridLayoutManager extends GridLayoutManager {

        public RtlGridLayoutManager(Context context, int spanCount) {
            super(context, spanCount);
        }

    }
}
