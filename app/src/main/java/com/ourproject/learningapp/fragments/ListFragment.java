package com.ourproject.learningapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.adapters.LettersAdapter;
import com.ourproject.learningapp.globals.GlobalLetter;
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

        if (GlobalLetter.LETTERTYPE.equals("NORMAL_MOVEMENT")) {
            letters = getActivity().getResources().getStringArray(R.array.letters);
            lettersNamed1 = getActivity().getResources().getStringArray(R.array.lettersName1);
            lettersNamed2 = getActivity().getResources().getStringArray(R.array.lettersName2);
            lettersNamed3 = getActivity().getResources().getStringArray(R.array.lettersName3);
            lettersImages1 = getActivity().getResources().getStringArray(R.array.lettersImages1);
            lettersImages2 = getActivity().getResources().getStringArray(R.array.lettersImages2);
            lettersImages3 = getActivity().getResources().getStringArray(R.array.lettersImages3);
            lettersSounds = getActivity().getResources().getStringArray(R.array.lettersSounds);
            picSounds1 = getActivity().getResources().getStringArray(R.array.PicSounds1);
            picSounds2 = getActivity().getResources().getStringArray(R.array.PicSounds2);
            picSounds3 = getActivity().getResources().getStringArray(R.array.PicSounds3);
        }else
        {
            letters = getActivity().getResources().getStringArray(R.array.letters);
            lettersNamed1 = getActivity().getResources().getStringArray(R.array.KalematEldam);
            lettersNamed2 = getActivity().getResources().getStringArray(R.array.KalematElfath);
            lettersNamed3 = getActivity().getResources().getStringArray(R.array.KalematElkasr);
            lettersImages1 = getActivity().getResources().getStringArray(R.array.DamPics);
            lettersImages2 = getActivity().getResources().getStringArray(R.array.FathPics);
            lettersImages3 = getActivity().getResources().getStringArray(R.array.KasrPics);
            lettersSounds = getActivity().getResources().getStringArray(R.array.lettersSounds);
            picSounds1 = getActivity().getResources().getStringArray(R.array.damsound);
            picSounds2 = getActivity().getResources().getStringArray(R.array.fathsound);
            picSounds3 = getActivity().getResources().getStringArray(R.array.kasrsound);
        }
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
        RtlGridLayoutManager gridLayoutManager = new RtlGridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(gridLayoutManager);
        LettersAdapter lettersAdapter = new LettersAdapter(list, getActivity());
        recyclerView.setAdapter(lettersAdapter);
        return view;
    }
    public class RtlGridLayoutManager extends GridLayoutManager {

        public RtlGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public RtlGridLayoutManager(Context context, int spanCount) {
            super(context, spanCount);
        }

        public RtlGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }

        @Override
        protected boolean isLayoutRTL(){
            return true;
        }
    }
}
