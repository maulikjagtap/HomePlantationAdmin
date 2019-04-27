package com.example.design.homeplantationadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button signup;
    private Button login;
    private Button forgotpass;
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(Login_Activity.this, MainActivity.class));
            finish();
        }
        firebaseAuth=FirebaseAuth.getInstance();
        signup=findViewById(R.id.LoginScreen_signup_button);
        login=findViewById(R.id.LoginScreen_login_button);
        forgotpass=findViewById(R.id.LoginScreen_forgot_password_button);
        email=findViewById(R.id.LoginScreen_email_edittext);
        password=findViewById(R.id.LoginScreen_pass_edittext);
        progressBar=findViewById(R.id.progressBar);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        forgotpass.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.LoginScreen_login_button:
                login();
                break;
            case R.id.LoginScreen_signup_button:
                signup();
                break;
            case R.id.LoginScreen_forgot_password_button:
                forgotpassword();
                break;
        }

    }
    private void signup() {
        Intent intent=new Intent(Login_Activity.this,RegistrationActivity.class);
        startActivity(intent);
    }
    private void login() {
        String useremail = email.getText().toString();
        final String userpassword = password.getText().toString();
        if (TextUtils.isEmpty(useremail)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userpassword)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(useremail.equals("admin@gmail.com") && userpassword.equals("admin"))
        {
            Intent intent = new Intent(Login_Activity.this,AddData.class);
            startActivity(intent);
            finish();
        }
        firebaseAuth.signInWithEmailAndPassword(useremail, userpassword).addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    // there was an error
                    if (password.length() < 6) {
                        Toast.makeText(Login_Activity.this, "Password is Wrong", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Login_Activity.this, "Login Fail", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Intent intent = new Intent(Login_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }


    private void forgotpassword() {
        startActivity(new Intent(Login_Activity.this,ForgotPassword_Activity.class));
    }

    }
