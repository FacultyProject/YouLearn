package com.ourproject.learningapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.Myalert;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalLetter;
import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quiz1Fragment extends Fragment {
    String RandLetter;
    String WordPic;
    String [] Letters;
    TextView Word,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10;
    ImageView qimageView;
    public String Tempword="";
    ImageView Del ,DelOne;

    public Quiz1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_quiz1, container, false);
        qimageView= (ImageView) view.findViewById(R.id.qimage);
        DelOne= (ImageView) view.findViewById(R.id.delOnlyOne);
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

        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word.setText("");Tempword="";
                SetToDefault(L1,L2,L3,L4,L5,L6,L7,L8,L9,L10);
            }
        });

        Random r=new Random();
        final int Randnum=r.nextInt(28);
         String ImageUrl=getActivity().getResources().getStringArray(R.array.lettersImages1)[Randnum];

        if(GlobalLetter.QUIZID.equals("qIamge1") || GlobalLetter.QUIZID.equals("qIamge2") ){
            qimageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GlobalLetter.pMusic(getActivity().getResources().getStringArray(R.array.PicSounds1)[Randnum]);
                }
            });}


        int [] RandArr=new int[10];
        CreateRandArr(RandArr);
        Letters=getActivity().getResources().getStringArray(R.array.letters);
        WordPic=getActivity().getResources().getStringArray(R.array.lettersName1)[Randnum];

        if(GlobalLetter.QUIZID.equals("qIamge1") ||GlobalLetter.QUIZID.equals("qIamge3"))
        Picasso.with(getActivity()).load(ImageUrl).into(qimageView);
        else
            qimageView.setImageResource(R.drawable.funnyteacher);

        String[] SplitedWord = WordPic.split("");
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

        if(!WordPic.contains(view.getText()))
            view.setBackgroundResource( R.drawable.wrong_circle);
        else
            view.setBackgroundResource( R.drawable.right_circle);

        if (WordPic.equals(Tempword)) {
            //startActivity(new Intent(getActivity(),PopActivity.class));
            Myalert myalert=new Myalert();
            myalert.show(getFragmentManager(),"My Alert");
        }
        else {
            Tempword = Tempword.concat(view.getText().toString());
            Word.setText(Tempword);
            if (WordPic.equals(Tempword)) {
                //startActivity(new Intent(getActivity(),PopActivity.class));
                Myalert myalert=new Myalert();
                myalert.show(getFragmentManager(),"My Alert");
            }
        }
    }

    public void SetToDefault(TextView ... textViews){
        for(TextView v:textViews)
            v.setBackgroundResource( R.drawable.img);
    }

    /////////////////////////////////////////////////////
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
