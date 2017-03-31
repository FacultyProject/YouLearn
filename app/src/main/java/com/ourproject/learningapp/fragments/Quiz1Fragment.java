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

/**
 * A simple {@link Fragment} subclass.
 */
public class Quiz1Fragment extends Fragment {
    private List<QuizModel1> list=new ArrayList<>();
    private String [] PicUrl,PicName,Letter1,Letter2,Letter3,Letter4,Letter5,Letter6,Letter7
            ,Letter8,Letter9,Letter10;

    public Quiz1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quiz1, container, false);
        PicUrl=getActivity().getResources().getStringArray(R.array.Quizpics);
        PicName=getActivity().getResources().getStringArray(R.array.Quizwords);
        Letter1=getActivity().getResources().getStringArray(R.array.q1Letters1);
        Letter2=getActivity().getResources().getStringArray(R.array.q1Letters2);
        Letter3=getActivity().getResources().getStringArray(R.array.q1Letters3);
        Letter4=getActivity().getResources().getStringArray(R.array.q1Letters4);
        Letter5=getActivity().getResources().getStringArray(R.array.q1Letters5);
        Letter6=getActivity().getResources().getStringArray(R.array.q1Letters6);
        Letter7=getActivity().getResources().getStringArray(R.array.q1Letters7);
        Letter8=getActivity().getResources().getStringArray(R.array.q1Letters8);
        Letter9=getActivity().getResources().getStringArray(R.array.q1Letters9);
        Letter10=getActivity().getResources().getStringArray(R.array.q1Letters10);

        for(int i=0;i<PicUrl.length;i++){
            QuizModel1 quizModel1=new QuizModel1();
            quizModel1.setPicUrl(PicUrl[i]);
            quizModel1.setPicWord(PicName[i]);
            quizModel1.setLetter1(Letter1[i]);
            quizModel1.setLetter2(Letter2[i]);
            quizModel1.setLetter3(Letter3[i]);
            quizModel1.setLetter4(Letter4[i]);
            quizModel1.setLetter5(Letter5[i]);
            quizModel1.setLetter6(Letter6[i]);
            quizModel1.setLetter7(Letter7[i]);
            quizModel1.setLetter8(Letter8[i]);
            quizModel1.setLetter9(Letter9[i]);
            quizModel1.setLetter10(Letter10[i]);
            list.add(quizModel1);

        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.quiz1_recyvlerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        Quiz1Adapter quiz1Adapter=new Quiz1Adapter(list,getActivity());
        recyclerView.setAdapter(quiz1Adapter);

        return view;
    }

}
