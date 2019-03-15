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

import java.util.ArrayList;
import java.util.Calendar;

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


    @SuppressLint("ValidFragment")
    public static class TimePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

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
        System.out.println(check);
        TextView start = (TextView) findViewById(R.id.start);
        TextView end = (TextView) findViewById(R.id.end);
        if (check == true) {
            start.setText(hourOfDay + " : " + minute);
        } else {
            end.setText(hourOfDay + " : " + minute);
        }

    }


    public void send(View view) {
        final String startTime;
        final String endTime;
        TextView start = (TextView) findViewById(R.id.start);
        TextView end = (TextView) findViewById(R.id.end);

        startTime = start.getText().toString();
        endTime = end.getText().toString();
        System.out.println(Thevenue);

        //Checks whether a venue has been entered
        if (Thevenue.length() == 0) {
            Thevenue.setError("Please enter the venue");
        }
        //Checks if a course has been selected
        else if (topic[0] == null) {
            sel_Course.setText("Please Select a course");
        }
        //Checks if a duration has been selected
        else if (startTime.equals("0: 00") && endTime.equals("0: 00")) {
            TimeError.setText("Please enter the duration that you have tutored");
        } else {
            EditText e3 = findViewById(R.id.enterVenue);

            venue = e3.getText().toString();

            Intent intent = new Intent(this, qrGenerator.class);
            //String time = hourString + "hrs" + ":" + min1String + min2String + "mins";
//Sends the course,name,student numbber,venue and time to the qrGenerator Class
            intent.putExtra("course", course);
            intent.putExtra("name", name);
            intent.putExtra("student_num", stud_num);
            intent.putExtra("venue", venue);
            intent.putExtra("startTime", startTime);
            intent.putExtra("endTime", endTime);

            startActivity(intent);

        }
    }
}
