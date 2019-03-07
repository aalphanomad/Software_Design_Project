package com.example.admin.tutortracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class mainQR extends AppCompatActivity{

    //public static TextView resultTV;
    String value1, value2, value3, value4, value5, value6;
    Button scan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrgenerator);

        scan = (Button)findViewById(R.id.btn2);

    }


    public void scanIn(View view) {
        Intent i = new Intent(view.getContext(), qrScanner.class);
        startActivity(i);
    }

    public void doneScan(View view) {


    }
}
