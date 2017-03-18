package com.ourproject.learningapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.adapters.LettersAdapter;
import com.ourproject.learningapp.models.LettersModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Ali on 3/2/2017.
 */
public class ListFragment extends Fragment {
    private List<LettersModel>list=new ArrayList<>();
    private String letters[],lettersNamed1[],lettersNamed2[],lettersNamed3[],
            lettersImages1[],lettersSounds[],lettersImages2[],lettersImages3[],
            picSounds1[],picSounds3[],picSounds2[];
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        letters=getActivity().getResources().getStringArray(R.array.letters);
        lettersNamed1=getActivity().getResources().getStringArray(R.array.lettersName1);
        lettersNamed2=getActivity().getResources().getStringArray(R.array.lettersName2);
        lettersNamed3=getActivity().getResources().getStringArray(R.array.lettersName3);
        lettersImages1=getActivity().getResources().getStringArray(R.array.lettersImages1);
        lettersImages2=getActivity().getResources().getStringArray(R.array.lettersImages2);
        lettersImages3=getActivity().getResources().getStringArray(R.array.lettersImages3);
        lettersSounds=getActivity().getResources().getStringArray(R.array.lettersSounds);
        picSounds1 =getActivity().getResources().getStringArray(R.array.PicSounds1);
        picSounds2=getActivity().getResources().getStringArray(R.array.PicSounds2);
        picSounds3=getActivity().getResources().getStringArray(R.array.PicSounds3);
        for (int i=0;i<letters.length;i++){
            LettersModel lettersModel=new LettersModel();
            lettersModel.setLetter(letters[i]);
            lettersModel.setWord1(lettersNamed1[i]);
            lettersModel.setWord2(lettersNamed2[i]);
            lettersModel.setWord3(lettersNamed3[i]);
            lettersModel.setPic1(lettersImages1[i]);
            lettersModel.setPic2(lettersImages2[i]);
            lettersModel.setPic3(lettersImages3[i]);
            lettersModel.setLetterSound(lettersSounds[i]);
            lettersModel.setPicSounds1(picSounds1[i]);
            lettersModel.setPicSounds2(picSounds2[i]);
            lettersModel.setPicSounds3(picSounds3[i]);
            list.add(lettersModel);
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.letters_recyvlerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        LettersAdapter lettersAdapter = new LettersAdapter(list, getActivity());
        recyclerView.setAdapter(lettersAdapter);
        return view;
    }
}
