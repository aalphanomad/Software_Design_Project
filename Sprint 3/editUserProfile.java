package com.example.projectgamma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class editUserProfile extends AppCompatActivity {
    ListView listview;
    android.support.v7.app.AlertDialog myDialog;
    ArrayAdapter<String> adapter = null;
    ArrayList<String> arrayListofCourses = null;
    String[] courses;
    String item;

    private List<String> allCourses;
    ArrayList selectedItems = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        courses = qrGenerator.Global.Get_Courses();
        arrayListofCourses = new ArrayList<String>(Arrays.asList(courses));

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListofCourses);
        listview = findViewById(R.id.lv3);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItem.trim().equals(arrayListofCourses.get(position).trim())) {
                    removeElement(selectedItem, position);
                } else {
                    Toast.makeText(getApplicationContext(), "Error Removing Course", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void change(View view) {
    }

    public void removeElement(String selectedItem, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayListofCourses.remove(position);
                listview.setAdapter(adapter);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void addCourse(View view) {

        ArrayList allCourses = new ArrayList();
        allCourses.addAll(Arrays.asList(new String[]{"COMS1015 (Basic Computer Organisation)", "COMS1018 (Introduction to Algorithms and Programming)", "COMS1017 (Introduction to Data Structures and Algorithms", "COMS1016 (Discrete Computational Structures)", "COMS2002 (Database Fundementals)", "COMS2013 (Mobile Computing)", "COMS2014 (Computer Networks)", "COMS2015 (Analysis of Algorithms)", "COMS3003 (Formal Languages and Automata)", "COMS3005 (Advanced Analysis of Algorithms)", "COMS3009 (Software Design)", "COMS3010 (Operating Systems and System Programming)", "COMS3007 (Machine Learning)", "COMS3006 (Computer Graphics and Visualisation)", "COMS3008 (Parallel Computing)", "COMS3011 (Software Design)"}));
        ArrayList availableCourses = allCourses;

        for(int i=0; i<arrayListofCourses.size(); i++){
            if (allCourses.toString().contains(arrayListofCourses.get(i))){
                availableCourses.remove(arrayListofCourses.get(i));
            }
        }


        //Displays the courses in the popup spinner
            android.support.v7.app.AlertDialog.Builder myBuilder = new android.support.v7.app.AlertDialog.Builder(this);
            CharSequence[] The_courses = new CharSequence[50];

                The_courses = (CharSequence[]) availableCourses.toArray(new CharSequence[0]);

            final CharSequence[] finalThe_courses = The_courses;
            myBuilder.setTitle("Proposed Courses").setMultiChoiceItems(The_courses, null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                    //Selects the labels of the courses seleted by the user
                    if (isChecked == true) {
                        arrayListofCourses.add(String.valueOf(finalThe_courses[position]));

                    } else {
                        arrayListofCourses.remove(String.valueOf(finalThe_courses[position]));
                    }
                }
            });
            myBuilder.setPositiveButton("Selected Items", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    StringBuilder sb = new StringBuilder();
                    for (Object i : arrayListofCourses) {
                        listview.setAdapter(adapter);
                    }
                }
            });
            myDialog = myBuilder.create();
            myDialog.show();

        }
    }
