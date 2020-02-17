package com.example.projectgamma;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
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
import static com.example.projectgamma.qrGenerator.Global.course;


public class MyTutors extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener{
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    String result;
    String login_url=null;
    String post_data=null;
    private Spinner mySpinner1;
    String[] Course1,Course2,Course3,Course4,Course5;
    String[] stu_Course1,stu_Course2,stu_Course3,stu_Course4,stu_Course5;
    String MyCourses;
    ArrayList<String>tutors=new ArrayList<>();
    List<String> course_arr;
TextView Message;
ArrayList dummy=new ArrayList();
    Button email;
    ListView lv;
    FloatingActionButton fab;
    Spinner Course_Spinner;
    TextView course_message;
    TextView tutors_message;
    ImageView imageView5;

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
        TextView Message=(TextView) findViewById(R.id.Message);
        lv=(ListView) findViewById(R.id.lv);
        fab=(FloatingActionButton) findViewById(R.id.fab);
        Course_Spinner=(Spinner) findViewById(R.id.Course_Spinner);
        course_message=(TextView) findViewById(R.id.course_message);
        tutors_message=(TextView) findViewById(R.id.tutor_message);
        email=(Button) findViewById(R.id.email);
        imageView5=(ImageView) findViewById(R.id.imageView5);

        mySpinner1 = (Spinner) findViewById(R.id.Course_Spinner);
        mySpinner1.setOnItemSelectedListener(this);




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
             Course1=ja.getString("Course1").split(",");//Gets tutors_name name for Course 1
             Course2=ja.getString("Course2").split(",");//Gets tutors_name name for Course 2
             Course3=ja.getString("Course3").split(",");//Gets tutors_name name for Course 3
             Course4=ja.getString("Course4").split(",");//Gets tutors_name name for Course 4
             Course5=ja.getString("Course5").split(",");//Gets tutors_name name for Course 5

             stu_Course1=qrGenerator.Global.formatter(ja.getString("stu_Course1").split(","));//Gets tutors_name name for Course 5
            stu_Course2=qrGenerator.Global.formatter(ja.getString("stu_Course2").split(","));//Gets tutors_name name for Course 5
            stu_Course3=qrGenerator.Global.formatter(ja.getString("stu_Course3").split(","));//Gets tutors_name name for Course 5
            stu_Course4=qrGenerator.Global.formatter(ja.getString("stu_Course4").split(","));//Gets tutors_name name for Course 5
            stu_Course5=qrGenerator.Global.formatter(ja.getString("stu_Course5").split(","));//Gets tutors_name name for Course 5


            MyCourses = ja.getString("Courses");

            System.out.println("THE COURSES"+Arrays.toString(Course2));
            if(!MyCourses.equals("[]")) {

                for (int i = 0; i < stu_Course1.length; i++) {
                    tutors.add(stu_Course1[i] + "@students.wits.ac.za");
                }


                course_arr = Arrays.asList(MyCourses.split(","));
                course_arr.set(course_arr.size() - 1, course_arr.get(course_arr.size() - 1).substring(0, course_arr.get(course_arr.size() - 1).length() - 1));
                course_arr.set(0, course_arr.get(0).substring(1, course_arr.get(0).length()));


                for (int i = 0; i < course_arr.size(); i++) {

                    if (!course_arr.get(i).equals("null")) {
                        dummy.add(course_arr.get(i).substring(1, course_arr.get(i).length() - 1) + "-" + BackgroundWorker.Course_corr(course_arr.get(i).substring(1, course_arr.get(i).length() - 1)));
                    }
                }


                String[] dummy2 = (String[]) dummy.toArray(new String[dummy.size()]);

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dummy2);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner1.setAdapter(dataAdapter);
            }


            else {

                Message.setVisibility(View.VISIBLE);
                lv.setVisibility(View.INVISIBLE);
                fab.setVisibility(View.INVISIBLE);
                Course_Spinner.setVisibility(View.INVISIBLE);
                course_message.setVisibility(View.INVISIBLE);
                tutors_message.setVisibility(View.INVISIBLE);
                email.setVisibility(View.INVISIBLE);
                Message.setText("Nothing to see here.");
                imageView5.setVisibility(View.VISIBLE);


                        Toast.makeText(this, "Once your application has been reviewed by an admin, you will be able to view the tutors of your courses.", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        setNavigationViewListener();

    }

    public void email(View view){
        Intent emailIntent=new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,tutors.toArray(new String[tutors.size()]));
        emailIntent.setType("text/plain");
        startActivity(emailIntent);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Message.setVisibility(View.INVISIBLE);
        lv.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        if (position == 0) {
            if (!Arrays.toString(Course1).equals("[[]]")) {


                if(Arrays.toString(Course1).charAt(1)=="[".charAt(0))
                    Course1 = qrGenerator.Global.formatter(Course1);
                tutors.clear();
                for(int i=0;i<stu_Course1.length;i++){
                    tutors.add(stu_Course1[i]+"@students.wits.ac.za");
                }
                lv.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course1));

            }
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
Message.setVisibility(View.INVISIBLE);
                    String selectedNum = stu_Course1[position].toString(); //get string of the clicked subject
                    String selectedName = Course1[position].toString();
                    //send this string to the activity that follows
                    Intent i = new Intent(getApplicationContext(), tutorProfile.class);
                    i.putExtra("tutorNum", selectedNum);
                    i.putExtra("tutorName", selectedName);
                    i.putExtra("tutorRole","0");

                    startActivity(i);

                }
            });
        }


        if (position == 1) {
            if (!Arrays.toString(Course2).equals("[[]]")) {
                if(Arrays.toString(Course2).charAt(1)=="[".charAt(0))
                    Course2 = qrGenerator.Global.formatter(Course2);
                tutors.clear();
                for(int i=0;i<stu_Course2.length;i++){
                    tutors.add(stu_Course2[i]+"@students.wits.ac.za");
                }                lv.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course2));


            }else{
                lv.setVisibility(View.INVISIBLE);
                email.setVisibility(View.INVISIBLE);
                Message.setText("There are no tutors for this course.");
                Message.setVisibility(View.VISIBLE);

            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String selectedNum = stu_Course2[position].toString(); //get string of the clicked subject
                    String selectedName = Course2[position].toString();
                    //send this string to the activity that follows
                    Intent i = new Intent(getApplicationContext(), tutorProfile.class);
                    i.putExtra("tutorNum", selectedNum);
                    i.putExtra("tutorName", selectedName);
                    i.putExtra("tutorRole","0");

                    startActivity(i);

                }
            });
        }
        if (position == 2) {
            if (!Arrays.toString(Course3).equals("[[]]")) {
                if(Arrays.toString(Course3).charAt(1)=="[".charAt(0))
                    Course3 = qrGenerator.Global.formatter(Course3);
                tutors.clear();
                for(int i=0;i<stu_Course3.length;i++){
                    tutors.add(stu_Course3[i]+"@students.wits.ac.za");
                }                lv.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course3));

            }else{
                Message.setText("There are no tutors for this course");
                Message.setVisibility(View.VISIBLE);
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String selectedNum = stu_Course3[position].toString(); //get string of the clicked subject
                    String selectedName = Course3[position].toString();
                    //send this string to the activity that follows
                    Intent i = new Intent(getApplicationContext(), tutorProfile.class);
                    i.putExtra("tutorNum", selectedNum);
                    i.putExtra("tutorName", selectedName);
                    i.putExtra("tutorRole","0");

                    startActivity(i);

                }
            });
        }
        if (position == 3) {
            if (!Arrays.toString(Course4).equals("[[]]")) {
                if(Arrays.toString(Course4).charAt(1)=="[".charAt(0))
                    Course4 = qrGenerator.Global.formatter(Course4);
                tutors.clear();
                for(int i=0;i<stu_Course4.length;i++){
                    tutors.add(stu_Course4[i]+"@students.wits.ac.za");
                }                lv.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course4));

            }else{
                Message.setText("There are no tutors for this course");
                Message.setVisibility(View.VISIBLE);
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String selectedNum = stu_Course4[position].toString(); //get string of the clicked subject
                    String selectedName = Course4[position].toString();
                    //send this string to the activity that follows
                    Intent i = new Intent(getApplicationContext(), tutorProfile.class);
                    i.putExtra("tutorNum", selectedNum);
                    i.putExtra("tutorName", selectedName);
                    i.putExtra("tutorRole","0");
                    startActivity(i);

                }
            });
        }
        if (position == 4) {
            if (!Arrays.toString(Course5).equals("[[]]")) {
                if(Arrays.toString(Course5).charAt(1)=="[".charAt(0))
                    Course5 = qrGenerator.Global.formatter(Course5);

                tutors.clear();
                    for(int i=0;i<stu_Course5.length;i++){
                        tutors.add(stu_Course5[i]+"@students.wits.ac.za");
                    }                    Course5 = qrGenerator.Global.formatter(Course5);
                lv.setAdapter(new ArrayAdapter<String>(MyTutors.this, android.R.layout.simple_list_item_1, Course5));

            }
            else{
                Course5=null;
                Message.setText("There are no tutors for this course");
                Message.setVisibility(View.VISIBLE);
            }

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String selectedNum = stu_Course5[position].toString(); //get string of the clicked subject
                    String selectedName = Course5[position].toString();
                    //send this string to the activity that follows
                    Intent i = new Intent(getApplicationContext(), tutorProfile.class);
                    i.putExtra("tutorNum", selectedNum);
                    i.putExtra("tutorName", selectedName);
                    i.putExtra("tutorRole","0");

                    startActivity(i);

                }
            });
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}