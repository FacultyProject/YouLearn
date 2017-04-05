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
import com.ourproject.learningapp.adapters.Quiz1Adapter;
import com.ourproject.learningapp.models.LettersModel;
import com.ourproject.learningapp.models.QuizModel1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quiz1Fragment extends Fragment {
    private List<QuizModel1> list=new ArrayList<>();
    private String [] PicUrl,PicName,PicSound ;

    public Quiz1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quiz1, container, false);
        Random r=new Random();
        int x=r.nextInt(3);

        if(x==0) {
            PicUrl = getActivity().getResources().getStringArray(R.array.lettersImages1);
            PicName = getActivity().getResources().getStringArray(R.array.lettersName1);
            PicSound = getActivity().getResources().getStringArray(R.array.PicSounds1);
        }
        else if(x==1){
            PicUrl = getActivity().getResources().getStringArray(R.array.lettersImages2);
            PicName = getActivity().getResources().getStringArray(R.array.lettersName2);
            PicSound = getActivity().getResources().getStringArray(R.array.PicSounds2);
        }
        else{
            PicUrl = getActivity().getResources().getStringArray(R.array.lettersImages3);
            PicName = getActivity().getResources().getStringArray(R.array.lettersName3);
            PicSound = getActivity().getResources().getStringArray(R.array.PicSounds3);
        }

        for(int i=0;i<PicUrl.length;i++){
            QuizModel1 quizModel1=new QuizModel1();
            quizModel1.setPicUrl(PicUrl[i]);
            quizModel1.setPicWord(PicName[i]);
            quizModel1.setSoundUrl(PicSound[i]);

            list.add(quizModel1);

        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.quiz1_recyvlerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        Quiz1Adapter quiz1Adapter=new Quiz1Adapter(list,getActivity());
        recyclerView.setAdapter(quiz1Adapter);

        return view;
    }

}
