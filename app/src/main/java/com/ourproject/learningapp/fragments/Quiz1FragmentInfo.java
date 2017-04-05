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
import com.ourproject.learningapp.globals.GlobalLetter;
import com.ourproject.learningapp.models.MadModel;
import com.ourproject.learningapp.models.QuizModel1;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quiz1FragmentInfo extends Fragment {
    String RandLetter;
    String WordPic;
    String [] Letters;
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

        if(GlobalLetter.QUIZID.equals("qIamge1") ||GlobalLetter.QUIZID.equals("qIamge2") ){
            qimageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GlobalLetter.pMusic(quizModel1.getSoundUrl());
                }
            });}

        if(GlobalLetter.QUIZID.equals("qIamge1") ||GlobalLetter.QUIZID.equals("qIamge3"))
            Picasso.with(getActivity()).load(quizModel1.getPicUrl())
                    .into(qimageView);
        else
            qimageView.setImageResource(R.drawable.funnyteacher);
        Letters=getActivity().getResources().getStringArray(R.array.letters);
        int [] RandArr=new int[10];
        CreateRandArr(RandArr);

        WordPic=quizModel1.getPicWord();
        String[] SplitedWord = quizModel1.getPicWord().split("");
        String arrChars[] =new String[10];
        for(int i=0;i<SplitedWord.length;i++){
            AddToStringArray(arrChars, SplitedWord[i]);
        }
        for(int i=0;i<10-(SplitedWord.length-1) ;i++){
            do{
                RandLetter=Letters[random(0,27)];
            }while(IsFoundInStringArray(arrChars,RandLetter));
            AddToStringArray(arrChars, RandLetter);
        }
        for(int i=0;i<arrChars.length  ;i++){
            if(arrChars[i].equals("")){
                arrChars[i]=Letters[random(0,27)];
            }
        }

        L1.setText(arrChars[RandArr[0]]);
        L2.setText(arrChars[RandArr[1]]);
        L3.setText(arrChars[RandArr[2]]);
        L4.setText(arrChars[RandArr[3]]);
        L5.setText(arrChars[RandArr[4]]);
        L6.setText(arrChars[RandArr[5]]);
        L7.setText(arrChars[RandArr[6]]);
        L8.setText(arrChars[RandArr[7]]);
        L9.setText(arrChars[RandArr[8]]);
        L10.setText(arrChars[RandArr[9]]);

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
                ButtonListner(L9);
            }
        });
        L10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonListner(L10);
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



    public  void AddToStringArray(String [] arr,String s){
        for(int i=0;i<arr.length;i++){
            if(arr[i] == null){
                arr[i]=s;
                break;
            }
        }
    }



    public  boolean IsFoundInStringArray(String [] arr,String s){
        for(int i=0;i<arr.length;i++){
            if(arr[i] == null ? s == null : arr[i].equals(s))
                return true;
        }
        return false;
    }

    public  int random(int a,int b){
        b++;
        return (int) (a+((b-a)*Math.random()));
    }
    public   void addtoarray(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                arr[i] = num;
                break;
            }
        }
    }

    public   boolean IsFoundInArray(int [] arr,int num){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==num)
                return true;
        }
        return false;
    }
    public   void NegativeArray(int []arr){
        for(int i=0;i<arr.length;i++)
            arr[i]=-1;
    }

    public void CreateRandArr(int [] arr){
        NegativeArray(arr);
        int num;
        for(int i=0;i<arr.length;i++) {
            do {
                num = random(0, 9);
            } while (IsFoundInArray(arr, num));
            addtoarray(arr, num);
        }
    }

}
