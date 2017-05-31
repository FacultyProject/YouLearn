package com.ourproject.learningapp.tabs_fragments;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ourproject.learningapp.activities.LettersActivity;
import com.ourproject.learningapp.activities.MadLettersActivity;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalVariables;
import com.ourproject.learningapp.services.ServiceClass;

/**
 * Created by Moetaz on 2/24/2017.
 */
public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag1, container, false);


        ImageView imageView = (ImageView) view.findViewById(R.id.arabic_letters_img);
        ImageView madImage = (ImageView) view.findViewById(R.id.mamdod_img);
        ImageView Shortmov = (ImageView) view.findViewById(R.id.shortmov);
        ImageView letterAnim = (ImageView) view.findViewById(R.id.letters_animation);


        Shortmov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariables.LETTERTYPE = "ShortMovement";
                Intent intent = new Intent(getActivity(), LettersActivity.class);
                getActivity().startActivity(intent);
            }
        });

        madImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MadLettersActivity.class);
                getActivity().startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariables.LETTERTYPE = "NORMAL_MOVEMENT";

                getActivity().stopService(new Intent(getActivity(), ServiceClass.class));

                Intent intent = new Intent(getActivity(), LettersActivity.class);
                getActivity().startActivity(intent);

                Intent serviceIntent = new Intent(getActivity(), ServiceClass.class);
                serviceIntent.putExtra("id", R.raw.mini_nasheed);
                getActivity().startService(serviceIntent);
            }
        });
        letterAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariables.LETTERTYPE = "LETTER_ANIMATION";
                Intent intent = new Intent(getActivity(), LettersActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
