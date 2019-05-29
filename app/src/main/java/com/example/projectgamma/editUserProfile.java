package com.example.projectgamma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;

public class editUserProfile extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
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
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        //create array that takes thhe values of the courses the tutor contains
        courses = qrGenerator.Global.Get_Courses();
        //convert above array to arraylist so we can perform certain actions later on
        //arrayListofCourses = new ArrayList<String>(Arrays.asList(courses));
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //declare adapter for listview with simple layout that will show the courses tutored
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListofCourses);
        listview = findViewById(R.id.lv3);
//        listview.setAdapter(adapter);   //update listview
        setNavigationViewListener();


        //when a course in the listview is clicked the user will have the option to delete that specific course
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItem.trim().equals(arrayListofCourses.get(position).trim())) {
                    removeElement(selectedItem, position);  //remove funcion is called to remove certain course
                } else {
                    Toast.makeText(getApplicationContext(), "Error Removing Course", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void change(View view) {
            ArrayList test=new ArrayList();
            for(int i=0;i<5;i++){
                test.add(null);
            }
            for(int i=0;i<arrayListofCourses.size();i++){
                String[] dummy=arrayListofCourses.get(i).split(" ");
                test.set(i,dummy[0]);
            }
        String course1=null;
        String course2=null;
        String course3=null;
        String course4=null;
        String course5=null;

       if(test.get(0)!=null) {
            course1 = test.get(0).toString();
       }      if(test.get(1)!=null) {
            course2 = test.get(1).toString();
        }
            if(test.get(2)!=null) {
                course3 = test.get(2).toString();
            }
                if(test.get(3)!=null) {
                    course4 = test.get(3).toString();
                }
                    if(test.get(4)!=null) {
                         course5 = test.get(4).toString();
                    }

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute("edit courses", qrGenerator.Global.GetStudent_Num(), course1,course2,course3,course4,course5);
        }



    //function to remove the clicked on courses in the listview
    public void removeElement(String selectedItem, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + selectedItem + "?");   //if item is clicked ask user to confirm deletion
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayListofCourses.remove(position);    //remove item from arraylist
                listview.setAdapter(adapter);   //update the listview
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

        ArrayList allCourses = new ArrayList(); //create arraylist containing all possible courses
        //fill in the this array
        allCourses.addAll(Arrays.asList(new String[]{"COMS1015 (Basic Computer Organisation)", "COMS1018 (Introduction to Algorithms and Programming)", "COMS1017 (Introduction to Data Structures and Algorithms", "COMS1016 (Discrete Computational Structures)", "COMS2002 (Database Fundementals)", "COMS2013 (Mobile Computing)", "COMS2014 (Computer Networks)", "COMS2015 (Analysis of Algorithms)", "COMS3003 (Formal Languages and Automata)", "COMS3005 (Advanced Analysis of Algorithms)", "COMS3009 (Software Design)", "COMS3010 (Operating Systems and System Programming)", "COMS3007 (Machine Learning)", "COMS3006 (Computer Graphics and Visualisation)", "COMS3008 (Parallel Computing)", "COMS3011 (Software Design Project)"}));

        //create another arraylist that will represent all the courses the tutor does not take
        //equate it to allCourses array for now
        ArrayList availableCourses = allCourses;


        //in loop, check whether the allCourses arraylist contains the i-th element in the array representing the current courses we have
        //if it is true, then remove it from available courses since it is already taken by the tutor
        for(int i=0; i<arrayListofCourses.size(); i++){
            if (allCourses.toString().contains(arrayListofCourses.get(i))){
                availableCourses.remove(arrayListofCourses.get(i));
            }
        }


        android.support.v7.app.AlertDialog.Builder myBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        CharSequence[] The_courses = new CharSequence[50];
        //create an array of Charsequence to take the values of available courses so we can use it in the drop down list
        The_courses = (CharSequence[]) availableCourses.toArray(new CharSequence[0]);

        //make it final
        final CharSequence[] finalThe_courses = The_courses;
        myBuilder.setTitle("Proposed Courses").setMultiChoiceItems(The_courses, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                //Selects the labels of the courses seleted by the user
                if (isChecked == true) {
                    //if a certain item is selected to add to courses tutored, add it to the arraylist that already contains current curses taken
                    if (arrayListofCourses.size() < 5) {
                        arrayListofCourses.add(String.valueOf(finalThe_courses[position]));
                    }

                    else{
                        Toast.makeText(getApplicationContext(),"You cannot add any more courses!",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    //if unchecked remove from arraylist or do nt even add it
                    arrayListofCourses.remove(String.valueOf(finalThe_courses[position]));
                }
            }
        });
        myBuilder.setPositiveButton("Selected Items", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //iterate through our updated arraylist containg current courses
                StringBuilder sb = new StringBuilder();
                for (Object i : arrayListofCourses) {
                    listview.setAdapter(adapter);   //update the listview with the modified arraylist
                }
            }
        });
        myDialog = myBuilder.create();
        myDialog.show();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
        }

        return super.onOptionsItemSelected(item);

    }
    //Handles events related to the side-swipe feature
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Activity_History: {
                Intent myIntent = new Intent(editUserProfile.this, Claim_Form.class);
                editUserProfile.this.startActivity(myIntent);
                break;
            }
            case R.id.My_Schedule: {
                Intent myIntent = new Intent(editUserProfile.this, SecondFragment.class);
                editUserProfile.this.startActivity(myIntent);
                break;
            }

            case R.id.Activity: {
                Intent myIntent = new Intent(editUserProfile.this, Claim_Form.class);
                editUserProfile.this.startActivity(myIntent);
                break;
            }

            case R.id.user_profile: {
                Intent myIntent = new Intent(editUserProfile.this, ViewProfile.class);
                editUserProfile.this.startActivity(myIntent);
                break;
            }
            case R.id.Logout: {
                Intent myIntent = new Intent(editUserProfile.this, LoginActivity.class);
                editUserProfile.this.startActivity(myIntent);
                break;
            }
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.navigation);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.user_name);
        TextView navUserEmail = (TextView) headerView.findViewById(R.id.user_email);
        navUsername.setText(GetName());
        navUserEmail.setText(GetStudent_Num()+"@students.wits.ac.za");

        navigationView.setNavigationItemSelectedListener( editUserProfile.this);
    }
}