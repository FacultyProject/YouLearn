package com.ourproject.learningapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.ourproject.learningapp.R;
import com.ourproject.learningapp.dataStorage.SharedPref;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Regiter;
    private EditText email;
    private EditText password;
    private TextView signin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Firebase mUsers,mScr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        Firebase.setAndroidContext(this);

        mUsers = new Firebase("https://youlearn-56a66.firebaseio.com/users");
        mScr = new Firebase("https://youlearn-56a66.firebaseio.com/score");

        progressDialog=new ProgressDialog(this);
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }

        Regiter= (Button) findViewById(R.id.buRegister);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        signin= (TextView) findViewById(R.id.login);

        Regiter.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    private void RegiterUser(){
        final String EMAIL=email.getText().toString().trim();
        String PASSWORD=password.getText().toString().trim();

        new SharedPref(getApplicationContext()).SaveItem("UserId",EMAIL.substring(0, EMAIL.indexOf('@')));
        if(TextUtils.isEmpty(EMAIL)){
            Toast.makeText(SignupActivity.this,"Enter email",Toast.LENGTH_LONG).show();

        }
        if(TextUtils.isEmpty(PASSWORD)){
            Toast.makeText(SignupActivity.this,"Enter password",Toast.LENGTH_LONG).show();

        }
        progressDialog.setMessage("Registering ...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(EMAIL,PASSWORD)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Firebase childRef1 = mUsers.child(EMAIL.substring(0,EMAIL.indexOf('@')));
                            childRef1.setValue((EMAIL.substring(0,EMAIL.indexOf('@'))));

                            Firebase childRef2 = mScr.child(EMAIL.substring(0,EMAIL.indexOf('@')));
                            childRef2.setValue("-2");


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
        if(view==Regiter){
            RegiterUser();
        }
        if(view==signin){
            startActivity(new Intent(this,LoginActivity.class));

        }

    }
}

