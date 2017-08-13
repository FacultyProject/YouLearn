package com.ourproject.learningapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.dataStorage.SharedPref;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Regiter;
    private EditText email,UserName;
    private EditText password;
    private TextView signin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        Firebase.setAndroidContext(this);
        if (!MainActivity.mTwoPane){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();



        progressDialog=new ProgressDialog(this);
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }

        Regiter= (Button) findViewById(R.id.buRegister);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        signin= (TextView) findViewById(R.id.login);
        UserName = (EditText) findViewById(R.id.user_name);


        Regiter.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    private void RegiterUser(){
        final String EMAIL=email.getText().toString().trim();
        String PASSWORD=password.getText().toString().trim();

        new SharedPref(getApplicationContext()).SaveItem("UserId",EMAIL.substring(0, EMAIL.indexOf('@')));
        if(TextUtils.isEmpty(EMAIL)){
            Toast.makeText(SignupActivity.this,"Enter email",Toast.LENGTH_LONG).show();
                return;
        }
        if(TextUtils.isEmpty(PASSWORD)){
            Toast.makeText(SignupActivity.this,"Enter password",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Registering ...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(EMAIL,PASSWORD)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mDatabase.child("usersinfo").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("email").setValue(EMAIL);

                           // mDatabase.child("usersinfo").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                  //  .child("score").setValue("-2");
                            mDatabase.child("usersinfo").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child("check").setValue("-1");
                            mDatabase.child("usersinfo").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child("userName").setValue(UserName.getText().toString());
                            mDatabase.child("usersinfo").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child("userid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());



                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Log.e("SignupActivity", "Failed Registration", e);
                            progressDialog.hide();
                            return;
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == Regiter){
            RegiterUser();
        }
        if(view==signin){
            startActivity(new Intent(this,LoginActivity.class));

        }

    }
}

