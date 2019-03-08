package com.example.projectgamma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Claim_Form extends AppCompatActivity {

    int hour = 0;
    int min1 = 0;
    int min2 = 0;
    private Spinner mySpinner;

    ArrayList selectedItems = new ArrayList();
    String course = "COMS2323";
    String name;
    String stud_num;
    TextView Thevenue, TimeError;
    String type;
    String date;
    String venue;
    TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        Thevenue = findViewById(R.id.enterVenue);
        TimeError = findViewById(R.id.TimeError);
        venue = Thevenue.getText().toString();
        tv = (TextView) findViewById(R.id.duration);
        Intent i = getIntent();
        type = i.getStringExtra("insert");
        name = i.getStringExtra("name");
        stud_num = i.getStringExtra("student_num");

    }

    public void increase(View view) {


        min1 += 3;

        if (min1 == 6) {
            min1 = 0;
            hour += 1;
        }

        tv.setText(hour + " hrs : " + min1 + min2 + " min");
    }

    public void decrease(View view) {
        TextView tv = findViewById(R.id.duration);
        int prevmin1 = min1;
        min1 -= 3;

        if (hour > 0 && min1 - 3 == 0) {
            min1 = 0;
        } else if (hour > 0 && prevmin1 == 0) {
            hour -= 1;
            min1 = 3;
        }

        if (min1 - 3 < 0) {
            min1 = 0;
        }


        tv.setText(hour + " hrs : " + min1 + min2 + " min");
    }


    public void send(View view) {
        if (Thevenue.length() == 0) {
            Thevenue.setError("Please enter the venue");
        } else if (hour == 0 && min1 == 0 && min2 == 0) {
            TimeError.setText("Please enter the duration that you have tutored");
        } else {
            EditText e3 = findViewById(R.id.enterVenue);

            final String hourString, min1String, min2String;
            hourString = String.valueOf(hour);
            min1String = String.valueOf(min1);
            min2String = String.valueOf(min2);


            venue = e3.getText().toString();

            Intent intent = new Intent(this, qrGenerator.class);
            String time = hourString + "hrs" + ":" + min1String + min2String + "mins";

            System.out.println(course + ":" + name + ":" + stud_num + ":"  + ":" + venue + ":" + time);
            intent.putExtra("course", course);
            intent.putExtra("name", name);
            intent.putExtra("student_num", stud_num);
            intent.putExtra("venue", venue);
            intent.putExtra("time", time);

            startActivity(intent);

        }
    }
}