package com.ourproject.learningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mohamed Ali on 3/2/2017.
 */
public class LettersAdapter extends RecyclerView.Adapter<LettersAdapter.MyViewHolder> {
    List<String> lettters;
    Context context;
    public LettersAdapter( List<String> lettters, Context context) {
        this.lettters = new ArrayList<>();
        this.lettters = lettters;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textView.setText(lettters.get(position));
        holder.textView.setTypeface(MainActivity.font);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.stopService(new Intent(context,ServiceClass.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return lettters.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.mark);
        }
    }
}