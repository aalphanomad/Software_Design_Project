package com.example.projectgamma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by 1601745 on 2018/05/03.
 */

public class ThirdFragment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
        }

        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        System.out.println("Testing"+item.getItemId());
        switch (item.getItemId()) {
            case R.id.MyCourses: {
                Intent myIntent = new Intent(ThirdFragment.this, FirstFragment.class);
                ThirdFragment.this.startActivity(myIntent);
                break;
            }
            case R.id.MySchedule: {
                Intent myIntent = new Intent(ThirdFragment.this, MySchedule.class);
                ThirdFragment.this.startActivity(myIntent);                break;
            }

            case R.id.Claim: {
                Intent myIntent = new Intent(ThirdFragment.this, FourthFragment.class);
                ThirdFragment.this.startActivity(myIntent);                break;
            }
            case R.id.settings: {
                Intent myIntent = new Intent(ThirdFragment.this, FifthFragment.class);
                ThirdFragment.this.startActivity(myIntent);                break;
            }
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) ThirdFragment.this);
    }
    }
