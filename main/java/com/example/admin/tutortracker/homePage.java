package com.example.admin.tutortracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class homePage extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

    }

    public void something(View view) {
        TextView tv = (TextView) findViewById(R.id.textView12);
        tv.setText("Please fill in the form");

        LinearLayout layout = (LinearLayout) findViewById(R.id.linLay);
        layout.removeAllViews();

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        EditText et1 = new EditText(this);
        et1.setHint("Full name");
        layout.addView(et1, lp);

        EditText et2 = new EditText(this);
        et2.setHint("Student No.");
        layout.addView(et2, lp);

        EditText et3 = new EditText(this);
        et3.setHint("Email Address");
        layout.addView(et3, lp);

        EditText et4 = new EditText(this);
        et4.setHint("Year of Study");
        layout.addView(et4, lp);

        EditText et5 = new EditText(this);
        et5.setHint("Payroll No.");
        layout.addView(et5, lp);

        EditText et6 = new EditText(this);
        et6.setHint("Cellphone No.");
        layout.addView(et6, lp);

        Button btn = new Button(this);
        btn.setText("SEND");
        layout.addView(btn, lp);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), homepage2.class);
                startActivity(i);
            }
        });
    }
}
