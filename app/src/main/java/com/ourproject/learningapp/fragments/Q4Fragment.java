package com.ourproject.learningapp.fragments;


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

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Q4Fragment extends Fragment {
    public static int TRUEPOSTION;

    int[] index,imagesIndex;
    String[] images;
    public static int position =0,s,s0;
    String[]letters,lettersImages1,lettersSounds;
    ImageView imageView,imageView2,imageView3;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_q4, container, false);
        index=randArray(28);
        imagesIndex=randArray(3);
        images=new String[3];
        imageView= (ImageView)view. findViewById(R.id.imageView);
        imageView2= (ImageView)view. findViewById(R.id.imageView2);
        imageView3= (ImageView)view. findViewById(R.id.imageView3);
        textView= (TextView) view.findViewById(R.id.textView);
        letters=getActivity().getResources().getStringArray(R.array.letters);
        lettersSounds=getActivity().getResources().getStringArray(R.array.lettersSounds);
        lettersImages1=getActivity().getResources().getStringArray(R.array.lettersImages1);
        showData(position++);
        final PopFragment popFragment=new PopFragment();
        popFragment.setPositionRespone(new PositionRespone() {
            @Override
            public void postitionPlus(int position) {
                showData(position);
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                (getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main,popFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });
        return view;
    }
    private void showData(int position){

        textView.setText(letters[index[position]]);
        images[imagesIndex[0]]=lettersImages1[index[position]];
        Random random = new Random();
        s=random.nextInt(28);
        s0=random.nextInt(28);
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

}
