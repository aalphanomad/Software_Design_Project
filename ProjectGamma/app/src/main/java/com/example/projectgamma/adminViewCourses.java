package com.example.projectgamma;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class adminViewCourses extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner mySpinner;
    private List<String> courses;
    String item;

    ListView listview;
    ArrayAdapter<String> adapter = null;

    //array to be used in courses to add in radio button
    CharSequence[] The_courses = new CharSequence[50];
    //arrays that contain all possible courses
    CharSequence[] COMS_Courses = {"COMS1015 (Basic Computer Organisation)", "COMS1018 (Introduction to Algorithms and Programming)", "COMS1017 (Introduction to Data Structures and Algorithms", "COMS1016 (Discrete Computational Structures)", "COMS2002 (Database Fundementals)", "COMS2013 (Mobile Computing)", "COMS2014 (Computer Networks)", "COMS2015 (Analysis of Algorithms)", "COMS3003 (Formal Languages and Automata)", "COMS3005 (Advanced Analysis of Algorithms)", "COMS3009 (Software Design)", "COMS3010 (Operating Systems and System Programming)", "COMS3007 (Machine Learning)", "COMS3006 (Computer Graphics and Visualisation)", "COMS3008 (Parallel Computing)", "COMS3011 (Software Design)"};
    CharSequence[] CAM_Courses = {"APPM1006", "APPM1025", "APPM2007", "CAM3017"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminviewcourses);

        //Initializes a Spinner which is used to select the courses selected by the tutor
        mySpinner = (Spinner) findViewById(R.id.Degree_Spinner2);
        mySpinner.setOnItemSelectedListener(this);
        courses = new ArrayList<String>();
        courses.add("COMS");
        courses.add("CAM");
        //code used to help initialize the spinner(Which array to display
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();   //item gets string value of the selected option from the spinner

        if (item == "COMS") {   //if item on spinner is coms assign our main array to contain coms courses
            The_courses = COMS_Courses;
        } else if (item == "CAM") {   //if item on spinner is coms assign our main array to contain cam courses
            The_courses = CAM_Courses;
        }

        //initialise adapter to takke on the values of the main array and update our listview
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, The_courses);
        listview = findViewById(R.id.lv_course);
        listview.setAdapter(adapter);


        //give on click feature on each subject to view more information
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = The_courses[position].toString(); //get string of the clicked subject
                //send this string to the activity that follows
                Intent i = new Intent(getApplicationContext(), adminViewTutors.class);
                i.putExtra("courseView", selected);
                startActivity(i);
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}