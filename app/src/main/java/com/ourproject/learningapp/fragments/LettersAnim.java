package com.ourproject.learningapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalVariables;

/**
 * Created by Mohamed Ali on 5/2/2017.
 */
public class LettersAnim extends Fragment {
    private ImageView gif,playSound;
    private String[]lettersSounds;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letter_drawing, container, false);
        lettersSounds=getActivity().getResources().getStringArray(R.array.lettersSounds);
        gif= (ImageView) view.findViewById(R.id.gif);
        playSound= (ImageView) view.findViewById(R.id.play);
        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariables.pMusic(lettersSounds[Integer.parseInt(GlobalVariables.ainmLetterPosition)-1],getActivity());
            }
        });
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gif);
        Glide.with(this).load("file:///android_asset/Animation/a"+ GlobalVariables.ainmLetterPosition+".gif").into(imageViewTarget);

        return view;

    }
}
