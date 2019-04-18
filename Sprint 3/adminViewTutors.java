package com.example.projectgamma;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class adminViewTutors extends AppCompatActivity {

    ListView listview;
    ArrayAdapter<String> adapterOfLecturers = null;
    ArrayAdapter<String> adapterOfTutors = null;
    String courseName, lecturerName;
    TextView courseTV, lecturerTV;
    String[] tutors = {"Hamza", "Abdullah", "Mayur", "Innocent", "Elgoni"};
    String[] lecturers = {"Mr 1", "Mr 2", "Mr 3", "Dr Opo", "Prof Marubini"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminviewtutors);

        //recieve value of string of the course that was clicked on in the previous activity
        Bundle bundle = getIntent().getExtras();
        courseName=bundle.getString("courseView");

        //set the textview to show the clicked on cours
        courseTV = (TextView)findViewById(R.id.tv_course2);
        courseTV.setText(courseName);

        //the adapter initialised for our lecturer array to show all lecturers of the specific course in the listview
        adapterOfLecturers = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lecturers);
        listview = findViewById(R.id.lv_lecturers);
        listview.setAdapter(adapterOfLecturers);

        //the adapter initialised for our tutors array to show all tutors of the specific course in the listview
        adapterOfTutors = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tutors);
        listview = findViewById(R.id.lv_tutors);
        listview.setAdapter(adapterOfTutors);
    }
}
