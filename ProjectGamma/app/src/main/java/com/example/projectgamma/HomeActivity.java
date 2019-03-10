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

import java.io.InputStream;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String[] names = {"Amy", "John"};
    InputStream is = null;
    String line = null;
    String result = null;
    String temp = "";
    String[] arr;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    TextView dummy;
    Button Claims;
    String name;
    String stud_num;

    /**
     * this method runs when the activity is started
     * it simply displays thee student number and name of the currently logged in user
     *
     * it also creates a button that will allow the user to generate a new claims form
     *
     * @param savedInstanceState
     */
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();

      //  name = i.getStringExtra("name");
      //  stud_num = i.getStringExtra("stud_num");
        name=qrGenerator.Global.GetName();
        stud_num=qrGenerator.Global.GetStudent_Num();
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        dummy = findViewById(R.id.Name_TB);
        if (dummy.length() != 0 || name.length() != 0) {
            dummy.setText(name.toUpperCase().charAt(0) + name.substring(1, name.length()) + "!");
        }

        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Claims = findViewById(R.id.Claims_but);
        Claims.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, Claim_Form.class);

                i.putExtra("insert", "insert");

                i.putExtra("name", name);
                i.putExtra("student_num", stud_num);
                HomeActivity.this.startActivity(i);

            }
        });

        setNavigationViewListener();
    }

    public void onBackPressed() {


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        System.out.println("Testing" + item.getItemId());
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
