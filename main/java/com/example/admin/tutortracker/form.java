package com.example.admin.tutortracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class form extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
    }

    public void send(View view) {
        Intent i = new Intent(view.getContext(), lectureDuration.class);
        startActivity(i);

        EditText e1 = findViewById(R.id.editText1);
        EditText e2 = findViewById(R.id.editText2);
        EditText e3 = findViewById(R.id.editText3);

        Intent intent=new Intent(this,qrScanner.class); //sending value of edittexts to qrScanner class
        intent.putExtra("e1", String.valueOf(e1));
        intent.putExtra("e2", String.valueOf(e2));
        intent.putExtra("e3", String.valueOf(e3));

    }
}
