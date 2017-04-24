package com.ourproject.learningapp.fragments;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.Interface.PositionRespone;
import com.ourproject.learningapp.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Q4Fragment extends Fragment {

    int[] index,imagesIndex;
    String[] images;
    public static int position =0,s,s0;
    String[]letters,lettersImages1,lettersSounds,word1;
    ImageView imageView,imageView2,imageView3,play;
    TextView textView;
    public static String ans="false";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_q4, container, false);
        index=randArray(28);
        imageView= (ImageView)view. findViewById(R.id.imageView);
        imageView2= (ImageView)view. findViewById(R.id.imageView2);
        imageView3= (ImageView)view. findViewById(R.id.imageView3);
        textView= (TextView) view.findViewById(R.id.textView);
        letters=getActivity().getResources().getStringArray(R.array.letters);
        lettersSounds=getActivity().getResources().getStringArray(R.array.lettersSounds);
        lettersImages1=getActivity().getResources().getStringArray(R.array.lettersImages1);
        word1=getActivity().getResources().getStringArray(R.array.lettersName1);
        showData(position++);
        final Myalert myalert=new Myalert();
        myalert.setPositionRespone(new PositionRespone() {
            @Override
            public void postitionPlus(int position) {
                showData(position);
            }
        });
        LettersAnimation(textView,imageView,imageView2,imageView3);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pMusic(lettersSounds[index[position-1]]);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lettersImages1[index[position-1]]==images[0]){
                    ans="true";
                }else
                    ans="false";
                myalert.show(getFragmentManager(),"");


            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lettersImages1[index[position-1]]==images[1]){
                    ans="true";
                }else
                    ans="false";
                myalert.show(getFragmentManager(),"");


            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lettersImages1[index[position-1]]==images[2]){
                    ans="true";
                }else
                    ans="false";
                myalert.show(getFragmentManager(),"");

            }
        });
        return view;
    }
    private void showData(int position){
        imagesIndex=randArray(3);
        images=new String[3];
        textView.setText(letters[index[position]]);
        images[imagesIndex[0]]=lettersImages1[index[position]];
        Random random = new Random();
        do {
            s=random.nextInt(28);
            s0=random.nextInt(28);
        }while (position==s||position==s0||s==s0);
        images[imagesIndex[1]]=lettersImages1[index[s]];
        images[imagesIndex[2]]=lettersImages1[index[s0]];
        Picasso.with(getActivity()).load(images[0])
                .placeholder(R.drawable.load_icon).into(imageView);
        Picasso.with(getActivity()).load(images[1])
                .placeholder(R.drawable.load_icon).into(imageView2);
        Picasso.with(getActivity()).load(images[2])
                .placeholder(R.drawable.load_icon).into(imageView3);
    }
    public void LettersAnimation(View ... views){
        for(View v:views){
            ObjectAnimator a1= ObjectAnimator.ofFloat(v,"translationY",-600,0);
            a1.setDuration(2300);
            AnimatorSet animatorSet =new AnimatorSet();
            animatorSet.playTogether(a1);
            animatorSet.start();
        }
    }    public  int[] randArray(int num){
        int[] arr;
        arr=new int[num];
        int n;
        boolean bool = false;
        Random r = new Random();
        for(int i=0;i<arr.length;i++)
            arr[i]=-1;
        for(int i=0;i<arr.length;i++){
            n = r.nextInt(num);
            do{
                for(int j=0;j<arr.length;j++){

                    if(arr[j]!=n) {
                        bool=true;
                    }else{
                        n = r.nextInt(num);
                        bool=false;
                        break;
                    }
                }
            } while(bool==false);

            arr[i]=n;
        }
        return arr;
    }
    public static void pMusic(String url){
        MediaPlayer soundFile=new MediaPlayer();
        try {
            soundFile.stop();
            soundFile = new MediaPlayer();
            soundFile.setDataSource(url);
            soundFile.prepare();
            soundFile.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
