package com.example.projectgamma;

import android.app.Activity;
import android.app.Fragment;
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


import java.io.InputStream;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;


public class FirstFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    InputStream is = null;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();

    }








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
                FirstFragment.this.startActivity(myIntent);                break;
            }

            case R.id.Claim: {
                Intent myIntent = new Intent(FirstFragment.this, FourthFragment.class);
                FirstFragment.this.startActivity(myIntent);                break;
            }
            case R.id.settings: {
                Intent myIntent = new Intent(FirstFragment.this, FifthFragment.class);
                FirstFragment.this.startActivity(myIntent);                break;
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
        navUserEmail.setText(GetStudent_Num()+"@students.wits.ac.za");
    }

}

