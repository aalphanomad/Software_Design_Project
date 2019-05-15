package com.example.projectgamma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;


public class MyTutors extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    String result;
    String login_url=null;
    String post_data=null;

    Button mycourses;
    String[] Course1;
    String[] Course2;
    String[] Course3;
    String[] Course4;
    String[] Course5;
    String MyCourses;
    List<String> course_arr;
TextView Message;
ArrayList dummy=new ArrayList();
    ListView listview;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutors);
        Message=findViewById(R.id.Message);

        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mycourses=findViewById(R.id.Select_Course);
        listview = findViewById(R.id.lv);


        try {

            //The code below recieves input from other classes to use the BackgroundWorker to send Booking/Claim form information to the server
            String student_num = qrGenerator.Global.GetStudent_Num();

            //The URL to send data to the server when creating a booking/claim form


            login_url = "http://lamp.ms.wits.ac.za/~s1601745/get_students.php?";
            post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8");
            URL url = new URL(login_url);
            //The code below initializes an HTP POST request
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            //Creating the URL in order to send data for generating a claim form to the server
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject ja = new JSONObject(result);
             Course1=ja.getString("Course1").split(",");
             Course2=ja.getString("Course2").split(",");
             Course3=ja.getString("Course3").split(",");
             Course4=ja.getString("Course4").split(",");
             Course5=ja.getString("Course5").split(",");

             MyCourses=ja.getString("Courses");

            course_arr= Arrays.asList(MyCourses.split(","));
            course_arr.set(course_arr.size()-1,course_arr.get(course_arr.size()-1).substring(0,course_arr.get(course_arr.size()-1).length()-1));
            course_arr.set(0,course_arr.get(0).substring(1,course_arr.get(0).length()));
            for(int i=0;i<course_arr.size();i++){

                if(!course_arr.get(i).equals("null")){
                    dummy.add(course_arr.get(i).substring(1,course_arr.get(i).length()-1));
                }
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
        mycourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MyTutors.this);

                mBuilder.setTitle("Select a Course");
                String[] dummy2 = (String[]) dummy.toArray(new String[dummy.size()]);
                mBuilder.setSingleChoiceItems(dummy2, -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("THE I "+i);
                        if(i==0) {
                            if(Course1!=null) {
                                Course1 = qrGenerator.Global.formatter(Course1);
                                listview.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course1));
                                dialogInterface.dismiss();

                            }
                            else{
                                ImageView imgView=(ImageView) findViewById(R.id.imageView5);
                                Drawable drawable  = getResources().getDrawable(R.drawable.mag_glass);
                                imgView.setImageDrawable(drawable);
                                Message.setText("It's Empty Here...");
                            }
                        }
                        if(i==1) {
                            if(Course2!=null){
                                Course2 = qrGenerator.Global.formatter(Course2);
                                listview.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course2));
                                dialogInterface.dismiss();

                            }
                        }
                        if(i==2) {
                            if (Course3!=null) {
                                Course3 = qrGenerator.Global.formatter(Course3);
                                listview.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course3));
                                dialogInterface.dismiss();

                            }
                        }
                        if(i==3) {
                            if (Course4.length != 0) {
                                Course4 = qrGenerator.Global.formatter(Course4);
                                listview.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course4));
                                dialogInterface.dismiss();

                            }
                            }
                        if(i==4) {
                            if (Course5.length != 0) {
                                Course5 = qrGenerator.Global.formatter(Course5);
                                listview.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course5));
                                dialogInterface.dismiss();

                            }
                        }

                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }

        });
        setNavigationViewListener();

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
            case R.id.Scan_QR: {
                Intent myIntent = new Intent(MyTutors.this, qrScanner.class);
                MyTutors.this.startActivity(myIntent);
                break;
            }
            case R.id.View_Tutors: {
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

    public void scan(View view) {
        Intent i = new Intent(MyTutors.this, qrScanner.class);
        MyTutors.this.startActivity(i);
    }

    public void onBackPressed(){

    }



}