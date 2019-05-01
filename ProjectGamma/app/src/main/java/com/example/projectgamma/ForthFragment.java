package com.example.projectgamma;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;


public class ForthFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    String name, stud_num, email;
    String[] courses;
    public static TextView  nameTV, stud_numTV, emailTV;
    ListView listview;
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        //mDrawerlayout.addDrawerListener(mToggle);
        //mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setNavigationViewListener();

        //recieving account details of tutor from global variables set in qrGenerator function
        name = qrGenerator.Global.GetName();
        stud_num = qrGenerator.Global.GetStudent_Num();
        email = stud_num+"@students.wits.ac.za";
        courses = qrGenerator.Global.Get_Courses();

        //declaring the textviews
        nameTV = (TextView)findViewById(R.id.tv_Name);
        stud_numTV = (TextView)findViewById(R.id.tv_stud_num2);
        emailTV = (TextView)findViewById(R.id.tv_email2);

        //setting textviews with the informatin we recieve from the global variables
        nameTV.setText(name);
        stud_numTV.setText(stud_num);
        emailTV.setText(email);

        //declare adapter for listview with simple layout
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, courses);
        listview = findViewById(R.id.lv2);
        listview.setAdapter(adapter);   //update listview with information in array
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
        }

        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Claim_History: {
                Intent myIntent = new Intent(ForthFragment.this, Claim_History.class);
                ForthFragment.this.startActivity(myIntent);
                break;
            }
            case R.id.My_Schedule: {
                Intent myIntent = new Intent(ForthFragment.this, MySchedule.class);
                ForthFragment.this.startActivity(myIntent);                break;
            }

            case R.id.Claim: {
                Intent myIntent = new Intent(ForthFragment.this, Claim_Form.class);
                ForthFragment.this.startActivity(myIntent);                break;
            }
            case R.id.user_profile: {
                Intent myIntent = new Intent(ForthFragment.this, ForthFragment.class);
                ForthFragment.this.startActivity(myIntent);                break;
            }
            case R.id.Logout: {
                Intent myIntent = new Intent(ForthFragment.this, LoginActivity.class);
                ForthFragment.this.startActivity(myIntent);
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
        navigationView.setNavigationItemSelectedListener( ForthFragment.this);

    }

    //button to start editing courses
    public void edit(View view) {
        Intent i = new Intent(ForthFragment.this, editUserProfile.class);
        ForthFragment.this.startActivity(i);
    }

    //button to take us home
    public void home(View view) {
        Intent i = new Intent(ForthFragment.this, HomeActivity.class);
        ForthFragment.this.startActivity(i);
    }
}