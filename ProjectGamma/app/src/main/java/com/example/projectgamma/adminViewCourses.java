package com.example.projectgamma;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;

public class adminViewCourses extends AppCompatActivity implements AdapterView.OnItemSelectedListener,NavigationView.OnNavigationItemSelectedListener  {
    private Spinner mySpinner;
    private List<String> list=new ArrayList<>();
    String[] Cleancourses,Cleancodes;

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    String item;

    ListView listview;
    ArrayAdapter<String> adapter = null;

    //array to be used in courses to add in radio button
    CharSequence[] The_courses = new CharSequence[50];
    //arrays that contain all possible courses
    CharSequence[] COMS_Courses = {"COMS1015 (Basic Computer Organisation)", "COMS1018 (Introduction to Algorithms and Programming)", "COMS1017 (Introduction to Data Structures and Algorithms", "COMS1016 (Discrete Computational Structures)", "COMS2002 (Database Fundementals)", "COMS2013 (Mobile Computing)", "COMS2014 (Computer Networks)", "COMS2015 (Analysis of Algorithms)", "COMS3003 (Formal Languages and Automata)", "COMS3005 (Advanced Analysis of Algorithms)", "COMS3009 (Software Design)", "COMS3010 (Operating Systems and System Programming)", "COMS3007 (Machine Learning)", "COMS3006 (Computer Graphics and Visualisation)", "COMS3008 (Parallel Computing)", "COMS3011 (Software Design)"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminviewcourses);

        //Initializes a Spinner which is used to select the courses selected by the tutor

        //code used to help initialize the spinner(Which array to display
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            String login_url = "http://lamp.ms.wits.ac.za/~s1601745/get_all_courses.php";
            String post_data = "";
            //Initialize an HTTP POST connection to send data to the server
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

//Retrieves the informations passed from other classes to BackgroundWorker if we are trying to register

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            try {
                JSONObject ja = new JSONObject(result);
                String Allcourses = ja.getString("course_name").toString().substring(1, ja.get("course_name").toString().length() - 1).replace("\"", "");
                String Allcodes = ja.getString("course_code").toString().substring(1, ja.get("course_code").toString().length() - 1).replace("\"", "");
                Cleancourses = Allcourses.split(",");
                Cleancodes = Allcodes.split(",");
                for (int i = 0; i < Cleancourses.length; i++) {
                    list.add(Cleancodes[i] + "-(" + Cleancourses[i] + ")");
                }
                Collections.sort(list);
                Arrays.sort(Cleancodes);
                System.out.println(list);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        //declare adapter for listview with simple layout
        //initialise adapter to takke on the values of the main array and update our listview
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview = findViewById(R.id.lv_course);
        listview.setAdapter(adapter);
        //when a course in the listview is clicked the user will have the option to delete that specific course
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                System.out.println("SELECTED "+list.get(position));
                String[] info=list.get(position).split("-");
                //send this string to the activity that follows
                Intent i = new Intent(getApplicationContext(), adminViewTutors.class);
                i.putExtra("courseView", info[0]);
                startActivity(i);

            }
        });

        setNavigationViewListener();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();   //item gets string value of the selected option from the spinner



        //give on click feature on each subject to view more information


    }
    //Ensures you cannot go back to the login page when you are on the Home Page
    public void onBackPressed() {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.View_Everything: {
                break;
            }

            case R.id.Logout: {
                Intent myIntent = new Intent(adminViewCourses.this, LoginActivity.class);
                adminViewCourses.this.startActivity(myIntent);
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

        navigationView.setNavigationItemSelectedListener( adminViewCourses.this);
    }
}