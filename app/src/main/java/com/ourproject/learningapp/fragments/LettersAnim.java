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
    ImageView gif;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letter_animation, container, false);
        gif= (ImageView) view.findViewById(R.id.gif);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gif);
        Glide.with(this).load("file:///android_asset/Animation/a"+ GlobalVariables.ainmLetterPosition+".gif").into(imageViewTarget);

        return view;

    }
}
