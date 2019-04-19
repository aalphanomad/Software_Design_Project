package com.example.projectgamma;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;
import static com.example.projectgamma.qrGenerator.Global.setGet_courses;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Initialization and assigning of vaariables

//Initializes a drawerlayout for the side-swipe feature
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    TextView name_label;
    Button Claims;
    String name;
    String stud_num;
    String[] get_courses1,get_courses2;
    ArrayList get_courses3=new ArrayList();
    String []str;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver1, new IntentFilter("INTENT_7"));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();

  //Recieves the name and student number
        name= GetName();
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

        Claims = findViewById(R.id.Scan_button);
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

        BackgroundWorker backgroundWorker = new BackgroundWorker(HomeActivity.this);
        backgroundWorker.execute("get courses", GetName(), GetStudent_Num());

    }
//Ensures you cannot go back to the login page when you are on the Home Page
    public void onBackPressed() {


    }

    private BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            get_courses1 = intent.getStringArrayExtra("get courses");

            for(int i=0;i<get_courses1.length;i++){
                get_courses2=get_courses1[i].split(":");
                get_courses3.add(get_courses2[1]);
            }

            get_courses3.set(get_courses3.size()-1,get_courses3.get(get_courses3.size()-1).toString().substring(0,(Integer)(get_courses3.get(get_courses3.size()-1).toString().length())-2));
            get_courses3.removeAll(Collections.singletonList("null"));


            //System.out.println(get_courses3);
            str = new String[get_courses3.size()];
            for (int j = 0; j < get_courses3.size(); j++) {
get_courses3.set(j,get_courses3.get(j).toString().substring(1,get_courses3.get(j).toString().length()-1));
                // Assign each value to String array
                str[j] = get_courses3.get(j).toString()+" "+MainActivity.Global.Course_corr(get_courses3.get(j).toString());
            }

            setGet_courses(str);
        }
    };
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
                Intent myIntent = new Intent(HomeActivity.this, Claim_History.class);
                HomeActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.MySchedule: {
                Intent myIntent = new Intent(HomeActivity.this, adminViewCourses.class);
                HomeActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.Claim: {
                Intent myIntent = new Intent(HomeActivity.this, Claim_Form.class);
                HomeActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.user_profile: {
                Intent myIntent = new Intent(HomeActivity.this, ForthFragment.class);
                HomeActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.Logout: {
                Intent myIntent = new Intent(HomeActivity.this, LoginActivity.class);
                HomeActivity.this.startActivity(myIntent);
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

        navigationView.setNavigationItemSelectedListener( HomeActivity.this);
    }
}
