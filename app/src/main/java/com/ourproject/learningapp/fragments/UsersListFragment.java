package com.ourproject.learningapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.SelfTestActivity;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.globals.ConstantVariables;
import com.ourproject.learningapp.globals.GlobalVariables;
import com.ourproject.learningapp.models.usersinfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment {

    private RecyclerView userslist;

    private DatabaseReference mDatabase;
    public UsersListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("usersinfo");

        userslist = (RecyclerView) view.findViewById(R.id.users_list);
        userslist.setHasFixedSize(true);
        userslist.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerAdapter<usersinfo,UserHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<usersinfo, UserHolder>(
                        usersinfo.class
                        ,R.layout.usre_row
                        ,UserHolder.class
                        ,mDatabase
                ) {
            @Override
            protected void populateViewHolder(final UserHolder viewHolder, final usersinfo model, final int position) {


                viewHolder.uName.setText(model.getUserName());
                viewHolder.uName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DatabaseReference newRef = mDatabase.child(GlobalVariables.getUserId()).child("ScoreInfo").push();

                        final String key =  newRef.getKey();
                        new SharedPref(getActivity()).SaveItem("ChallengeKey",key);
                        DatabaseReference ComRef = getRef(position);
                        final String ComKey = ComRef.getKey();
                        new SharedPref(getActivity()).SaveItem("CompetitorId",ComKey);

                         mDatabase.child(GlobalVariables.getUserId()).child("ScoreInfo").child(key).child("CompetitorId")
                                 .setValue(model.getUserid());
                        mDatabase.child(GlobalVariables.getUserId()).child("ScoreInfo").child(key).child("CompetitorName")
                                .setValue(model.getUserName());
                        //CompetitorScore

                        mDatabase.child(GlobalVariables.getUserId()).child("ScoreInfo").child(key).child("CompetitorScore")
                                .setValue("-1");
                        //------------------------------------//
                        mDatabase.child(model.getUserid()).child("ScoreInfo").child(key).child("CompetitorId") //user 2
                                .setValue(GlobalVariables.getUserId());

                        mDatabase.child(model.getUserid()).child("ScoreInfo").child(key).child("CompetitorName")
                                        .setValue(new SharedPref(getActivity()).GetItem("UserName"));

                         mDatabase.child(model.getUserid()).child("ScoreInfo").child(key).child("UserScore")
                                 .setValue("-1");
                         //GlobalVariables.message(getActivity(),key);

                        GlobalVariables.ChallangeMode = true;
                        startActivity(new Intent(getActivity(), SelfTestActivity.class));

                    }
                });

            }
        };

        userslist.setAdapter(firebaseRecyclerAdapter);



        return view;
    }

    public static class UserHolder extends RecyclerView.ViewHolder{


        TextView uName;
        public UserHolder(View itemView) {
            super(itemView);


              uName = (TextView) itemView.findViewById(R.id.username);

        }

        }
    }


