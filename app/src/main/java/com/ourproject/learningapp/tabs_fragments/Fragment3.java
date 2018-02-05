package com.ourproject.learningapp.tabs_fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.CompetionsResultActivity;
import com.ourproject.learningapp.activities.SelfTestActivity;
import com.ourproject.learningapp.globals.GlobalVariables;


/**
 * Created by Moetaz on 2/27/2017.
 */
public class Fragment3 extends Fragment {

    private CardView SelfTestCard ,cardView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag3, container, false);
        SelfTestCard = (CardView) view.findViewById(R.id.selftest);
        cardView2 = (CardView) view.findViewById(R.id.card2);


        SelfTestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GlobalVariables.G1.clear();
                GlobalVariables.G2.clear();
                GlobalVariables.G3.clear();
                startActivity(new Intent(getActivity(), SelfTestActivity.class));
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),CompetionsResultActivity.class));
            }
        });


            return view;
    }
}
