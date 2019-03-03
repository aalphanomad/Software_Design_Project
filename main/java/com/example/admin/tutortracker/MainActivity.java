package com.example.admin.tutortracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    public static TextView resultTV;
    Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //resultTV = (TextView)findViewById(R.id.tv);
        //scan = (Button)findViewById(R.id.btn);
    }

    public void signIn(View view) {

        ContentValues params = new ContentValues();
        final String enteredPassword = ((EditText) findViewById(R.id.password)).getText().toString();
        final String enteredstudentnum = ((EditText) findViewById(R.id.studentnum)).getText().toString();

        @SuppressLint("StaticFieldLeak") AsyncHTTPPost asyncHTTPPost = new AsyncHTTPPost(
                "http://lamp.ms.wits.ac.za/~s1601745/signin.php?studentnum=" + ((EditText) findViewById(R.id.studentnum)).getText().toString(), params) {
            @Override
            protected void onPostExecute(String output) {
                try {
                    JSONArray arr = new JSONArray(output);
                    String name = arr.getJSONObject(0).getString("NAME");
                    String password = arr.getJSONObject(0).getString("USER_PASSWORD");
                    String studentnum = arr.getJSONObject(0).getString("STUDENT_NUMBER");


                    if (password.equals(enteredPassword) && studentnum.equals(enteredstudentnum)) {
                        Toast t1 = Toast.makeText(getApplicationContext(), "SIGN IN SUCCESSFUL", Toast.LENGTH_SHORT);
                        t1.setGravity(Gravity.CENTER, 0, 0);
                        t1.show();

                        Intent i = new Intent(getBaseContext(), homePage.class);
                        i.putExtra("name", name);
                        i.putExtra("studentnum", studentnum);
                        startActivity(i);
                    } else {
                        Toast t2 = Toast.makeText(getApplicationContext(), "PASSWORD IS INCORRECT", Toast.LENGTH_SHORT);
                        t2.setGravity(Gravity.CENTER, 0, 0);
                        t2.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "STUDENT NUMBER DOES NOT EXIST. PLEASE CREATE AN ACCOUNT", Toast.LENGTH_SHORT).show();
                }

            }
        };
        asyncHTTPPost.execute();

    }


    public void newUserClick(View v) {
        Intent i = new Intent(v.getContext(), homePage.class);
        startActivity(i);

    }


    public void scanIn(View view) {
        Intent i = new Intent(MainActivity.this, QR.class);
        startActivity(i);
    }
}
