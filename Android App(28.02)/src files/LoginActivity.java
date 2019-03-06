package com.example.projectgamma;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity implements OnClickListener {
    EditText UsernameEt, PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsernameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
        final Button signin = (Button) findViewById(R.id.signin_but);

        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 //   attemptLogin();
                Intent test=new Intent(LoginActivity.this,HomeActivity.class);
                LoginActivity.this.startActivity(test);
                finish();
            }
        });

        final Button button = (Button) findViewById(R.id.new_userbt);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });
    }

    private boolean validate() {
        boolean valid = true;
        if (UsernameEt.getText().toString().length() == 0) {
            UsernameEt.setError("Please enter your Username");
            valid = false;
        }
        if (PasswordEt.getText().toString().length() == 0) {
            PasswordEt.setError("Please enter your Password");
            valid = false;
        }
        return valid;

    }

    public void onExecute() {
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    private void attemptLogin() {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

////CHECK!!!! ONCLICK FOR MULTIPLE
   @Override
    public void onClick(View v) {
       // attemptLogin();
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


};



