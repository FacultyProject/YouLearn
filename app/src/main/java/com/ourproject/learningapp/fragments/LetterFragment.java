package com.ourproject.learningapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalVariables;
import com.ourproject.learningapp.models.LettersModel;


/**
 * Created by Mohamed Ali on 3/14/2017.
 */
public class LetterFragment extends Fragment implements View.OnClickListener {
    public static final String fTAG = LetterFragment.class.getSimpleName();
    private TextView wordTxt1, wordTxt2, wordTxt3, letter, letterF, letterD, letterK;
    private ImageView word1Img, word2Img, word3Img, play;
    private  LettersModel lettersModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        lettersModel = (LettersModel) bundle.getSerializable("wordslist");
        GlobalVariables.pMusic(lettersModel.getLetterSound(), getActivity());

    }

    private void toZoomFrag(ImageView view1, View view2, String image, String text, String sound, String letter) {
        ZoomFragment zoomFragment = ZoomFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("image", image);
        bundle.putString("text", text);
        bundle.putString("sound", sound);
        bundle.putString("letter", letter);
        zoomFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .addSharedElement(view1, ViewCompat.getTransitionName(view1))
                .addSharedElement(view2, ViewCompat.getTransitionName(view2))
                .addSharedElement(play, ViewCompat.getTransitionName(play))
                .addToBackStack(fTAG)
                .replace(R.id.fmain2, zoomFragment)
                .commit();


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (GlobalVariables.LETTERTYPE.equals("NORMAL_MOVEMENT")) {
            LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.shortmov_layout);
            myLayout.setVisibility(View.GONE);
        }
        letter = (TextView) view.findViewById(R.id.letter);
        play = (ImageView) view.findViewById(R.id.play);
        wordTxt1 = (TextView) view.findViewById(R.id.word1);
        wordTxt2 = (TextView) view.findViewById(R.id.word2);
        wordTxt3 = (TextView) view.findViewById(R.id.word3);
        word1Img = (ImageView) view.findViewById(R.id.word_image1);
        word2Img = (ImageView) view.findViewById(R.id.word_image2);
        word3Img = (ImageView) view.findViewById(R.id.word_image3);
        letterD = (TextView) view.findViewById(R.id.letter_madmom);
        letterF = (TextView) view.findViewById(R.id.letter_maftoh);
        letterK = (TextView) view.findViewById(R.id.letter_maksor);

        letter.setText(lettersModel.getLetter());
        letterF.setText(lettersModel.getLetter() + "َ");
        letterD.setText(lettersModel.getLetter() + "ُ");
        letterK.setText(lettersModel.getLetter() + "ِ");

        GlobalVariables.colorChar(wordTxt1, lettersModel.getLetter(), lettersModel.getWord1());
        GlobalVariables.colorChar(wordTxt2, lettersModel.getLetter(), lettersModel.getWord2());
        GlobalVariables.colorChar(wordTxt3, lettersModel.getLetter(), lettersModel.getWord3());

        play.setOnClickListener(this);
        wordTxt1.setOnClickListener(this);
        wordTxt2.setOnClickListener(this);
        wordTxt3.setOnClickListener(this);
        word1Img.setOnClickListener(this);
        word2Img.setOnClickListener(this);
        word3Img.setOnClickListener(this);

        GlobalVariables.showPics(lettersModel.getPic1(), getActivity(), word1Img);
        GlobalVariables.showPics(lettersModel.getPic2(), getActivity(), word2Img);
        GlobalVariables.showPics(lettersModel.getPic3(), getActivity(), word3Img);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_letter, container, false);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                GlobalVariables.pMusic(lettersModel.getLetterSound(), getActivity());
                break;
            case R.id.word1:
                GlobalVariables.pMusic(lettersModel.getPicSounds1(), getActivity());

                break;
            case R.id.word2:
                GlobalVariables.pMusic(lettersModel.getPicSounds2(), getActivity());

                break;
            case R.id.word3:
                GlobalVariables.pMusic(lettersModel.getPicSounds3(), getActivity());

                break;
            case R.id.word_image1:
                toZoomFrag(word1Img, wordTxt1, lettersModel.getPic1(), lettersModel.getWord1(), lettersModel.getPicSounds1(), lettersModel.getLetter());
                GlobalVariables.pMusic(lettersModel.getPicSounds1(), getActivity());

                break;
            case R.id.word_image2:
                toZoomFrag(word2Img, wordTxt2, lettersModel.getPic2(), lettersModel.getWord2(), lettersModel.getPicSounds2(), lettersModel.getLetter());
                GlobalVariables.pMusic(lettersModel.getPicSounds2(), getActivity());

                break;
            case R.id.word_image3:
                toZoomFrag(word3Img, wordTxt3, lettersModel.getPic3(), lettersModel.getWord3(), lettersModel.getPicSounds3(), lettersModel.getLetter());
                GlobalVariables.pMusic(lettersModel.getPicSounds3(), getActivity());

                break;
        }
    }
}
