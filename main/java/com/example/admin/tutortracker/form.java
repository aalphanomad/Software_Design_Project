package com.example.admin.tutortracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class form extends AppCompatActivity {

    int hour=0;
    int min1=0;
    int min2=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
    }

    public void increase(View view) {

        TextView tv =(TextView)findViewById(R.id.duration);

        min1+=3;

        if (min1==6){
            min1=0;
            hour+=1;
        }

        tv.setText(hour + " hrs : " + min1+min2+" min");
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


        tv.setText(hour + " hrs : " + min1+min2+" min");
    }


    /*public void task(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                TextView tv= findViewById(R.id.tv);
                tv.setText(input.getText());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }*/

    public void send(View view) {

        //EditText e1 = findViewById(R.id.editText1);
        //EditText e2 = findViewById(R.id.editText2);
        EditText e3 = findViewById(R.id.enterVenue);
        //final String one;
        //final String two;
        final String venue;
        final String hourString, min1String, min2String;
        hourString = String.valueOf(hour);
        min1String = String.valueOf(min1);
        min2String = String.valueOf(min2);

        //one  = e1.getText().toString();
        //two  = e2.getText().toString();
        venue  = e3.getText().toString();

        Intent intent=new Intent(this,qrGenerator.class); //sending value of edittexts to qrScanner class
        //intent.putExtra("e1", one);
        //intent.putExtra("e2", two);
        intent.putExtra("venue", venue);
        intent.putExtra("hr", hourString);
        intent.putExtra("min1", min1String);
        intent.putExtra("min2", min2String);
        startActivity(intent);

    }
}

