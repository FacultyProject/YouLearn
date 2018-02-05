
package com.ourproject.learningapp.fragments;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.activities.About;
import com.ourproject.learningapp.activities.RegisteringActivity;
import com.ourproject.learningapp.adapters.CustomPagerAdapter;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.ourproject.learningapp.globals.GlobalVariables;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
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
    public static final int REQUET_CODE = 1;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;
    ImageView ProfImage;
    private Firebase mCheck;
    List<String> finalArray;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        GlobalVariables.GetUserName(getActivity());


        if (firebaseAuth.getCurrentUser() == null) {
            getActivity().finish();
            startActivity(new Intent(getActivity(), RegisteringActivity.class));

        }
        storageReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(getActivity());
        mCheck = new Firebase("https://youlearn-56a66.firebaseio.com/usersinfo/"+GlobalVariables.getUserId());


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

        AddUrlsToArrary();

        allFiles = finalArray.toArray(new String[finalArray.size()]);
        toolbar = (Toolbar) view.findViewById(R.id.app_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        drawerLayout = (DrawerLayout) view.findViewById(R.id.drewer);
        navigationView = (NavigationView) view.findViewById(R.id.nav);

        View view1 = navigationView.getHeaderView(0);
        final TextView tView = (TextView) view1.findViewById(R.id.user);
        tView.setText( new SharedPref(getActivity()).GetItem("UserName"));
        ProfImage = (ImageView) view1.findViewById(R.id.userpic);
        ProfImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUET_CODE);
            }
        });
        final StorageReference filepath2 = storageReference.child("usersProfilePic/" + GlobalVariables.getUserId() + ".jpg");

        mCheck.child("check").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String UserCheck = (String) dataSnapshot.getValue();
                if (UserCheck != null) {
                    if (Integer.parseInt(UserCheck) == -1) {
                        ProfImage.setImageResource(R.drawable.avatar);
                    } else
                        Glide.with(getActivity()).using(new FirebaseImageLoader()).load(filepath2).into(ProfImage);
                } else {
                    ProfImage.setImageResource(R.drawable.avatar);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        getActivity().registerReceiver(downloadReceiver, filter);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        Intent intent1 = new Intent(getActivity(), About.class);
                        getActivity().startActivity(intent1);

                        break;
                    case R.id.share:
                        ApplicationInfo applicationInfo = getActivity().getApplicationContext().getApplicationInfo();
                        String apkPath = applicationInfo.sourceDir;
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("application/vnd.android.package-archive");
                        intent.putExtra(Intent.EXTRA_TEXT, Uri.fromFile(new File(apkPath)));
                        getActivity().startActivity(Intent.createChooser(intent, "Share via"));

                        break;
                    case R.id.offline: {
                        exists = true;
                        Menu nav_Menu = navigationView.getMenu();
                        nav_Menu.findItem(R.id.offline).setEnabled(false);
                        for (int i = 0; i < allFiles.length; i++) {
                            checkFiles(allFiles[i], i);
                            DownloadChecker(downloadManager, allFiles[i], i);
                        }
                        if (exists) {
                            nav_Menu.findItem(R.id.offline).setEnabled(true);
                            Toast.makeText(getActivity(), "Files Already Exists",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            nav_Menu.findItem(R.id.offline).setEnabled(false);
                            Toast.makeText(getActivity(), "Start Downloading", Toast.LENGTH_LONG).show();
                        }

                    }
                    break;
                    case R.id.mLogout: {
                        Logout();
                    }
                    break;
                }
                return true;
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open, R.string.colse);

        if(Build.VERSION.SDK_INT >= 23)
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
        else
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        if(Build.VERSION.SDK_INT >= 24)
            TabListnerAPI24Orhiger();
        else
        TabListnerAPIlessthan24();

        return view;
    }

    private void AddUrlsToArrary() {
        finalArray = new ArrayList<>();
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
    }

    private void Logout() {
        firebaseAuth.signOut();
        getActivity().finish();
        startActivity(new Intent(getActivity(), RegisteringActivity.class));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUET_CODE && resultCode == getActivity().RESULT_OK) {
            progressDialog.setMessage("Upload");
            progressDialog.show();

            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bData = baos.toByteArray();
            ProfImage.setImageBitmap(bitmap);

            StorageReference filepath = storageReference.child("usersProfilePic/" + GlobalVariables.getUserId() + ".jpg");
            filepath.putBytes(bData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Firebase childRef3 = mCheck.child("check");
                    childRef3.setValue("1");
                    progressDialog.dismiss();
                    Uri downloadurl = taskSnapshot.getDownloadUrl();
                    Picasso.with(getActivity()).load(downloadurl).into(ProfImage);
                    GlobalVariables.message(getActivity(), "Finished");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    GlobalVariables.message(getActivity(), e.getMessage());
                }
            });
        }
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

    private void TabListnerAPI24Orhiger(){

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    }

    private void TabListnerAPIlessthan24(){

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
    }
}

