package com.ourproject.learningapp.fragments;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.models.LettersModel;

import java.io.IOException;

/**
 * Created by Mohamed Ali on 3/14/2017.
 */
public class LetterFragment extends Fragment {
    TextView wordTxt1,wordTxt2,wordTxt3,letter;
    LettersModel lettersModel;
    MediaPlayer mediaPlayer;
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
        letter.setText(lettersModel.getLetter());
        letter.setTypeface(MainActivity.font);
        wordTxt1.setText(lettersModel.getWord1());
        wordTxt1.setTypeface(MainActivity.font);
        wordTxt2.setText(lettersModel.getWord2());
        wordTxt2.setTypeface(MainActivity.font);
        wordTxt3.setText(lettersModel.getWord3());
        wordTxt3.setTypeface(MainActivity.font);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("http://server10.mp3quran.net/minsh/001.mp3");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
}
