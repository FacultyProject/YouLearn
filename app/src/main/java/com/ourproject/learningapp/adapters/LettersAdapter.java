package com.ourproject.learningapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.LetterInfo;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.fragments.LetterFragment;
import com.ourproject.learningapp.globals.GlobalVariables;
import com.ourproject.learningapp.models.LettersModel;
import com.ourproject.learningapp.services.ServiceClass;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mohamed Ali on 3/2/2017.
 */
public class LettersAdapter extends RecyclerView.Adapter<LettersAdapter.MyViewHolder> {
    private List<LettersModel> letttersInfo;
    private Context context;
    public LettersAdapter(List<LettersModel> letttersInfo, Context context) {
        this.letttersInfo = new ArrayList<>();
        this.letttersInfo = letttersInfo;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (MainActivity.mTwoPane) {
            holder.textView.setWidth(100);
            holder.textView.setHeight(100);
        }
        holder.textView.setText(letttersInfo.get(position).getLetter());
        holder.textView.setTypeface(MainActivity.font);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.stopService(new Intent(context,ServiceClass.class));

                if (GlobalVariables.LETTERTYPE.equals("NORMAL_MOVEMENT")||GlobalVariables.LETTERTYPE.equals("ShortMovement")) {
                    if (MainActivity.mTwoPane) {
                        Bundle bundle = new Bundle();
                        LetterFragment detailFragment = new LetterFragment();
                        bundle.putSerializable("wordslist", letttersInfo.get(position));
                        detailFragment.setArguments(bundle);
                        ((FragmentActivity) context).getFragmentManager().beginTransaction()
                                .replace(R.id.letter_detail_container, detailFragment).commit();

                    } else {
                        Intent intent = new Intent(context, LetterInfo.class);
                        intent.putExtra("wordslist", letttersInfo.get(position));
                        context.startActivity(intent);
                    }
                }else {
                    Intent intent = new Intent(context, LetterInfo.class);
                    GlobalVariables.ainmLetterPosition =Integer.toString(position+1);
                    context.startActivity(intent);


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return letttersInfo.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.mark);
        }
    }
}