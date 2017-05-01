package com.ourproject.learningapp.fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.SelfTestActivity;
import com.ourproject.learningapp.globals.GlobalVariables;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Q6Fragment extends Fragment {
    public static final int TARGER2=10;
    public static  boolean TAG = false;
    int rand,Randlist;
    private String[] Words, PicWords;
    ImageView imageView, Rec;
    TextView textView1, textView2;

    public Q6Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Randlist=new Random().nextInt(3);
        if(Randlist == 0) {
            do{
                rand = new Random().nextInt(28);
            }while (GlobalVariables.G1.contains(rand));
            GlobalVariables.G1.add(rand);

            Words = getActivity().getResources().getStringArray(R.array.lettersName1);
            PicWords = getActivity().getResources().getStringArray(R.array.lettersImages1);
        }else if(Randlist == 1){
            do{
                rand = new Random().nextInt(28);
            }while (GlobalVariables.G2.contains(rand));
            GlobalVariables.G2.add(rand);

            Words = getActivity().getResources().getStringArray(R.array.lettersName2);
            PicWords = getActivity().getResources().getStringArray(R.array.lettersImages2);
        }
        else

        {
            do{
                rand = new Random().nextInt(28);
            }while (GlobalVariables.G3.contains(rand));
            GlobalVariables.G3.add(rand);

            Words = getActivity().getResources().getStringArray(R.array.lettersName3);
            PicWords = getActivity().getResources().getStringArray(R.array.lettersImages3);
        }

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q6, container, false);
        imageView = (ImageView) view.findViewById(R.id.img);
        Rec = (ImageView) view.findViewById(R.id.StartRecord);
        textView1 = (TextView) view.findViewById(R.id.wPic);
        textView2 = (TextView) view.findViewById(R.id.incomeWord);
        Picasso.with(getActivity()).load(PicWords[rand]).into(imageView);
        textView1.setText(Words[rand]);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Log.i(tag, "keyCode: " + keyCode);
                if( (keyCode == KeyEvent.KEYCODE_BACK )  ) {
                    // Log.i(tag, "onKey Back listener is working!!!");
                    GlobalVariables.TAG="none";
                     TAG = false;
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return false;
                } else {
                    return true;
                }
            }
        });

        Rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-EG");
                try   {
                    startActivityForResult(intent, 10);
                } catch (ActivityNotFoundException a){
                    Toast.makeText(getActivity(), "your device doent support", Toast.LENGTH_LONG).show();

                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            try {
                String WORDPIC = Words[rand].replace("ة", "ه").replace("أ", "ا");

                ArrayList<String> result = new ArrayList<>();
                switch (requestCode) {
                    case 10:

                          if (resultCode == getActivity().RESULT_OK && data != null) {
                            result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                            textView2.setText(result.get(0));
                        }
                        break;

                }


                if (WORDPIC.equals(result.get(0)) || WORDPIC.contains(result.get(0)) || result.get(0).contains(WORDPIC)
                        || CompareStrings(WORDPIC.toCharArray(),result.get(0).toCharArray()) ) {
                    if(GlobalVariables.SelfTestMode){
                        SelfTestActivity.TimerIsRunning=false;
                        GlobalVariables.scr++;
                        GlobalVariables.rAnswer=true;
                        SelfTestAlert selfTestAlert=new SelfTestAlert();
                        selfTestAlert.show(getFragmentManager(),"sAlert");
                    }
                    else {
                        GlobalVariables.nOfRightAns++;
                        GlobalVariables.TAG="Q6Fragment";
                        Q6Fragment.TAG = true;
                        goTOalert();
                    }
                } else {
                    if(GlobalVariables.SelfTestMode){
                        SelfTestActivity.TimerIsRunning=false;
                        GlobalVariables.rAnswer=false;
                        SelfTestAlert selfTestAlert=new SelfTestAlert();
                        selfTestAlert.show(getFragmentManager(),"srAlert");
                    }
                    else {
                        WrongAnsAlert wrongAnsAlert = new WrongAnsAlert();
                        wrongAnsAlert.show(getFragmentManager(), "qAlert");
                    }
                    //textView2.setText("");
                }
            }catch (Exception e){
                Toast.makeText(getActivity(), "من فضلك انطق الكلمه", Toast.LENGTH_LONG).show();
            }

    }

        public   boolean CompareStrings(char [] s1,char [] s2){
            int count = 0;
            for(int i=0;i<s1.length;i++){
                for(int j=0;j<s2.length;j++){
                    if(s1[i] == (s2[j])){
                        count ++;
                        break;
                    }
                }
            }
            return count >= 3;
        }

    public void goTOalert(){
        if(GlobalVariables.nOfRightAns < TARGER2) {
            RightAnsAlert rightAnsAlert = new RightAnsAlert();
            rightAnsAlert.show(getFragmentManager(), "qAlert");
        }
        else{
            GlobalVariables.TAG="none";

            GlobalVariables.QuizCompleted=true;
            RightAnsAlert rightAnsAlert = new RightAnsAlert();
            rightAnsAlert.show(getFragmentManager(), "qAlert");
        }

    }
}
