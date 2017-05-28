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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ourproject.learningapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Signin;
    private EditText email;
    private EditText password;
    private TextView Signup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth =FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }
        progressDialog=new ProgressDialog(this);

        Signin= (Button) findViewById(R.id.busiGnin);
        email= (EditText) findViewById(R.id.eMail);
        password= (EditText) findViewById(R.id.passWord);
        Signup = (TextView) findViewById(R.id.sigNup);

        Signup.setOnClickListener(this);
        Signin.setOnClickListener(this);
    }


    private void UserLogin(){
        String EMAIL=email.getText().toString().trim();
        String PASSWORD=password.getText().toString().trim();


        if(TextUtils.isEmpty(EMAIL)){
            Toast.makeText(LoginActivity.this,"Enter email",Toast.LENGTH_LONG).show();

        }
        if(TextUtils.isEmpty(PASSWORD)){
            Toast.makeText(LoginActivity.this,"Enter password",Toast.LENGTH_LONG).show();

        }
        progressDialog.setMessage("Registering ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(EMAIL,PASSWORD)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start profile activiy
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{

                            Log.e("Error",task.getException().getMessage());
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == Signin){
            UserLogin();
        }
        if(view == Signup){
            finish();
            startActivity(new Intent(this,SignupActivity.class));
        }

    }
}
