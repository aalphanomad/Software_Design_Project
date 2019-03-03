package com.example.admin.tutortracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class homepage2 extends AppCompatActivity{

    int hour=0;
    int hour2=0;
    int min1=0;
    int min2=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage2);

    }

    public void increase(View view) {

        TextView tv =(TextView)findViewById(R.id.duration);

        min1+=3;

        if (min1==6){
            min1=0;
            hour+=1;
        }

        tv.setText(hour + " : " + min1+min2);
    }

    public void decrease(View view) {
        TextView tv =(TextView)findViewById(R.id.duration);
        int prevmin1 = min1;
        min1-=3;

        if (hour>0 && min1-3==0) {
            min1=0;
        }

        else if (hour>0 && prevmin1==0){
            hour-=1;
            min1=3;
        }

        if (min1-3<0){
            min1=0;
        }


        tv.setText(hour + " : " + min1+min2);
    }
}