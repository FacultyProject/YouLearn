package com.ourproject.learningapp.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.globals.GlobalLetter;
import com.ourproject.learningapp.models.LettersModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mohamed Ali on 3/14/2017.
 */
public class LetterFragment extends Fragment implements View.OnClickListener {
    TextView wordTxt1,wordTxt2,wordTxt3,letter;
    ImageView word1Img,word2Img,word3Img;
    LettersModel lettersModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        lettersModel=(LettersModel) bundle.getSerializable("wordslist");

    }
    private void toZoomFrag(String image,String text,String sound){
        ZoomFragment zoomFragment=new ZoomFragment();
        Bundle bundle=new Bundle();
        bundle.putString("image",image);
        bundle.putString("text",text);
        bundle.putString("sound",sound);
        zoomFragment.setArguments(bundle);
        ((FragmentActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.letter_fragment,zoomFragment)
                .addToBackStack(null)
                .commit();
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
        colorChar(wordTxt1,lettersModel.getLetter(),lettersModel.getWord1());
        colorChar(wordTxt2,lettersModel.getLetter(),lettersModel.getWord2());
        colorChar(wordTxt3,lettersModel.getLetter(),lettersModel.getWord3());

        letter.setOnClickListener(this);
        wordTxt1.setOnClickListener(this);
        wordTxt2.setOnClickListener(this);
        wordTxt3.setOnClickListener(this);
        word1Img.setOnClickListener(this);
        word2Img.setOnClickListener(this);
        word3Img.setOnClickListener(this);

        Picasso.with(getActivity()).load(lettersModel.getPic1())
                .into(word1Img);
        Picasso.with(getActivity()).load(lettersModel.getPic2())
                .into(word2Img);
        Picasso.with(getActivity()).load(lettersModel.getPic3())
                .into(word3Img);

        return view;
    }
    public void colorChar(TextView textView,String ch,String word){
        if (ch.contains("ه"))
            ch="ه";
        else if (ch.contains("أ")&&word.contains("ء"))
            ch="ء";
        String ayeTemp = word;

        ArrayList<Integer> positionInt = new ArrayList<>();
        for (int i = 0; i < ayeTemp.length(); i++) {
            if (ayeTemp.contains(ch)) {
                if (positionInt.size() == 0) {
                    positionInt.add(ayeTemp.indexOf(ch));
            }
        }}
        Spannable wordtoSpan = new SpannableString(word);
        for (int i = 0; i < positionInt.size(); i++) {
            wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), positionInt.get(i), positionInt.get(i) + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(wordtoSpan);
        textView.setTypeface(MainActivity.font);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.letter:
                GlobalLetter.pMusic(lettersModel.getLetterSound());
                break;
            case R.id.word1:
                GlobalLetter.pMusic(lettersModel.getPicSounds1());

                break;
            case R.id.word2:
                GlobalLetter.pMusic(lettersModel.getPicSounds2());

                break;
            case R.id.word3:
                GlobalLetter.pMusic(lettersModel.getPicSounds3());

                break;
            case R.id.word_image1:
                toZoomFrag(lettersModel.getPic1(),lettersModel.getWord1(),lettersModel.getPicSounds1());
                GlobalLetter.pMusic(lettersModel.getPicSounds1());

                break;
            case R.id.word_image2:
                toZoomFrag(lettersModel.getPic2(),lettersModel.getWord2(),lettersModel.getPicSounds2());
                GlobalLetter.pMusic(lettersModel.getPicSounds2());

                break;
            case R.id.word_image3:
                toZoomFrag(lettersModel.getPic3(),lettersModel.getWord3(),lettersModel.getPicSounds3());
                GlobalLetter.pMusic(lettersModel.getPicSounds3());

                break;
        }
    }
}
