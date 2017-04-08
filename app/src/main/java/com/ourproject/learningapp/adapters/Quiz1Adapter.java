package com.ourproject.learningapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MadLetterInfo;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.activities.Quiz1Info;
import com.ourproject.learningapp.models.MadModel;
import com.ourproject.learningapp.models.QuizModel1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moetaz on 3/27/2017.
 */

public class Quiz1Adapter extends RecyclerView.Adapter<Quiz1Adapter.MyViewHolder> {
    private List<QuizModel1> QLetterInfo;
    private Context context;

    public Quiz1Adapter(List<QuizModel1> letttersInfo, Context context) {
        this.QLetterInfo = new ArrayList<>();
        this.QLetterInfo = letttersInfo;
        this.context = context;
    }

    @Override
    public Quiz1Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz1, parent, false);
        return new Quiz1Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Quiz1Adapter.MyViewHolder holder, final int position) {

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(context,Quiz1Info.class);
                intent.putExtra("qlist",QLetterInfo.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return QLetterInfo.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.q_mark);
        }
    }
}