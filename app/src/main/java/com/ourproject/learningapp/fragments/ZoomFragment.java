package com.ourproject.learningapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.globals.GlobalLetter;
import com.squareup.picasso.Picasso;

/**
 * Created by Moetaz on 3/23/2017.
 */

public class ZoomFragment extends Fragment {
    ImageView imageView;
    TextView textView;
    Bundle bundle;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_zoom, container, false);
        imageView= (ImageView) view.findViewById(R.id.image);
        Picasso.with(getActivity()).load( bundle.get("image").toString()).into(imageView);
        textView=(TextView)view.findViewById(R.id.text);
        textView.setText(bundle.get("text").toString());
        textView.setTypeface(MainActivity.font);
        GlobalLetter.colorChar(textView,bundle.get("letter").toString(),bundle.get("text").toString());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalLetter.pMusic(bundle.getString("sound").toString(),getActivity());
            }
        });
        return view;
    }
}
