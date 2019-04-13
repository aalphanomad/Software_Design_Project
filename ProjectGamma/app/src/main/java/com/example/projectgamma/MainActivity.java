package com.example.projectgamma;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    public static class Global {
public static String Course_corr(String course_code){
    String ans=null;
    System.out.println("Hash"+course_code);
    switch(course_code){
        case "COMS1015":
            ans= "(Basic Computer Organisation)";
            break;
        case "COMS1018":
            ans= "(Introduction to Algorithms and Programming)";
            break;
        case "COMS1017":
            ans= "(Introduction to Data Structures and Algorithms)";
            break;
        case "COMS1016":
            ans= "(Discrete Computational Structures)";
            break;
        case "COMS2002":
            ans= "(Database Fundamentals)";
            break;
        case "COMS2013":
            ans= "(Mobile Computing)";
            break;
        case "COMS2014":
            ans= "(Computer Networks)";
            break;
        case "COMS2015":
            ans= "(Analysis of Algorithms)";
            break;
        case "COMS3002":
            ans= "(Software Engineering)";
            break;
        case "COMS3003":
            ans= "(Formal Languages and Automata)";
            break;
        case "COMS3005":
            ans= "(Advanced Analysis of Algorithms)";
            break;
        case "COMS3009":
            ans= "(Software Design)";
            break;
        case "COMS3010":
            ans= "(Operating Systems and System Programming)";
            break;
        case "COMS3007":
            ans= "(Machine Learning)";
            break;
        case "COMS3006":
            ans= "(Computer Graphics and Visualisation)";
            break;
        case "COMS3008":
            ans= "(Parallel Computing)";
            break;
        case "COMS3011":
            ans= "(Software Design Project)";
            break;


    }
    return ans;

}
    }
    private static int SPLASH_TIME_OUT = 2000;


    //Creates the splash screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent HomeIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(HomeIntent);
                    finish();


            }
        }, SPLASH_TIME_OUT);
    }
}


