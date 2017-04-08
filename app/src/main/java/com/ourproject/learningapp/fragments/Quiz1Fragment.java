package com.ourproject.learningapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.globals.GlobalLetter;
import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quiz1Fragment extends Fragment {
    private  String RandLetter,WordPic,ImageUrl,Tempword="";
    private String [] Letters,SplitedWord,arrChars ;
    private TextView Word,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10;
    private ImageView qimageView,Del ,DelOne;

    public Quiz1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Random r=new Random();
        final int Randnum=r.nextInt(28);
        final int Randlist=r.nextInt(3);
        int [] RandArr=new int[10];
        CreateRandArr(RandArr);
        Letters=getActivity().getResources().getStringArray(R.array.letters);
        if(Randlist==0){
        WordPic=getActivity().getResources().getStringArray(R.array.lettersName1)[Randnum];
        ImageUrl=getActivity().getResources().getStringArray(R.array.lettersImages1)[Randnum];
        }else if(Randlist==1){
            WordPic=getActivity().getResources().getStringArray(R.array.lettersName2)[Randnum];
            ImageUrl=getActivity().getResources().getStringArray(R.array.lettersImages2)[Randnum];
        }
        else {
            WordPic=getActivity().getResources().getStringArray(R.array.lettersName3)[Randnum];
            ImageUrl=getActivity().getResources().getStringArray(R.array.lettersImages3)[Randnum];
        }

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
        DelOne= (ImageView) view.findViewById(R.id.delOnlyOne);

        DelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char [] ch=Tempword.toCharArray();
                Word.setText("");Tempword="";
                for(int i=0;i<ch.length-1;i++){
                        Word.setText(Word.getText().toString().concat(String.valueOf(ch[i])));
                        Tempword=Tempword.concat(String.valueOf(ch[i])) ;

                }
            }
        });

        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word.setText("");Tempword="";
                SetToDefault(L1,L2,L3,L4,L5,L6,L7,L8,L9,L10);
            }
        });




        if(GlobalLetter.QUIZID.equals("qIamge1") || GlobalLetter.QUIZID.equals("qIamge2") ){
            qimageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Randlist==0)
                    GlobalLetter.pMusic(getActivity().getResources().getStringArray(R.array.PicSounds1)[Randnum]);
                    else if(Randlist==1)
                        GlobalLetter.pMusic(getActivity().getResources().getStringArray(R.array.PicSounds2)[Randnum]);
                    else
                        GlobalLetter.pMusic(getActivity().getResources().getStringArray(R.array.PicSounds3)[Randnum]);
                }
            });}


        if(GlobalLetter.QUIZID.equals("qIamge1") ||GlobalLetter.QUIZID.equals("qIamge3"))
        Picasso.with(getActivity()).load(ImageUrl).into(qimageView);
        else
            qimageView.setImageResource(R.drawable.funnyteacher);

         SplitedWord = WordPic.split("");
          arrChars  =new String[10];
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
            if(arrChars[i].equals("")) {
                do {
                    RandLetter = Letters[random(0, 27)];
                } while (IsFoundInStringArray(arrChars, RandLetter));
                arrChars[i] = RandLetter;
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

        if(Tempword.length() >WordPic.length()){
            WrongAnsAlert wrongAnsAlert =new WrongAnsAlert();
            wrongAnsAlert.show(getFragmentManager(),"Wrong Alert");
        }
        else {
        if (WordPic.equals(Tempword)) {

            RightAnsAlert rightAnsAlert =new RightAnsAlert();
            rightAnsAlert.show(getFragmentManager(),"Right Alert");
        }
        else {
            Tempword = Tempword.concat(view.getText().toString());
            Word.setText(Tempword);
            if (WordPic.equals(Tempword)) {

                RightAnsAlert rightAnsAlert =new RightAnsAlert();
                rightAnsAlert.show(getFragmentManager(),"Right Alert");
            }
        }
        }
    }

    public void SetToDefault(TextView ... textViews){
        for(TextView v:textViews)
            v.setBackgroundResource( R.drawable.img);
    }

    //-------------Pure java functions ---------------------//
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
