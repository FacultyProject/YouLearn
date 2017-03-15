package com.ourproject.learningapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.models.LettersModel;
import com.ourproject.learningapp.services.OnlineServiceClass;
import com.ourproject.learningapp.services.ServiceClass;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by Mohamed Ali on 3/14/2017.
 */
public class LetterFragment extends Fragment {
    TextView wordTxt1,wordTxt2,wordTxt3,letter;
    ImageView word1Img,word2Img,word3Img;
    LettersModel lettersModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        lettersModel=(LettersModel) bundle.getSerializable("wordslist");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_letter, container, false);
        letter= (TextView) view.findViewById(R.id.letter);
        wordTxt1= (TextView) view.findViewById(R.id.word1);
        wordTxt2= (TextView) view.findViewById(R.id.word2);
        wordTxt3= (TextView) view.findViewById(R.id.word3);
        word1Img= (ImageView) view.findViewById(R.id.word_image1);
        word2Img= (ImageView) view.findViewById(R.id.word_image2);
        word3Img= (ImageView) view.findViewById(R.id.word_image3);
        letter.setText(lettersModel.getLetter());
        letter.setTypeface(MainActivity.font);
        wordTxt1.setText(lettersModel.getWord1());
        wordTxt1.setTypeface(MainActivity.font);
        wordTxt2.setText(lettersModel.getWord2());
        wordTxt2.setTypeface(MainActivity.font);
        wordTxt3.setText(lettersModel.getWord3());
        wordTxt3.setTypeface(MainActivity.font);
     letter.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             getActivity().stopService(new Intent(getActivity(), OnlineServiceClass.class));
             Intent serviceIntent = new Intent(getActivity(), OnlineServiceClass.class);
             serviceIntent.putExtra("url",lettersModel.getLetterSound());
             getActivity().startService(serviceIntent);
         }
     });
        word1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Picasso.with(getActivity()).load(lettersModel.getPic1())
                .into(word1Img);
        Picasso.with(getActivity()).load(lettersModel.getPic2())
                .into(word2Img);
        Picasso.with(getActivity()).load(lettersModel.getPic3())
                .into(word3Img);

        return view;
    }
}
