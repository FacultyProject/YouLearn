package com.ourproject.learningapp.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.globals.GlobalVariables;
import com.ourproject.learningapp.models.ScoreInfo;

public class CompetionsResultActivity extends AppCompatActivity {

    FloatingActionButton Fab;
    private RecyclerView userslist;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competions_result);
        Fab = (FloatingActionButton) findViewById(R.id.fab);
        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ChallangeActivity.class));
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("usersinfo").
                child(GlobalVariables.getUserId())
                .child("ScoreInfo");
        userslist = (RecyclerView)  findViewById(R.id.results);
        userslist.setHasFixedSize(true);
        userslist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FirebaseRecyclerAdapter<ScoreInfo,ScoreHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ScoreInfo, ScoreHolder>(
                 ScoreInfo.class,
                        R.layout.result_row
                        ,ScoreHolder.class
                        ,mDatabase
        ) {
            @Override
            protected void populateViewHolder(ScoreHolder viewHolder, final ScoreInfo model, final int position) {

                viewHolder.uName.setText(model.getCompetitorName());


                if (model.getUserScore() != null && model.getCompetitorScore() != null){
                    if (Integer.parseInt(model.getUserScore()) == -1)
                        viewHolder.Result.setText(model.getCompetitorScore() + " : " + "-");
                    else if (Integer.parseInt(model.getCompetitorScore()) == -1)
                        viewHolder.Result.setText("-" + " : " + model.getUserScore());
                    else
                        viewHolder.Result.setText(model.getCompetitorScore() + " : " + model.getUserScore());
            }

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference KeyRef = getRef(position);

                        String Key = KeyRef.getKey();
                        new SharedPref(getApplicationContext()).SaveItem("ChallengeKey",Key);
                        new SharedPref(getApplicationContext()).SaveItem("CompetitorId",model.getCompetitorId());
                        if(Integer.parseInt(model.getUserScore()) == -1) {
                            GlobalVariables.ChallangeMode = true;
                            startActivity(new Intent(getApplicationContext(), SelfTestActivity.class));
                        }
                    }
                });
            }
        };
        userslist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class ScoreHolder extends RecyclerView.ViewHolder{


        TextView uName,Result;

        public ScoreHolder(View itemView) {
            super(itemView);


            uName = (TextView) itemView.findViewById(R.id.chUser);
            Result = (TextView) itemView.findViewById(R.id.usersScore);

        }

    }
}
