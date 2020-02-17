package com.example.projectgamma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LecturerAdmin extends AppCompatActivity{
    Button LecturerBut,AdminBut;
    TextView Name_TB;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lectureradmin);
        LecturerBut=(Button) findViewById(R.id.LecturerBut);
        AdminBut=(Button)findViewById(R.id.AdminBut);
        Name_TB=(TextView) findViewById(R.id.Name_TB);
        Name_TB.setText(qrGenerator.Global.GetName());
        LecturerBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(LecturerAdmin.this,MyTutors.class);
                qrGenerator.Global.setRole("1");
                startActivity(intent);

            }
        });

        AdminBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(LecturerAdmin.this,adminViewCourses.class);
                qrGenerator.Global.setRole("2");
                startActivity(intent);
            }
            });
}

}

