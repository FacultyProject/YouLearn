package com.ourproject.learningapp.fragments;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.SelfTestActivity;
import com.ourproject.learningapp.globals.GlobalVariables;
import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quiz1Fragment extends Fragment {
    public static final int TAEGET=4;
    private  String RandLetter,WordPic,ImageUrl,Tempword="";
    private String [] Letters,SplitedWord,arrChars ;
    private TextView Word,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10;
    private ImageView qimageView,Del ,DelOne,play;
    private ArrayList<TextView> aTextview = new ArrayList<>();
    private int Randnum ;
    final int Randlist=new Random().nextInt(3);
    int [] RandArr;

    public Quiz1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

           RandArr=new int[10];
        CreateRandArr(RandArr);
        Letters=getActivity().getResources().getStringArray(R.array.letters);

        if(Randlist==0){
            do{
                Randnum=new Random().nextInt(28);
            }while (GlobalVariables.G1.contains(Randnum));
            GlobalVariables.G1.add(Randnum);
            WordPic=getActivity().getResources().getStringArray(R.array.lettersName1)[Randnum];
            ImageUrl=getActivity().getResources().getStringArray(R.array.lettersImages1)[Randnum];
        }else if(Randlist==1){
            do{
                Randnum=new Random().nextInt(28);
            }while (GlobalVariables.G2.contains(Randnum));
            GlobalVariables.G2.add(Randnum);

            WordPic=getActivity().getResources().getStringArray(R.array.lettersName2)[Randnum];
            ImageUrl=getActivity().getResources().getStringArray(R.array.lettersImages2)[Randnum];
        }
        else {
            do{
                Randnum=new Random().nextInt(28);
            }while (GlobalVariables.G3.contains(Randnum));
            GlobalVariables.G3.add(Randnum);

            WordPic=getActivity().getResources().getStringArray(R.array.lettersName3)[Randnum];
            ImageUrl=getActivity().getResources().getStringArray(R.array.lettersImages3)[Randnum];
        }

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
        DelOne= (ImageView) view.findViewById(R.id.delOnlyOne);
        play=(ImageView) view.findViewById(R.id.play);
        play.setVisibility(View.GONE);
        LettersAnimation(L1,L2,L3,L4,L5,L6,L7,L8,L9,L10);

        DelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char [] ch=Tempword.toCharArray();
                Word.setText("");Tempword="";
                for(int i=0;i<ch.length-1;i++){
                        Word.setText(Word.getText().toString().concat(String.valueOf(ch[i])));
                        Tempword=Tempword.concat(String.valueOf(ch[i])) ;

                }

                if(!aTextview.isEmpty()) {
                    SetToDefault(aTextview.get(aTextview.size() - 1));
                    aTextview.remove(aTextview.size() - 1);
                }
            }
        });

        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word.setText("");Tempword="";
                while(!aTextview.isEmpty()) {
                    SetToDefault(aTextview.get(aTextview.size()-1));
                    aTextview.remove(aTextview.size()-1);
                }
            }
        });


        if(GlobalVariables.QUIZID.equals("qIamge1") || GlobalVariables.QUIZID.equals("qIamge2") ){
            play.setVisibility(View.VISIBLE);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Randlist==0)
                    GlobalVariables.pMusic(getActivity().getResources().getStringArray(R.array.PicSounds1)[Randnum],getActivity());
                    else if(Randlist==1)
                        GlobalVariables.pMusic(getActivity().getResources().getStringArray(R.array.PicSounds2)[Randnum],getActivity());
                    else
                        GlobalVariables.pMusic(getActivity().getResources().getStringArray(R.array.PicSounds3)[Randnum],getActivity());
                }
            });}


        if(GlobalVariables.QUIZID.equals("qIamge1") || GlobalVariables.QUIZID.equals("qIamge3"))
        //Picasso.with(getActivity()).load(ImageUrl).into(qimageView);
        GlobalVariables.showPics(ImageUrl,getActivity(),qimageView);
        else
            qimageView.setImageResource(R.drawable.funnyteacher);

         SplitedWord = WordPic.split("");
          arrChars  =new String[10];
        for(int i=0;i<SplitedWord.length;i++){
            AddToStringArray(arrChars, SplitedWord[i]);
        }
        for(int i=0;i<10-(SplitedWord.length- 1) ;i++){
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

           aTextview.add(view);
        if(Tempword.length() <= WordPic.length())
        LettersColor(view);


        if(Tempword.length() > WordPic.length()) {

            Word.setText(""); Tempword = "";
            SetToDefault(L1, L2, L3, L4, L5, L6, L7, L8, L9, L10);

            if (GlobalVariables.SelfTestMode == true) {
                GlobalVariables.rAnswer=false;
                GoToSelfTestAlert();

            } else{
                WrongAnsAlert wrongAnsAlert = new WrongAnsAlert();
                wrongAnsAlert.setCancelable(false);
                wrongAnsAlert.show(getFragmentManager(), "Wrong Alert");
        }
        }

        else {
        if (WordPic.equals(Tempword)) {

            if(GlobalVariables.SelfTestMode == true){
                GlobalVariables.rAnswer=true;
                GlobalVariables.scr++;

                GoToSelfTestAlert();

            }else
            GoToAlert();
        }
        else {
            Tempword = Tempword.concat(view.getText().toString());
            Word.setText(Tempword);
            if (WordPic.equals(Tempword))
            {

                if(GlobalVariables.SelfTestMode == true){
                    GlobalVariables.rAnswer=true;
                    GlobalVariables.scr++;

                    GoToSelfTestAlert();

                }else
                GoToAlert();
            }
        }
        }
    }

    public void LettersColor(TextView view){

        if(!WordPic.contains(view.getText())  ) {

                CircularAnimation(view,R.drawable.wrong_circle);
        }
        else{
            CircularAnimation(view,R.drawable.right_circle);
        }
    }
    public void LettersAnimation(View ... views){
        for(View v:views){
            ObjectAnimator a1= ObjectAnimator.ofFloat(v,"translationY",-600,0);
            a1.setDuration(2300);
            AnimatorSet animatorSet =new AnimatorSet();
            animatorSet.playTogether(a1);
            animatorSet.start();
        }
    }

    public void SetToDefault(TextView ... textViews){
        for(TextView v:textViews) {
            CircularAnimation(v,R.drawable.img);
        }


    }

    public void CircularAnimation(View v,int color){
        int finalRad = (int) Math.hypot(v.getWidth()/2,v.getHeight()/2);
        if (Build.VERSION.SDK_INT >= 21) {
            Animator animator = ViewAnimationUtils.createCircularReveal(
                    v,
                    (int) v.getWidth() / 2,
                    (int) v.getHeight() / 2, 0,
                    finalRad);
            v.setBackgroundResource(color);

            animator.start();
        } else
            v.setBackgroundResource(color);
    }

    public void GoToSelfTestAlert(){
        SelfTestActivity.TimerIsRunning=false;
        SelfTestAlert selfTestAlert=new SelfTestAlert();
        selfTestAlert.setCancelable(false);
        selfTestAlert.show(getFragmentManager(),"Salert");
    }
    public void GoToAlert(){
        GlobalVariables.nOfRightAns++;
        if(GlobalVariables.nOfRightAns <  TAEGET) {
            GoToRightAlert();
        }
        else
        {
            GlobalVariables.QuizCompleted=true;
            GoToRightAlert();
        }
    }

    public void GoToRightAlert(){
        RightAnsAlert rightAnsAlert = new RightAnsAlert();
        rightAnsAlert.setCancelable(false);
        rightAnsAlert.show(getFragmentManager(), "Right Alert");
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

    public boolean IsFoundInArray(int [] arr,int num){
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
