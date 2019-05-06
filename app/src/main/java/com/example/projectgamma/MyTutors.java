package com.example.projectgamma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;


public class MyTutors extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    Button scan;
    String Course1;
    String Course2;
    String Course3;
    String Course4;
    String Course5;

    protected void onCreate(Bundle savedInstanceState) {
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver1, new IntentFilter("INTENT_10"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver2, new IntentFilter("INTENT_11"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver3, new IntentFilter("INTENT_12"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver4, new IntentFilter("INTENT_13"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver5, new IntentFilter("INTENT_14"));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutors);
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        BackgroundWorker backgroundWorker = new BackgroundWorker(MyTutors.this);
        backgroundWorker.execute("get tutors",GetStudent_Num());


        setNavigationViewListener();

    }


    private BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Course1 = intent.getStringExtra("Course1");
            System.out.println("DIE");
            System.out.println(Course1);

        }
    };
    private BroadcastReceiver mReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Course2 = intent.getStringExtra("Course2");

        }
    };

    private BroadcastReceiver mReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Course3 = intent.getStringExtra("Course3");


        }
    };

    private BroadcastReceiver mReceiver4 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Course4 = intent.getStringExtra("Course4");


        }
    };

    private BroadcastReceiver mReceiver5 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Course5 = intent.getStringExtra("Course5");


        }
    };
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
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MyCourses: {
                Intent myIntent = new Intent(MyTutors.this, Claim_History.class);
                MyTutors.this.startActivity(myIntent);
                break;
            }
            case R.id.MySchedule: {
                Intent myIntent = new Intent(MyTutors.this, MySchedule.class);
                MyTutors.this.startActivity(myIntent);
                break;
            }
            case R.id.Claim: {
                Intent myIntent = new Intent(MyTutors.this, Claim_Form.class);
                MyTutors.this.startActivity(myIntent);
                break;
            }
            case R.id.user_profile: {
                Intent myIntent = new Intent(MyTutors.this, MyTutors.class);
                MyTutors.this.startActivity(myIntent);
                break;
            }
            case R.id.Logout: {
                Intent myIntent = new Intent(MyTutors.this, LoginActivity.class);
                MyTutors.this.startActivity(myIntent);
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

        navigationView.setNavigationItemSelectedListener( MyTutors.this);
    }



}