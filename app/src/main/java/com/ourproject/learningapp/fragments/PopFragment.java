package com.ourproject.learningapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ourproject.learningapp.PositionRespone;
import com.ourproject.learningapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopFragment extends Fragment {
    PositionRespone positionRespone;
    public void setPositionRespone(PositionRespone positionRespone) {
        this.positionRespone = positionRespone;
    }
    ImageView imageView;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pop, container, false);
        final Q4Fragment q4Fragment=new Q4Fragment();
        imageView= (ImageView) view.findViewById(R.id.next);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionRespone.postitionPlus(q4Fragment.position++);
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        return view;
    }

}