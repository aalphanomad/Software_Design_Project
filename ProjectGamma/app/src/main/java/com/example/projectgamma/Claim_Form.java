package com.example.projectgamma;
//The java import statements
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Claim_Form extends AppCompatActivity {

    //Variable assignments
    int hour = 0;
    int min1 = 0;
    int min2 = 0;
    ArrayList selectedItems = new ArrayList();
    String course ;
    String name;
    String stud_num;
    TextView Thevenue, TimeError,sel_Course;
    String type;
    String date;
    String venue;
    TextView tv;
    String[] topic=new String[3];
    private Spinner mySpinner;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Selecting wich xml res file is called when calling the Claim_form class
        setContentView(R.layout.form);
        Thevenue = findViewById(R.id.enterVenue);
        TimeError = findViewById(R.id.TimeError);
        venue = Thevenue.getText().toString();
        tv = (TextView) findViewById(R.id.duration);
        Button button=(Button)findViewById(R.id.button);
        sel_Course=findViewById(R.id.Sel_Course);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Array containing list of courses
                final String[] listItems = getResources().getStringArray(R.array.course_list);
                //Pop up form when selecting course is displayed
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Claim_Form.this);
                mBuilder.setTitle("Select a Course");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        topic=listItems[i].split(" ");
                        course=topic[0];
                        //Displays the course selected
                        sel_Course.setText(course);

                        dialogInterface.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }

        });
        Intent i = getIntent();
        //Recieves data from another class
        type = i.getStringExtra("insert");
        name = i.getStringExtra("name");
        stud_num = i.getStringExtra("student_num");

    }
//Increases the time-picker
   public void increase(View view) {


        min1 += 3;

        if (min1 == 6) {
            min1 = 0;
            hour += 1;
        }

        tv.setText(hour + " hrs : " + min1 + min2 + " min");
    }
//Decreases the time-picker
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
        //Checks whether a venue has been entered
        if (Thevenue.length() == 0) {
            Thevenue.setError("Please enter the venue");
        }
        //Checks if a course has been selected
               else if(topic[0]==null){
            sel_Course.setText("Please Select a course");
        }
        //Checks if a duration has been selected
        else if (hour == 0 && min1 == 0 && min2 == 0) {
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
//Sends the course,name,student numbber,venue and time to the qrGenerator Class
            intent.putExtra("course", course);
            intent.putExtra("name", name);
            intent.putExtra("student_num", stud_num);
            intent.putExtra("venue", venue);
            intent.putExtra("time", time);

            startActivity(intent);

        }
    }


}