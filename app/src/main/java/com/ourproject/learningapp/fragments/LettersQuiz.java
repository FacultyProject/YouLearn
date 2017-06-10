package com.ourproject.learningapp.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import com.ourproject.learningapp.globals.GlobalVariables;

import java.util.Random;

/**
 * Created by Mohamed Ali on 6/10/2017.
 */
public class LettersQuiz extends Fragment {
    int[] index, lettersIndex;
    String[] lettersPos;
    public static int position = 0, s, s0;
    String[] letters, lettersImages1, lettersSounds, word1;
    public static TextView textView1, textView2, textView3;
    ImageView playSound;
    public static String ans = "false";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letters_quiz, container, false);
        index = randArray(28);
        textView1 = (TextView) view.findViewById(R.id.letter1);
        textView2 = (TextView) view.findViewById(R.id.letter2);
        textView3 = (TextView) view.findViewById(R.id.letter3);
        playSound = (ImageView) view.findViewById(R.id.play);
        letters = getActivity().getResources().getStringArray(R.array.letters);
        lettersSounds = getActivity().getResources().getStringArray(R.array.lettersSounds);
        lettersImages1 = getActivity().getResources().getStringArray(R.array.lettersImages1);
        word1 = getActivity().getResources().getStringArray(R.array.lettersName1);
        showData(position++);
        final Myalert myalert = new Myalert();
        myalert.setPositionRespone(new PositionRespone() {
            @Override
            public void postitionPlus(int position) {
                showData(position);
            }

            @Override
            public void animation(View... views) {
                LettersAnimation(textView1, textView2, textView3);
            }
        });
        LettersAnimation(textView1, textView2, textView3);
        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVariables.pMusic(lettersSounds[index[position - 1]], getActivity());
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (letters[index[position - 1]] == lettersPos[0]) {
                    ans = "true";
                } else
                    ans = "false";
                myalert.setCancelable(false);
                myalert.show(getFragmentManager(), "");


            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (letters[index[position - 1]] == lettersPos[1]) {
                    ans = "true";
                } else
                    ans = "false";
                myalert.setCancelable(false);
                myalert.show(getFragmentManager(), "");


            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (letters[index[position - 1]] == lettersPos[2]) {
                    ans = "true";
                } else
                    ans = "false";
                myalert.setCancelable(false);
                myalert.show(getFragmentManager(), "");

            }
        });
        return view;
    }

    private void showData(int position) {
        lettersIndex = randArray(3);
        lettersPos = new String[3];
        lettersPos[lettersIndex[0]] = letters[index[position]];
        Random random = new Random();
        do {
            s = random.nextInt(28);
            s0 = random.nextInt(28);
        } while (position == s || position == s0 || s == s0);
        lettersPos[lettersIndex[1]] = letters[index[s]];
        lettersPos[lettersIndex[2]] = letters[index[s0]];
        textView1.setText(lettersPos[0]);
        textView2.setText(lettersPos[1]);
        textView3.setText(lettersPos[2]);

    }

    public void LettersAnimation(View... views) {
        for (View v : views) {
            ObjectAnimator a1 = ObjectAnimator.ofFloat(v, "translationX", 600, 0);
            a1.setDuration(2300);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(a1);
            animatorSet.start();
        }
    }

    public int[] randArray(int num) {
        int[] arr;
        arr = new int[num];
        int n;
        boolean bool = false;
        Random r = new Random();
        for (int i = 0; i < arr.length; i++)
            arr[i] = -1;
        for (int i = 0; i < arr.length; i++) {
            n = r.nextInt(num);
            do {
                for (int j = 0; j < arr.length; j++) {

                    if (arr[j] != n) {
                        bool = true;
                    } else {
                        n = r.nextInt(num);
                        bool = false;
                        break;
                    }
                }
            } while (bool == false);

            arr[i] = n;
        }
        return arr;
    }
}
