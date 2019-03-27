package com.example.projectgamma;
//The java import statements
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.String.valueOf;

public class Claim_Form extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    //Variable assignments
    ArrayList selectedItems = new ArrayList();
    String course;
    String name;
    String stud_num;
    TextView Thevenue, TimeError, sel_Course;
    String type;
    String date;
    String venue;
    TextView tv;
    String[] topic = new String[3];
    private Spinner mySpinner;
    static int hour;
    static int minute;
     String startTime;
     String endTime;



    boolean check;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Selecting wich xml res file is called when calling the Claim_form class
        setContentView(R.layout.form);
        Thevenue = findViewById(R.id.enterVenue);
        TimeError = findViewById(R.id.TimeError);
        venue = Thevenue.getText().toString();
        tv = (TextView) findViewById(R.id.duration);
        Button button = (Button) findViewById(R.id.button);
        sel_Course = findViewById(R.id.Sel_Course);


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
                        topic = listItems[i].split(" ");
                        course = topic[0];
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


    boolean validate(String startTime,String endTime) {
        boolean valid = true;

        //Checks whether a venue has been entered
        if (Thevenue.length() == 0) {
            Thevenue.setError("Please enter the venue");
            valid = false;
        }
        //Checks if a course has been selected
        if (topic[0] == null) {
            sel_Course.setText("Please Select a course");
            valid = false;
        }
        //Checks if the start_time has been selected

        TextView start = (TextView) findViewById(R.id.start);
        TextView end = (TextView) findViewById(R.id.end);
        startTime=start.getText().toString();
        endTime=end.getText().toString();

System.out.println("Para"+startTime+"+"+endTime+"+");

        if(startTime.equals("0 : 00 ") || endTime.equals("0 : 00 ")) {
                TimeError.setText("Please enter a valid period for which you have tutored");
                valid=false;

        }

        return valid;

    }

    @SuppressLint("ValidFragment")
    public static class TimePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
    }


    public void buttonStartTime(View v) {
        check = true;
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    public void buttonEndTime(View v) {
        check = false;
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        TextView start = (TextView) findViewById(R.id.start);
        TextView end = (TextView) findViewById(R.id.end);


        if (check == true) {
            if(Integer.toString(minute).length()==1) {
                start.setText(hourOfDay + " : " + "0" + minute);
            }
            else{
                start.setText(hourOfDay + " : " + minute);
            }
        } if(check==false) {
            if (Integer.toString(minute).length() == 1) {
                end.setText(hourOfDay + " : " + "0" + minute);
            } else {
                end.setText(hourOfDay + " : " + minute);
            }
        }
        startTime = start.getText().toString();
        endTime = (String) end.getText();
        System.out.println("Non"+startTime+" "+endTime);
    }

    public void send(View view) {

        if (validate(startTime,endTime) == false) {
            Toast.makeText(Claim_Form.this, "Please fill in the form", Toast.LENGTH_LONG).show();
        } else {

            EditText e3 = findViewById(R.id.enterVenue);
            venue = e3.getText().toString();

            Intent intent = new Intent(this, qrGenerator.class);
//Sends the course,name,student number,venue and time to the qrGenerator Class

            qrGenerator.Global.setCourse(course);
            qrGenerator.Global.setName(name);
            qrGenerator.Global.setStudent_num(stud_num);
            qrGenerator.Global.setVenue(venue);
            qrGenerator.Global.setStartTime(startTime);
            qrGenerator.Global.setEndTime(endTime);



            startActivity(intent);

        }
    }
}

