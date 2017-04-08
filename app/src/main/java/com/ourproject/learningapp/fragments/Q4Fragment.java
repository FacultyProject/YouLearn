package com.ourproject.learningapp.fragments;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ourproject.learningapp.PositionRespone;
import com.ourproject.learningapp.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Q4Fragment extends Fragment {
    public static int TRUEPOSTION;

    int[] index,imagesIndex;
    String[] images;
    public static int position =0,s,s0;
    String[]letters,lettersImages1,lettersSounds,word1;
    ImageView imageView,imageView2,imageView3;
    TextView textView;
    public static String ans="false";
    PopFragment popFragment;
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
        popFragment=new PopFragment();
        popFragment.setPositionRespone(new PositionRespone() {
            @Override
            public void postitionPlus(int position) {
                showData(position);
            }
        });

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
                toPopFrag(word1[index[position-1]]);

            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lettersImages1[index[position-1]]==images[1]){
                    ans="true";
                }else
                    ans="false";
                toPopFrag(word1[index[position-1]]);

            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lettersImages1[index[position-1]]==images[2]){
                    ans="true";
                }else
                    ans="false";
                toPopFrag(word1[index[position-1]]);
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
                .into(imageView);
        Picasso.with(getActivity()).load(images[1])
                .into(imageView2);
        Picasso.with(getActivity()).load(images[2])
                .into(imageView3);
    }
    public  int[] randArray(int num){
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
    private void toPopFrag(String text){
        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        popFragment.setArguments(bundle);
        (getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main,popFragment)
                .addToBackStack(null)
                .commit();
    }

}
