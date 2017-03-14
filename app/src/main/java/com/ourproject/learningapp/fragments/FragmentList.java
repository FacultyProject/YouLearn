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
public class FragmentList extends Fragment {
    List<LettersModel>list=new ArrayList<>();
    String letters[],lettersNamed1[],lettersNamed2[],lettersNamed3[];
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        letters=getActivity().getResources().getStringArray(R.array.letters);
        lettersNamed1=getActivity().getResources().getStringArray(R.array.lettersName1);
        lettersNamed2=getActivity().getResources().getStringArray(R.array.lettersName2);
        lettersNamed3=getActivity().getResources().getStringArray(R.array.lettersName3);

        for (int i=0;i<letters.length;i++){
            LettersModel lettersModel=new LettersModel();
            lettersModel.setLetter(letters[i]);
            lettersModel.setWord1(lettersNamed1[i]);
            lettersModel.setWord2(lettersNamed2[i]);
            lettersModel.setWord3(lettersNamed3[i]);
            list.add(lettersModel);
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.letters_recyvlerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        LettersAdapter lettersAdapter = new LettersAdapter(list, getActivity());
        recyclerView.setAdapter(lettersAdapter);
        return view;
    }
}
