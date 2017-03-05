package com.ourproject.learningapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ourproject.learningapp.adapters.LettersAdapter;
import com.ourproject.learningapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Ali on 3/2/2017.
 */
public class FragmentList extends Fragment {
    List<String>list=new ArrayList<>();
    String letters[]={"ث","ت","ب","أ",
            "ج","ح","خ","د",
            "ذ","ر","ز","س",
            "ش","ص","ض","ط",
            "ظ","ع","غ","ف",
            "ق","ك","ل","م",
            "ن","هـ","و","ي"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        for (int i=0;i<letters.length;i++){
            list.add(letters[i]);
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.letters_recyvlerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        LettersAdapter lettersAdapter = new LettersAdapter(list, getActivity());
        recyclerView.setAdapter(lettersAdapter);
        return view;
    }
}
