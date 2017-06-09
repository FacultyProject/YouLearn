package com.ourproject.learningapp.fragments;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Mohamed Ali on 6/9/2017.
 */
public class AboutFrag extends Fragment {
    private TextView textView;
    private StringBuilder text = new StringBuilder();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);


        textView = (TextView) view.findViewById(R.id.about);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getActivity().getAssets().open("file.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getActivity(), "Error reading file!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
            textView.setTextDirection(View.TEXT_DIRECTION_FIRST_STRONG_RTL);
            textView.setText(text);
            textView.setTypeface(MainActivity.font);


            return view;

        }
    }
}