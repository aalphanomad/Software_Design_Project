package com.example.projectgamma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.preference.PreferenceManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity implements OnClickListener {

     ConnectivityManager conMgr;
     NetworkInfo activeNetwork;


    EditText UsernameEt, PasswordEt;
    TextView AboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Setting components to variables
        setContentView(R.layout.activity_login);
        UsernameEt = findViewById(R.id.etUserName);
        PasswordEt = findViewById(R.id.etPassword);
        AboutUs=(TextView) findViewById(R.id.AboutUs);
        AboutUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, AboutUs.class);
                LoginActivity.this.startActivity(i);
            }
        });
        final Button signin = (Button) findViewById(R.id.signin_but);

        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if the input received from the login screen is valid,proceed to login
                if (validate() == true) {
                    ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
                    if(activeNetwork != null && activeNetwork.isConnected()) {
                        attemptLogin();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Please check  your internet connection and try again.", Toast.LENGTH_SHORT).show();
                    }
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

    public static String get_SHA_1_SecurePassword(String passwordToHash,String Student_num)
    {
        String generatedPassword = null;
        try {

            //System.out.println("TEST1"+temp_salt);
            //Byte salt=Byte.valueOf(temp_salt);
            byte[] salt= Student_num.getBytes();

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            System.out.println(generatedPassword);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
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
            backgroundWorker.execute(type, username, get_SHA_1_SecurePassword(password,username));


    }
public void onBackPressed(){


}
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


};



