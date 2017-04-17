package com.ourproject.learningapp.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourproject.learningapp.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Q5Fragment extends Fragment {
    public static int COUNT=0;
    ViewGroup mRoot;
    ArrayList<String> arrayList;
    String [] Letters,RandLetters;
    String letter;
    private TextView L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15,L16,L17,L18,L19,L20;
    boolean check=false;
    String [] TwoLetters=new String [2];
    TextView [] Towviews =new TextView[2];
    int id;


    public Q5Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int index;
        int rand;
        RandLetters=new String[20];
        arrayList=new ArrayList<>();
        Letters=getActivity().getResources().getStringArray(R.array.letters);
        for(int i=0;i<10;i++){

            do{
                letter=Letters[new Random().nextInt(28)];
            }while (arrayList.contains(letter));
            arrayList.add(letter);
        }
        for ( int i=0;i<RandLetters.length;i++){

            if(i<10)
                index=i;
            else
                index=i-10;
            do{
                rand=new Random().nextInt(20);
            }while (!(RandLetters[rand]==null));

            RandLetters[rand]=arrayList.get(index);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_q5, container, false);
        mRoot= (ViewGroup) view.findViewById(R.id.llView);
        L1= (TextView) view.findViewById(R.id.R1);
        L2= (TextView) view.findViewById(R.id.R2);
        L3= (TextView) view.findViewById(R.id.R3);
        L4= (TextView) view.findViewById(R.id.R4);
        L5= (TextView) view.findViewById(R.id.R5);
        L6= (TextView) view.findViewById(R.id.R6);
        L7= (TextView) view.findViewById(R.id.R7);
        L8= (TextView) view.findViewById(R.id.R8);
        L9= (TextView) view.findViewById(R.id.R9);
        L10= (TextView) view.findViewById(R.id.R10);
        L11= (TextView) view.findViewById(R.id.R11);
        L12= (TextView) view.findViewById(R.id.R12);
        L13= (TextView) view.findViewById(R.id.R13);
        L14= (TextView) view.findViewById(R.id.R14);
        L15= (TextView) view.findViewById(R.id.R15);
        L16= (TextView) view.findViewById(R.id.R16);
        L17= (TextView) view.findViewById(R.id.R17);
        L18= (TextView) view.findViewById(R.id.R18);
        L19= (TextView) view.findViewById(R.id.R19);
        L20= (TextView) view.findViewById(R.id.R20);



        Settext(L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15,L16,L17,L18,L19,L20);
        Thread timer=new Thread() {
            public void run() {


                try
                {
                    sleep(5000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SetToDefault(L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15,L16,L17,L18,L19,L20);
                        }
                    });

                }
            }

        };
        timer.start();

        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=0;
                LetterListner(L1,id);
            }
        });
        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=1;
                LetterListner(L2,id);
            }
        });
        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=2;

                LetterListner(L3,id);
            }
        });
        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=3;
                LetterListner(L4,id);

            }
        });
        L5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=4;
                LetterListner(L5,id);

            }
        });
        L6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=5;
                LetterListner(L6,id);

            }
        });
        L7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=6;
                LetterListner(L7,id);

            }
        });
        L8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=7;
                LetterListner(L8,id);

            }
        });
        L9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=8;
                LetterListner(L9,id);

            }
        });
        L10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=9;
                LetterListner(L10,id);

            }
        });
        L11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=10;
                LetterListner(L11,id);

            }
        });
        L12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=11;
                LetterListner(L12,id);

            }
        });
        L13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=12;
                LetterListner(L13,id);

            }
        });
        L14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=13;
                LetterListner(L14,id);

            }
        });
        L15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=14;
                LetterListner(L15,id);

            }
        });
        L16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=15;
                LetterListner(L16,id);

            }
        });

        L17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=16;
                LetterListner(L17,id);

            }
        });
        L18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=17;
                LetterListner(L18,id);

            }
        });
        L19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=18;
                LetterListner(L19,id);

            }
        });
        L20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=19;
                LetterListner(L20,id);

            }
        });


        return view;
    }

    public void lFade(TextView ...textViews){

        if(Build.VERSION.SDK_INT >= 21) {
            //Fade fade=new Fade();
            Explode fade = new Explode();
            fade.setDuration(4000);
            TransitionManager.beginDelayedTransition(mRoot, fade);
            toggleVisibilty(textViews);
        }
        else{
            for(TextView current : textViews){
                if(current.getVisibility()==View.VISIBLE){
                    current.setVisibility(View.INVISIBLE);
                }
            }
        }

    }
    public void toggleVisibilty(TextView ...textViews){
        for(TextView current : textViews){
            if(current.getVisibility()==View.VISIBLE){
                current.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void LetterListner(TextView textView,int id){
        if(check){
            textView.setText(RandLetters[id]);
            Towviews[1]=textView;
            TwoLetters[1]=RandLetters[id];
            if(TwoLetters[0].equals(TwoLetters[1])) {
                COUNT++;

                lFade(Towviews);
                if(COUNT==10) {
                    Myalert myalert = new Myalert();
                    myalert.show(getFragmentManager(), "mAlert");
                }
            }
            else
            {

                Thread timer=new Thread() {
                    public void run() {


                        try
                        {
                            sleep(3000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        finally {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SetToDefault(Towviews);
                                }
                            });

                        }
                    }

                };
                timer.start();
            }

            check=false;

        }
        else {
            textView.setText(RandLetters[id]);
            Towviews[0]=textView;
            TwoLetters[0]=RandLetters[id];
            check=true;
        }
    }
    public void Settext(TextView ...textViews){
        int i=0;
        for(TextView textView:textViews){
            textView.setText(RandLetters[i]);
            i++;
        }
    }

    public void SetToDefault(TextView ...textViews){


        for(TextView textView:textViews){

            textView.setText(" ? ");

        }
    }



}
