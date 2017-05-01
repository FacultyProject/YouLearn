package com.ourproject.learningapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.globals.GlobalVariables;
import com.ourproject.learningapp.models.MadModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moetaz on 3/16/2017.
 */

public class MadLettersAdapter extends RecyclerView.Adapter<MadLettersAdapter.MyViewHolder> {
    private List<MadModel> letttersInfo;
    private Context context;

    public MadLettersAdapter(List<MadModel> letttersInfo, Context context) {
        this.letttersInfo = new ArrayList<>();
        this.letttersInfo = letttersInfo;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mad, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textView.setText(letttersInfo.get(position).getLetterSection());
        holder.textView.setTypeface(MainActivity.font);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GlobalVariables.pMusic(letttersInfo.get(position).getSectionSound(),context);

            }
        });
    }

    @Override
    public int getItemCount() {
        return letttersInfo.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.mark_mad);
            imageView= (ImageView) itemView.findViewById(R.id.play);
        }
    }
}