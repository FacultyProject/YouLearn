package com.ourproject.learningapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopFragment extends Fragment {
    Bundle bundle;
    TextView word,textView;
    ImageView imageView;
    PositionRespone positionRespone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getArguments();
    }

    public void setPositionRespone(PositionRespone positionRespone) {
        this.positionRespone = positionRespone;
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pop, container, false);
        final Q4Fragment q4Fragment =new Q4Fragment();
        imageView= (ImageView) view.findViewById(R.id.next);
        textView= (TextView) view.findViewById(R.id.textView);
        word= (TextView) view.findViewById(R.id.word);
        word.setText("("+bundle.get("text").toString()+")");
        if (Q4Fragment.position==28){
            textView.setTextSize(30);
            textView.setText(" اكتمل الاختبار");
            imageView.setVisibility(view.GONE);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }else if (Q4Fragment.ans=="true"){
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    positionRespone.postitionPlus(q4Fragment.position++);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });}
        else if (Q4Fragment.ans=="false"){
            textView.setText("حاول مرة اخري!");
            textView.setTextSize(30);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });
            imageView.setVisibility(view.GONE);
        }
        return view;
    }
}