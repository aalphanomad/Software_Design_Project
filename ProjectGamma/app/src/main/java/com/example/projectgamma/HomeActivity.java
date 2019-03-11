package com.example.projectgamma;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Initialization and assigning of vaariables

//Initializes a drawerlayout for the side-swipe feature
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    TextView name_label;
    Button Claims;
    String name;
    String stud_num;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();

  //Recieves the name and student number
        name=qrGenerator.Global.GetName();
        stud_num=qrGenerator.Global.GetStudent_Num();


        //Dont worry  about this,this is for the side-swipe feature which we will use later
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);

        //Sets the label to Display the user's name
        name_label = findViewById(R.id.Name_TB);
        if (name_label.length() != 0 || name.length() != 0) {
            name_label.setText(name.toUpperCase().charAt(0) + name.substring(1, name.length()) + "!");
        }

        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Claims = findViewById(R.id.Claims_but);
        Claims.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, Claim_Form.class);
                //Sends the name and student number to the claims form class
                i.putExtra("insert", "insert");
                i.putExtra("name", name);
                i.putExtra("student_num", stud_num);
                HomeActivity.this.startActivity(i);

            }
        });

        setNavigationViewListener();
    }
//Ensures you cannot go back to the login page when you are on the Home Page
    public void onBackPressed() {


    }
//used for the side-swipe feature,dont worry about it now
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
            case R.id.MyCourses: {
                Intent myIntent = new Intent(HomeActivity.this, FirstFragment.class);
                HomeActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.MySchedule: {
                Intent myIntent = new Intent(HomeActivity.this, MySchedule.class);
                HomeActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.Claim: {
                Intent myIntent = new Intent(HomeActivity.this, FourthFragment.class);
                HomeActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.settings: {
                Intent myIntent = new Intent(HomeActivity.this, FifthFragment.class);
                HomeActivity.this.startActivity(myIntent);
                break;
            }
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener( HomeActivity.this);
    }
}
