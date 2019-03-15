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
        //Setting components to variables
        setContentView(R.layout.activity_login);
        UsernameEt = findViewById(R.id.etUserName);
        PasswordEt = findViewById(R.id.etPassword);
        final Button signin = (Button) findViewById(R.id.signin_but);

        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if the input received from the login screen is valid,proceed to login
                if (validate() == true) {
                    attemptLogin();

                }
            }
        });
//Creates a button to take the from the login screen to the registering screen
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
        //Checks if the user entered a Username
        if (UsernameEt.getText().toString().length() == 0) {
            UsernameEt.setError("Please enter your Username");
            valid = false;
        }
        //checks if the user entered a password
        if (PasswordEt.getText().toString().length() == 0) {
            PasswordEt.setError("Please enter your Password");
            valid = false;
        }
        return valid;

    }


    private void attemptLogin() {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        //Sets the type to login and send the necesary data to the BackgroundWorker to login the user
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


};



