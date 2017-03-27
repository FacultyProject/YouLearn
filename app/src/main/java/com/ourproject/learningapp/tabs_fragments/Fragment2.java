package com.ourproject.learningapp.tabs_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.LettersActivity;
import com.ourproject.learningapp.activities.Quiz1Activity;

/**
 * Created by Moetaz on 2/24/2017.
 */
public class Fragment2 extends Fragment {
    ImageView QImaeg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag2,container,false);
        QImaeg = (ImageView) view.findViewById(R.id.qimage1);

        QImaeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Quiz1Activity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}
