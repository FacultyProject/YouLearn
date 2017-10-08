package com.ourproject.learningapp.globals;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.ourproject.learningapp.activities.MainActivity;
import com.ourproject.learningapp.dataStorage.SharedPref;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Moetaz on 3/23/2017.
 */

public class GlobalVariables {

    public static boolean BackFrom3rdTab = false;
    public static boolean ChallangeMode = false;
    public static boolean QuizCompleted = false;
    public static String TAG = "none";
    public static ArrayList<Integer> G1 = new ArrayList<>();
    public static ArrayList<Integer> G2 = new ArrayList<>();
    public static ArrayList<Integer> G3 = new ArrayList<>();

    public static boolean rAnswer = false;
    public static int nOfQUESTONS = 0;
    public static int scr = 0;
    public static String ainmLetterPosition = null;
    public static boolean SelfTestMode = false;
    public static int nOfRightAns = 0;
    public static String QUIZID = "none";
    public static String MAD_LETTER = null;
    private static MediaPlayer soundFile = new MediaPlayer();
    private static Thread sound;
    public static String LETTERTYPE = "none";

    public static void showPics(String url, Context context, ImageView imageView) {
        String url0 = url.replace("https://firebasestorage.googleapis.com/v0/b/youlearn-56a66.appspot.com/o/", "");
        url0 = url0.substring(0, url0.length() - 57);
        url0 = url0.replace("%2F", "/");
        String fname = "/Android/data/com.ourproject.learningapp/files/Download/"
                + url0 + ".png";

        File applictionFile = new File(Environment
                .getExternalStorageDirectory().getAbsolutePath(), fname);
        if (applictionFile.exists()) {
            Picasso.with(context).load(applictionFile)
                    .into(imageView);
        } else {
            Picasso.with(context).load(url)
                    .into(imageView);
        }

    }

    public static String getUserId(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public static String GetUserName(final Context context){

        final String[] UserName = new String[1];
        Firebase mScr;
        mScr = new Firebase("https://youlearn-56a66.firebaseio.com/usersinfo/"+getUserId());
        mScr.child("userName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    UserName[0] = (String)dataSnapshot.getValue();

                new SharedPref(context).SaveItem("UserName",UserName[0]);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return UserName[0];

    }

    public static void pMusic(String url, Context context) {
        String url0;
        url0 = url.replace("https://firebasestorage.googleapis.com/v0/b/youlearn-56a66.appspot.com/o/", "");
        String url1 = url0.substring(0, url0.length() - 57);
        url1 = url1.replace("%2F", "/");
        if (url1.contains("Dofde%603"))
            url1 = url1.replace("%603", "");
        else if (url.contains("beba%603"))
            url1 = url1.replace("%603", "");
        else if (url1.contains("Dima%603"))
            url1 = url1.replace("%603", "");
        String fname = "/Android/data/com.ourproject.learningapp/files/Download/"
                + url1 + ".mp3";

        File applictionFile = new File(Environment
                .getExternalStorageDirectory().getAbsolutePath(), fname);
        if (applictionFile.exists()) {
            Uri file = Uri.fromFile(applictionFile);
            soundFile = MediaPlayer.create(context, file);
            sound = new Thread() {
                public void run() {
                    soundFile.start();
                }
            };
            sound.start();
        } else {
            if (isNetworkConnected(context)) {
                try {
                    soundFile.stop();
                    soundFile = new MediaPlayer();
                    soundFile.setDataSource(url);
                    soundFile.prepare();
                    soundFile.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                GlobalVariables.message(context, "Check INTERNET Connection!");
        }
    }

    public static void colorChar(TextView textView, String ch, String word) {
        if (ch.contains("ه"))
            ch = "ه";
        else if (ch.contains("أ") && word.contains("ء"))
            ch = "ء";
        else if (ch.contains("أ") && word.contains("إ"))
            ch = "إ";
        String ayeTemp = word;

        ArrayList<Integer> positionInt = new ArrayList<>();
        for (int i = 0; i < ayeTemp.length(); i++) {
            if (ayeTemp.contains(ch)) {
                if (positionInt.size() == 0) {
                    positionInt.add(ayeTemp.indexOf(ch));
                }
            }
        }
        Spannable wordtoSpan = new SpannableString(word);
        for (int i = 0; i < positionInt.size(); i++) {
            wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), positionInt.get(i), positionInt.get(i) + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(wordtoSpan);
        textView.setTypeface(MainActivity.font);
    }

    private static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public static void message(Context context, String m) {
        Toast.makeText(context, m, Toast.LENGTH_LONG).show();
    }
}
