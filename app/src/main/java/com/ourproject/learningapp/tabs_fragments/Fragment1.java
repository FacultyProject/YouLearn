package com.ourproject.learningapp.tabs_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.activities.LettersActivity;
import com.ourproject.learningapp.activities.MadLettersActivity;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.services.ServiceClass;

/**
 * Created by Moetaz on 2/24/2017.
 */
public class Fragment1 extends Fragment {
    //ImageView buPlay, buStop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag1, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.arabic_letters_img);
        ImageView madImage= (ImageView) view.findViewById(R.id.mamdod_img);

        //TextView textView = (TextView) view.findViewById(R.id.text);


        //textView.setTypeface(MainActivity.font);
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
                getActivity().stopService(new Intent(getActivity(), ServiceClass.class));

                Intent intent = new Intent(getActivity(), LettersActivity.class);
                getActivity().startActivity(intent);

                Intent serviceIntent = new Intent(getActivity(), ServiceClass.class);
                serviceIntent.putExtra("id", R.raw.mini_nasheed);
                getActivity().startService(serviceIntent);
            }
        });

        /**buPlay = (ImageView) view.findViewById(R.id.play);
        buPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().stopService(new Intent(getActivity(), ServiceClass.class));
                Intent serviceIntent = new Intent(getActivity(), ServiceClass.class);
                serviceIntent.putExtra("id", R.raw.full_nasheed);
                getActivity().startService(serviceIntent);
            }
        });

        buStop = (ImageView) view.findViewById(R.id.stop);
        buStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().stopService(new Intent(getActivity(), ServiceClass.class));
            }
        });**/
        return view;
    }
}
