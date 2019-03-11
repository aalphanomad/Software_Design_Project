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
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView lv;
    String[]names={"Amy","John"};
    InputStream is=null;
    String line=null;
    String result=null;
    String temp="";
    String[]arr;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();

        int id = i.getIntExtra("user_id",-1);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv=(ListView)findViewById(R.id.mylv);
        lv.setAdapter(new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,names));
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
/*
        try{
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://lamp.ms.wits.ac.za/~s1601745/student.php");
            HttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity();
            is=entity.getContent();
        }catch(Exception e){
            System.out.println("Error");
        }
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb=new StringBuilder();
            while((line=reader.readLine())!=null)
                sb.append(line+"\n");
            result=sb.toString();
            is.close();
            System.out.print("Here is the data");
            System.out.print(result);
            JSONArray jArray=new JSONArray(result);
            int count=jArray.length();
            for(int t=0;t<count;t++){
                JSONObject json_data=jArray.getJSONObject(t);
                temp+=json_data.getString("USERNAME")+":";
                arr=temp.split(":");
                System.out.print(arr.toString());
                lv.setAdapter(new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,arr));
            }


        }catch(Exception e){
            System.out.print("Error 2");
        }
*/
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
                Intent myIntent = new Intent(HomeActivity.this, FirstFragment.class);
               HomeActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.MySchedule: {
                Intent myIntent = new Intent(HomeActivity.this, MySchedule.class);
                HomeActivity.this.startActivity(myIntent);                break;
            }
            case R.id.Claim: {
                Intent myIntent = new Intent(HomeActivity.this, FourthFragment.class);
                HomeActivity.this.startActivity(myIntent);                break;
            }
            case R.id.settings: {
                Intent myIntent = new Intent(HomeActivity.this, FifthFragment.class);
                HomeActivity.this.startActivity(myIntent);                break;
            }
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) HomeActivity.this);
    }
}
