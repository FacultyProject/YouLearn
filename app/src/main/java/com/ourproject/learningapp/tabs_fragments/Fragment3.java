package com.ourproject.learningapp.tabs_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.SelfTestActivity;

/**
 * Created by Moetaz on 2/27/2017.
 */
public class Fragment3 extends Fragment {
    Button button;
    CardView SelfTestCard;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag3,container,false);
        SelfTestCard= (CardView) view.findViewById(R.id.selftest);
        SelfTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SelfTestActivity.class));
            }
        });


        return view;
    }
}
