
package com.ourproject.learningapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.ourproject.learningapp.activities.LoginActivity;
import com.ourproject.learningapp.adapters.CustomPagerAdapter;
import com.ourproject.learningapp.R;


public class MainFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            getActivity().finish();
            startActivity(new Intent(getActivity(),LoginActivity.class));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(((AppCompatActivity) getActivity()).getSupportFragmentManager(), getContext()));

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


        toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
        setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            firebaseAuth.signOut();
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));

            return true;
        }


            return super.onOptionsItemSelected(item);
        }

    }

