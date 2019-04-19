package com.example.projectgamma;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetRole;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;


public class LecturerHome extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    Button scan;
    String role=GetRole();

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_view);
        TextView dummy;
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        Intent i = getIntent();
        String test=qrGenerator.Global.GetName();
        dummy = findViewById(R.id.Name_TB);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //sets the name label to the user's name
        dummy.setText(test.toUpperCase().charAt(0) +test.substring(1,test.length())+"!");
        scan = findViewById(R.id.Scan_button);
        if(role.equals("1")) {
            scan.setText("Scan QR Code");
            scan.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(LecturerHome.this, qrScanner.class);
                    LecturerHome.this.startActivity(i);
                }
            });
        }

        if(role.equals("2")) {
            scan.setText("View All Details");
            scan.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(LecturerHome.this, adminViewCourses.class);
                    LecturerHome.this.startActivity(i);
                }
            });
        }




        setNavigationViewListener();

    }


    //Prevents the user from going back to the login screen from the homescreen
public void onBackPressed(){

}
    //used for the side-swipe feature,dont worry about it now
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
        }

        return super.onOptionsItemSelected(item);

    }
    //Handles events related to the side-swipe feature
    //SPLIT FOR LECTURER AND ADMIN HERE!!!!!!!!!!!!!!!!
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Scan_QR: {
                Intent myIntent = new Intent(LecturerHome.this, qrScanner.class);
                LecturerHome.this.startActivity(myIntent);
                break;
            }
            case R.id.View_Tutors: {
                Intent myIntent = new Intent(LecturerHome.this, MyTutors.class);
                LecturerHome.this.startActivity(myIntent);
                break;
            }
            case R.id.Logout: {
                Intent myIntent = new Intent(LecturerHome.this, LoginActivity.class);
                LecturerHome.this.startActivity(myIntent);
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

        navigationView.setNavigationItemSelectedListener( LecturerHome.this);
    }



   }