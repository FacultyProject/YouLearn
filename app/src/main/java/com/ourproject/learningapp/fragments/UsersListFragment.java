package com.ourproject.learningapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.SelfTestActivity;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.globals.ConstantVariables;
import com.ourproject.learningapp.globals.GlobalVariables;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersListFragment extends Fragment {

    private ListView listView;
    private ArrayList<String> mUsres = new ArrayList<>();
    private Firebase mRef,mScr,mCompitiors;
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
        mRef = new Firebase(ConstantVariables.fUsers);
        mScr = new Firebase(ConstantVariables.fScore);
        mCompitiors = new Firebase(ConstantVariables.fCompititors);

        final String USER = GlobalVariables.getUserName();

        listView = (ListView) view.findViewById(R.id.users_list);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                mUsres);
            listView.setAdapter(arrayAdapter);

        mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            String value = dataSnapshot.getValue(String.class);
                if(!value.equals(USER)) {
                    mUsres.add(value);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                new SharedPref(getActivity()).SaveItem("Challanger",mUsres.get(i));

                //Firebase childRef =mScr.child(mUsres.get(i));
                //childRef.setValue("-1");

                Firebase childRef2 = mCompitiors.child(mUsres.get(i));
                childRef2.setValue(USER);

                Firebase childRef3 = mCompitiors.child(USER);
                childRef3.setValue(mUsres.get(i));

                GlobalVariables.ChallangeMode = true;
                startActivity(new Intent(getActivity(), SelfTestActivity.class));
            }
        });


        return view;
    }

}
