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


import java.io.InputStream;



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
        populateListView();
        registerClickCallBack();
        setNavigationViewListener();

    }

    private void registerClickCallBack() {
        ListView list=(ListView)findViewById(R.id.listviewmain);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>paret,View viewClicked,int position,long id){
                //TextView textView=(TextView)viewClicked;
                //String message="You clicked #"+position +" which is string:"+textView.getText().toString();
            //Toast.makeText(FirstFragment.this,message,Toast.LENGTH_LONG).show();
                switch(position) {
                    case 0:
                        Intent HomeIntent1 = new Intent(FirstFragment.this, MyList.class);
                        startActivity(HomeIntent1);
                        HomeIntent1.putExtra("GRADE","8");

                        finish();
                        //
                        break;
                    case 1:
                        Intent HomeIntent2 = new Intent(FirstFragment.this, MyList.class);
                        HomeIntent2.putExtra("GRADE","9");

                        startActivity(HomeIntent2);
                         finish();
                        break;
                        case 2:
                            Intent HomeIntent3 = new Intent(FirstFragment.this, MyList.class);
                            HomeIntent3.putExtra("GRADE","10");

                            startActivity(HomeIntent3);
                             finish();
                        break;
                        case 3:
                            Intent HomeIntent4 = new Intent(FirstFragment.this, MyList.class);
                            HomeIntent4.putExtra("GRADE","11");

                            startActivity(HomeIntent4);
                             finish();
                        break;
                       case 4:
                        Intent HomeIntent5 = new Intent(FirstFragment.this, MyList.class);
                           HomeIntent5.putExtra("GRADE","12");

                           startActivity(HomeIntent5);
                         finish();
                        break;
                }

            }

        });


    }

    private void populateListView(){
        String[] Grades={"Grade 8","Grade 9","Grade 10","Grade 11","Grade 12"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Grades);
ListView list=(ListView)findViewById(R.id.listviewmain);
list.setAdapter(adapter);
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
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) FirstFragment.this);
    }

}

