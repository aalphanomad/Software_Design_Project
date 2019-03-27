package com.example.projectgamma;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Arrays;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;


public class FirstFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    InputStream is = null;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    String[] dates, courses, start_time, end_time, venue, valid;
    ListView listview;

/*
    //gets value from claims form generator for now
    String subject = qrGenerator.Global.GetCourse();
    String date = qrGenerator.Global.GetDate();
    String venue = qrGenerator.Global.GetVenue();
    String duration;


    //stores values into arrays to pass to the ArrayAdapter

    String[] mDate = {date};
    String[] mSubject = {subject};
    String[] mVenue = {venue};
    String[] mDuration = {duration};
*/


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        listview = findViewById(R.id.lv);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver1, new IntentFilter("INTENT_1"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver2, new IntentFilter("INTENT_2"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver3, new IntentFilter("INTENT_3"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver4, new IntentFilter("INTENT_4"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver5, new IntentFilter("INTENT_5"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver6, new IntentFilter("INTENT_6"));


        //call adapter class to take in our arrays
        //MyAdapter adapter = new MyAdapter(this, dates, courses, venue,start_time);
        //listview.setAdapter(adapter);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        String type = "fetching";
        String name = qrGenerator.Global.GetName();
        String student_no = qrGenerator.Global.GetStudent_Num();
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, name, student_no);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();

    }

    private BroadcastReceiver mReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            dates = intent.getStringArrayExtra("dates");
            //System.out.println(Arrays.toString(dates));
        }
    };
    private BroadcastReceiver mReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            courses = intent.getStringArrayExtra("courses");
            System.out.println(Arrays.toString(courses));
        }
    };
    private BroadcastReceiver mReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            start_time = intent.getStringArrayExtra("start_time");
            System.out.println(Arrays.toString(start_time));
        }
    };
    private BroadcastReceiver mReceiver4 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            end_time = intent.getStringArrayExtra("end_time");
            System.out.println(Arrays.toString(end_time));
        }
    };
    private BroadcastReceiver mReceiver5 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            venue = intent.getStringArrayExtra("venue");
            System.out.println(Arrays.toString(venue));
        }
    };
    private BroadcastReceiver mReceiver6 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            valid = intent.getStringArrayExtra("valid");
            System.out.println(Arrays.toString(valid));
        }
    };
/*
    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String rDate[];
        String rSubject[];
        String rVenue[];
        String rDuration[];

        //class will take in the above arrays 
        MyAdapter(Context c, String date[], String subject[], String venue[], String duration[]){
            super(c, R.layout.customlist, R.id.dateHistory, date);
            this.context = c;
            this.rDate = date;
            this.rSubject = subject;
            this.rVenue = venue;
            this.rDuration = duration;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.customlist, parent, false);

            //declare our textviews
            TextView theDate = row.findViewById(R.id.dateHistory);
            TextView theSubject = row.findViewById(R.id.subjectHistory);
            TextView theVenue = row.findViewById(R.id.venueHistory);
            TextView theDuration = row.findViewById(R.id.durationHistory);
            TextView theStatus = row.findViewById(R.id.status);

            //set our textviews with values from the arrays passed in
            theDate.setText(rDate[position]);
            theSubject.setText("Subject: " + rSubject[position]);
            theVenue.setText("Venue: " + rVenue[position]);
            theDuration.setText("Duration: " + rDuration[position]);
            //if tutor session has been validated then set text to:
            //theStatus.setText("Status: Validated");


            return row;
        }
    }

*/


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MyCourses: {
                Intent myIntent = new Intent(FirstFragment.this, FirstFragment.class);
                FirstFragment.this.startActivity(myIntent);
                break;
            }
            case R.id.MySchedule: {
                Intent myIntent = new Intent(FirstFragment.this, MySchedule.class);
                //myIntent.putExtra("user_id", "" + user_id);
                FirstFragment.this.startActivity(myIntent);
                break;
            }

            case R.id.Claim: {
                Intent myIntent = new Intent(FirstFragment.this, FourthFragment.class);
                FirstFragment.this.startActivity(myIntent);
                break;
            }
            case R.id.settings: {
                Intent myIntent = new Intent(FirstFragment.this, FifthFragment.class);
                FirstFragment.this.startActivity(myIntent);
                break;
            }
            case R.id.Logout: {
                Intent myIntent = new Intent(FirstFragment.this, LoginActivity.class);
                FirstFragment.this.startActivity(myIntent);
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
        navUserEmail.setText(GetStudent_Num() + "@students.wits.ac.za");
    }

}

