
package com.ourproject.learningapp.fragments;


import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.ourproject.learningapp.activities.LoginActivity;
import com.ourproject.learningapp.adapters.CustomPagerAdapter;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.globals.GlobalVariables;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DownloadManager downloadManager;
    private Long reference, referenceID;
    private Boolean exists = false;
    private String[] allFiles;
    private int id;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));

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
        if (GlobalVariables.BackFrom3rdTab) {
            GlobalVariables.BackFrom3rdTab = false;
            viewPager.setCurrentItem(2);
        }
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        List<String> finalArray = new ArrayList<>();
        Collections.addAll(finalArray, getResources().getStringArray(R.array.lettersImages1));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.lettersImages2));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.lettersImages3));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.lettersSounds));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.PicSounds1));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.PicSounds2));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.PicSounds3));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.DamPics));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.FathPics));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.KasrPics));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.damsound));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.fathsound));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.kasrsound));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.MadBelAlfSounds));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.MadBelWawSounds));
        Collections.addAll(finalArray, getResources().getStringArray(R.array.MadBelYaaSounds));

        allFiles = finalArray.toArray(new String[finalArray.size()]);
        toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) view.findViewById(R.id.drewer);
        navigationView = (NavigationView) view.findViewById(R.id.nav);
        View view1 = navigationView.getHeaderView(0);
        TextView tView = (TextView) view1.findViewById(R.id.user);
        tView.setText(new SharedPref(getActivity()).GetItem("UserId"));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        Toast.makeText(getActivity(), "Item 1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        GlobalVariables.message(getContext(),GlobalVariables.getUserName());
                        break;
                    case R.id.offline: {
                        exists = true;
                        for (int i = 0; i < allFiles.length; i++) {
                            checkFiles(allFiles[i], i);
                            DownloadChecker(downloadManager, allFiles[i], i);
                        }
                        if (exists) {
                            Toast.makeText(getActivity(), "Files Already Exists",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Menu nav_Menu = navigationView.getMenu();
                            nav_Menu.findItem(R.id.offline).setEnabled(false);
                            Toast.makeText(getActivity(), "Start Downloading", Toast.LENGTH_LONG).show();
                        }

                    }
                    break;
                    case R.id.mLogout: {
                        firebaseAuth.signOut();
                        getActivity().finish();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                    }
                    break;
                }
                return true;
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.colse);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

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

        return view;
    }


    public void DownloadChecker(DownloadManager downloadManager, String url, int index) {
        String type = null;
        String url0 = url.replace("https://firebasestorage.googleapis.com/v0/b/youlearn-56a66.appspot.com/o/", "");
        String url1 = url0.substring(0, url0.length() - 57);
        url1 = url1.replace("%2F", "/");
        if (url1.contains("Dofde%603"))
            url1 = url1.replace("%603", "");
        else if (url.contains("beba%603"))
            url1 = url1.replace("%603", "");
        else if (url1.contains("Dima%603"))
            url1 = url1.replace("%603", "");
        if (url.contains("sound") || url.contains("Elmad")) {
            type = "mp3";
        } else {
            type = "png";
        }
        String fname = "/Android/data/com.ourproject.learningapp/files/Download/"
                + url1 + "." + type;
        File applictionFile = new File(Environment
                .getExternalStorageDirectory().getAbsolutePath(), fname);
        if (applictionFile.exists()) {

        } else {
            if (Build.VERSION.SDK_INT <= 19) {

                File file = new File(Environment.getExternalStorageDirectory(),
                        "/Android/data/com.ourproject.learningapp/files/Download/" + url1.substring(0, url1.lastIndexOf("/")));
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            exists = false;
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setVisibleInDownloadsUi(false);
            request.setDestinationInExternalFilesDir(getActivity(), Environment.DIRECTORY_DOWNLOADS, url1 + "." + type);
            reference = downloadManager.enqueue(request);
            if (id == index)
                referenceID = reference;
        }
    }

    private void checkFiles(String url, int index) {
        String type = null;
        String url0 = url.replace("https://firebasestorage.googleapis.com/v0/b/youlearn-56a66.appspot.com/o/", "");
        String url1 = url0.substring(0, url0.length() - 57);
        url1 = url1.replace("%2F", "/");
        if (url1.contains("Dofde%603"))
            url1 = url1.replace("%603", "");
        else if (url.contains("beba%603"))
            url1 = url1.replace("%603", "");
        else if (url1.contains("Dima%603"))
            url1 = url1.replace("%603", "");
        if (url.contains("sound") || url.contains("Elmad")) {
            type = "mp3";
        } else {
            type = "png";
        }
        String fname = "/Android/data/com.ourproject.learningapp/files/Download/"
                + url1 + "." + type;
        File applictionFile = new File(Environment
                .getExternalStorageDirectory().getAbsolutePath(), fname);
        if (applictionFile.exists()) {

        } else
            id = index;
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            //check if the broadcast message is for our Enqueued download
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            if (referenceId == referenceID) {

                Toast toast = Toast.makeText(getActivity(),
                        "Downloads Complete", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.offline).setEnabled(true);

            }


        }
    };

}

