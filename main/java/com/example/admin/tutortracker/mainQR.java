package com.example.admin.tutortracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class mainQR extends AppCompatActivity{

    public static TextView resultTV;
    public static TextView resultTV1;
    public static TextView resultTV2;
    public static TextView resultTV3;
    Button scan;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrscanner);

        resultTV = (TextView)findViewById(R.id.tv1);
        //resultTV2= (TextView)findViewById(R.id.tv2);
        //resultTV3 = (TextView)findViewById(R.id.tv3);
        scan = (Button)findViewById(R.id.btn2);

    }

    public void generate(View view) {
        Intent i = new Intent(view.getContext(), qrGenerator.class);
        startActivity(i);
    }

    public void scanIn(View view) {
        Intent i = new Intent(view.getContext(), qrScanner.class);
        startActivity(i);
    }
}
