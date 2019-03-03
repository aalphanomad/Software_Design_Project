package com.example.admin.tutortracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class createAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
    }


    public void create(View view){
        final String studentnum = ((EditText)findViewById(R.id.studentnum2)).getText().toString();
        final String password = ((EditText)findViewById(R.id.password2)).getText().toString();
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String email = ((EditText)findViewById(R.id.email)).getText().toString();


        ContentValues params = new ContentValues();
        params.put("studentnum",studentnum);
        params.put("password",password);
        params.put("name",name);
        params.put("email",email);

        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost("http://lamp.ms.wits.ac.za/~s1601745/create.php", params) {
            @Override
            protected void onPostExecute(String output) {
                if (!studentnum.equals(null) && !password.equals(null) ) {
                    Toast t1 = Toast.makeText(getApplicationContext(), "Account Was Successfully Created", Toast.LENGTH_SHORT);
                    t1.setGravity(Gravity.CENTER, 0, 0);
                    t1.show();
                }


                if (!studentnum.isEmpty() && !password.isEmpty()){
                    Toast t2 = Toast.makeText(getApplicationContext(), "Sign in to confirm",Toast.LENGTH_SHORT);
                    t2.setGravity(Gravity.CENTER,0,0);
                    t2.show();
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Account Was Not Able To Be Created", Toast.LENGTH_SHORT).show();}
            }
        };
        asyncHTTPPost.execute();

    }
}