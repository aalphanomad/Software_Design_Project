package com.example.projectgamma;
//The java import statements

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.example.projectgamma.qrGenerator.Global.GetCourse;
import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;
import static com.example.projectgamma.qrGenerator.Global.Get_Courses;
import static java.lang.String.valueOf;

public class Claim_Form extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    //Variable assignments
    ArrayList selectedItems = new ArrayList();
    String course, name, stud_num, type, venue, startTime, endTime, currentDate;
    TextView Thevenue, TimeError, sel_Course;
    String[] topic = new String[3];
    ArrayList get_courses3=new ArrayList();
    private Spinner mySpinner1,mySpinner2;
    static int hour,minute;
    boolean check;
    private List<String> activities;
    private List<String> courses;
    String activity_selected;
    String []listItems;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Selecting which xml res file is called when calling the Claim_form class
        setContentView(R.layout.form);
        Thevenue = findViewById(R.id.enterVenue);
        TimeError = findViewById(R.id.TimeError);
        venue = Thevenue.getText().toString();
       // Button button = findViewById(R.id.button);
        sel_Course = findViewById(R.id.Sel_Course);
          listItems =Get_Courses();
        mySpinner1 = (Spinner) findViewById(R.id.Activity_Spinner);
        mySpinner1.setOnItemSelectedListener(this);

        mySpinner2 = (Spinner) findViewById(R.id.Course_Spinner);
        mySpinner2.setOnItemSelectedListener(this);
        activities = new ArrayList<String>();
        activities.add("Tutoring");
        activities.add("Invigilating");
        activities.add("Other");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, activities);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(dataAdapter);

        //ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listItems);
        //dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //mySpinner2.setAdapter(dataAdapter1);


        Intent i = getIntent();
        //Recieves data from another class
        type = i.getStringExtra("insert");
        name = i.getStringExtra("name");
        stud_num = i.getStringExtra("student_num");

    }



    boolean validate(String startTime, String endTime) {
        boolean valid = true;

        //Checks whether a venue has been entered
        if (Thevenue.length() == 0) {
            Thevenue.setError("Please enter the venue");
            valid = false;
        }
        //Checks if a course has been selected
        if (topic[0]==null) {
            sel_Course.setTextColor(Color.parseColor("#ff0000"));
            sel_Course.setText("Please Select a course");

            valid = false;
        }


        //Checks if the start_time has been selected

        TextView start = (TextView) findViewById(R.id.start);
        TextView end = (TextView) findViewById(R.id.end);
        startTime = start.getText().toString();
        endTime = end.getText().toString();

        if (checktimings(startTime, endTime) == false) {
            valid = false;
            TimeError.setText("End time cannot be before start time");
        }


        if (startTime.equals("0 : 00 ") || endTime.equals("0 : 00 ")) {
            TimeError.setText("Please enter a valid period for which you have tutored");
            valid = false;

        }


        return valid;

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        activity_selected = parent.getItemAtPosition(position).toString();
        //topic = listItems[position].split(" ");
        course = topic[0];
        System.out.println("THE COURSE   "+course);



    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean checktimings(String startTime1, String endTime1) {

        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        startTime1 = startTime1.replaceAll("\\s+", "");
        endTime1 = endTime1.replaceAll("\\s+", "");
        try {
            Date date1 = sdf.parse(startTime1);
            Date date2 = sdf.parse(endTime1);

            startTime = startTime1;
            endTime = endTime1;
            if (date1.before(date2)) {
                return true;
            } else {

                return false;
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
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
            if (Integer.toString(minute).length() == 1) {
                start.setText(hourOfDay + " : " + "0" + minute);
            } else {
                start.setText(hourOfDay + " : " + minute);
            }
        }
        if (check == false) {
            if (Integer.toString(minute).length() == 1) {
                end.setText(hourOfDay + " : " + "0" + minute);
            } else {
                end.setText(hourOfDay + " : " + minute);
            }
        }
        startTime = start.getText().toString();
        endTime = (String) end.getText();

    }

    public void send(View view) {

        if (validate(startTime, endTime) == false) {
            Toast.makeText(Claim_Form.this, "Please fill in the form", Toast.LENGTH_LONG).show();
        } else {
//sel_Course.setText(listItems[mySpinner2.getSelectedItemPosition()]);
            course = topic[0];
            System.out.println("THE COURSE   "+course);
            EditText e3 = findViewById(R.id.enterVenue);
            venue = e3.getText().toString();

//Sends the course,name,student number,venue and time to the qrGenerator Class

            qrGenerator.Global.setCourse(course);
            //qrGenerator.Global.setName(name);
            //qrGenerator.Global.setStudent_num(stud_num);
            qrGenerator.Global.setVenue(venue);
            qrGenerator.Global.setStartTime(startTime);
            qrGenerator.Global.setEndTime(endTime);
            Calendar calender = Calendar.getInstance();
            currentDate = java.text.DateFormat.getDateInstance().format(calender.getTime());

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);

            backgroundWorker.execute("booking", name, stud_num, course, currentDate, venue, startTime, endTime,activity_selected);


        }
    }
}
