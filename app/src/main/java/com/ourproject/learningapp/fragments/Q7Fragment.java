package com.ourproject.learningapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalVariables;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Q7Fragment extends Fragment {
    public int check ;
    ArrayList<Integer> numList = new ArrayList<>();
    ArrayList<String> strList =new ArrayList<>();
    String [] DamSounds ,FathSounds ,KasrSounds ,DamLetters ,FathLetters,KasrLetters;
    ImageView imageView;
    TextView textView1,textView2,textView3;
    int color1 ,color2;
    int RandGroup,RandLetter;
    String RightLetter;
    public Q7Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RandGroup = new Random().nextInt(3);
        RandLetter = new Random().nextInt(28);
        for(int i=0 ;i<3;i++){
            strList.add("null");
        }


        DamLetters = getActivity().getResources().getStringArray(R.array.HorofMadmoma);
        FathLetters = getActivity().getResources().getStringArray(R.array.HorofMaftoha);
        KasrLetters = getActivity().getResources().getStringArray(R.array.HorofMaksora);

        getRandList();
        GetRightLetter ();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_q7, container, false);
        imageView = (ImageView) view.findViewById(R.id.soundofword);
        textView1 = (TextView) view.findViewById(R.id.text1);
        textView2 = (TextView) view.findViewById(R.id.text2);
        textView3 = (TextView) view.findViewById(R.id.text3);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RandGroup == 0)
                    GlobalVariables.pMusic(getActivity().getResources().getStringArray(R.array.damsound)[RandLetter],getActivity());
                else if(RandGroup == 1)
                    GlobalVariables.pMusic(getActivity().getResources().getStringArray(R.array.fathsound)[RandLetter],getActivity());
                else
                    GlobalVariables.pMusic(getActivity().getResources().getStringArray(R.array.kasrsound)[RandLetter],getActivity());
            }
        });

        textView1.setText(strList.get(0));
        textView2.setText(strList.get(1));
        textView3.setText(strList.get(2));
        CheckForNull(textView1,textView2,textView3);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RightLetter.equals(textView1.getText()))
                {
                    GlobalVariables.nOfRightAns++;
                    GlobalVariables.TAG="Q7Fragment";
                    goTOalert();
                }else
                {
                    WrongAnsAlert wrongAnsAlert = new WrongAnsAlert();
                    wrongAnsAlert.show(getFragmentManager(), "qAlert");
                }
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RightLetter.equals(textView2.getText()))
                {
                    GlobalVariables.nOfRightAns++;
                    GlobalVariables.TAG="Q7Fragment";
                    goTOalert();
                }else
                {
                    WrongAnsAlert wrongAnsAlert = new WrongAnsAlert();
                    wrongAnsAlert.show(getFragmentManager(), "qAlert");
                }

            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(RightLetter.equals(textView3.getText()))
                {
                    GlobalVariables.nOfRightAns++;
                    GlobalVariables.TAG="Q7Fragment";
                    goTOalert();
                }else
                {
                    WrongAnsAlert wrongAnsAlert = new WrongAnsAlert();
                    wrongAnsAlert.show(getFragmentManager(), "qAlert");
                }

            }
        });
        return view;
    }



    public void goTOalert(){

            RightAnsAlert rightAnsAlert = new RightAnsAlert();
            rightAnsAlert.show(getFragmentManager(), "qAlert");



    }

    public void getRandList(){


        Random rand =new Random();
        int randnum;
        for(int i=0;i<3;i++) {
            do {
                randnum = rand.nextInt(3);
            } while (numList.contains(randnum));
            numList.add(randnum);

            if(i == 0) {
                strList.add(randnum, DamLetters[RandLetter]);
                if(strList.get(randnum).equals("null"))
                    check = 0;
            }
            else if(i == 1) {
                strList.add(randnum, FathLetters[RandLetter]);
                if(strList.get(randnum).equals("null"))
                    check = 1;
            }
            else {
                strList.add(randnum, KasrLetters[RandLetter]);
                if(strList.get(randnum).equals("null"))
                    check = 2;
            }
        }


    }

    public void GetRightLetter (){
        if(RandGroup == 0)
            RightLetter = DamLetters[RandLetter];
        else if(RandGroup == 1)
            RightLetter = FathLetters[RandLetter];
        else
            RightLetter = KasrLetters[RandLetter];
    }

    public void CheckForNull (TextView ... textViews){
        for(TextView textView: textViews){
            if (textView.getText().equals("null")){
                if(check == 0)
                    textView.setText(DamLetters[RandLetter]);
                else if(check == 1)
                    textView.setText(FathLetters[RandLetter]);
                else if(check == 2)
                    textView.setText(KasrLetters[RandLetter]);
            }
        }

        if(textViews[0].getText().equals(textViews[1].getText()))
            textViews[0].setText(FathLetters[RandLetter]);
        else if(textViews[0].getText().equals(textViews[2].getText()))
            textViews[0].setText(FathLetters[RandLetter]);
        else if (textViews[1].getText().equals(textViews[2].getText()))
            textViews[1].setText(FathLetters[RandLetter]);

    }
}
