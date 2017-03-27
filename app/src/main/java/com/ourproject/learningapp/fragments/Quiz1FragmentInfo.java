package com.ourproject.learningapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.PopActivity;
import com.ourproject.learningapp.models.MadModel;
import com.ourproject.learningapp.models.QuizModel1;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quiz1FragmentInfo extends Fragment {
      String WordPic;
    TextView Word,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10;
    ImageView qimageView;
    public String Tempword="";
    ImageView Del;

    QuizModel1 quizModel1;
    public Quiz1FragmentInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        quizModel1 =(QuizModel1) bundle.getSerializable("qlist");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_quiz1_fragment_info, container, false);
        qimageView= (ImageView) view.findViewById(R.id.qimage);
        Word= (TextView) view.findViewById(R.id.word);
        L1= (TextView) view.findViewById(R.id.l1);
        L2= (TextView) view.findViewById(R.id.l2);
        L3= (TextView) view.findViewById(R.id.l3);
        L4= (TextView) view.findViewById(R.id.l4);
        L5= (TextView) view.findViewById(R.id.l5);
        L6= (TextView) view.findViewById(R.id.l6);
        L7= (TextView) view.findViewById(R.id.l7);
        L8= (TextView) view.findViewById(R.id.l8);
        L9= (TextView) view.findViewById(R.id.l9);
        L10= (TextView) view.findViewById(R.id.l10);
        Del= (ImageView) view.findViewById(R.id.del);

        Picasso.with(getActivity()).load(quizModel1.getPicUrl()).into(qimageView);
           WordPic=quizModel1.getPicWord();
        L1.setText(quizModel1.getLetter1());
        L2.setText(quizModel1.getLetter1());
        L3.setText(quizModel1.getLetter1());
        L4.setText(quizModel1.getLetter1());
        L5.setText(quizModel1.getLetter1());
        L6.setText(quizModel1.getLetter1());
        L7.setText(quizModel1.getLetter1());
        L8.setText(quizModel1.getLetter1());
        L9.setText(quizModel1.getLetter1());
        L10.setText(quizModel1.getLetter1());

        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word.setText("");Tempword="";
            }
        });

        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L1);
            }
        });
        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L2);
            }
        });
        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L3);
            }
        });
        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L4);

            }
        });
        L5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L5);

            }
        });
        L6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L6);

            }
        });
        L7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L7);

            }
        });
        L8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L8);
            }
        });

        L9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L8);
            }
        });

        L10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L8);
            }
        });

        return view;
    }

    public void ButtonListner(TextView view){
        if (WordPic.equals(Tempword)) {
            startActivity(new Intent(getActivity(),PopActivity.class));
        }
        else {
            Tempword = Tempword.concat(view.getText().toString());
            Word.setText(Tempword);
            if (WordPic.equals(Tempword)) {
                startActivity(new Intent(getActivity(),PopActivity.class));
            }
        }
    }

}
