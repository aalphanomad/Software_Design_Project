package com.example.projectgamma;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.example.projectgamma.BackgroundWorker.Course_corr;
import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;

public class tutorProfile extends AppCompatActivity implements  AdapterView.OnItemSelectedListener,NavigationView.OnNavigationItemSelectedListener  {
    String name, stud_num, email;
    String courses;
    ArrayList<String> arrayListofCourses = null;
    public static TextView nameTV, stud_numTV, emailTV;
    ListView listview;
    ArrayAdapter<String> adapter = null;
    String tutorNum;
    String tutorName;
    String tutorRole;
    String result, login_url;
    Button button;
    Button email_but;
    ImageView ViewTrans;
    TextView tv_Courses;
    TextView View_Transcript;
    TextView ChangeRole;
    Spinner ChooseRoleMenu;
    String selectedRole;
    ArrayList<String> Roles=new ArrayList<>();
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutorprofile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        tv_Courses=(TextView) findViewById(R.id.tv_Courses);
        View_Transcript=(TextView) findViewById(R.id.View_Transcript);
        ChangeRole=(TextView) findViewById(R.id.ChangeRole);
        ViewTrans=(ImageView) findViewById(R.id.ViewTranscript);
        ChooseRoleMenu=(Spinner) findViewById(R.id.ChangeRoleMenu);
        //code used to help initialize the spinner(Which array to display
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        tutorNum = bundle.getString("tutorNum");
        tutorName = bundle.getString("tutorName");
        tutorRole=bundle.getString("tutorRole");
        if(qrGenerator.Global.GetRole().equals("2")){
            Roles.add("Tutor");
            Roles.add("Lecturer");
        }
        else if(qrGenerator.Global.GetRole().equals("4")) {
            Roles.add("Tutor");
            Roles.add("Lecturer");
            Roles.add("Admin");
            Roles.add("Lecturer & Admin");
        }
        ChooseRoleMenu.setOnItemSelectedListener(tutorProfile.this);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Roles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ChooseRoleMenu.setAdapter(dataAdapter);
        if(tutorRole.equals("0")) {
            ChooseRoleMenu.setSelection(0);
        }
        else if(tutorRole.equals("1")){
            ChooseRoleMenu.setSelection(1);

        }



        if(qrGenerator.Global.GetRole().equals("4") || qrGenerator.Global.GetRole().equals("2")){
            ChangeRole.setVisibility(View.VISIBLE);
            ChooseRoleMenu.setVisibility(View.VISIBLE);
        }
        button = findViewById(R.id.Done_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(qrGenerator.Global.role.equals("1")) {
                    Intent i = new Intent(tutorProfile.this, MyTutors.class);
                    tutorProfile.this.startActivity(i);
                }
                else{
                    if(!tutorRole.equals(selectedRole)) {
                        BackgroundWorker bw = new BackgroundWorker(tutorProfile.this);
                        bw.execute("change role", tutorNum, tutorRole, selectedRole);
                        Toast.makeText(tutorProfile.this, "Successfully Updated the User's Role", Toast.LENGTH_SHORT).show();
                    }

                    Intent i = new Intent(tutorProfile.this, adminViewCourses.class);
                    tutorProfile.this.startActivity(i);
                }
            }
        });

        if(tutorRole.equals("0")){
            tv_Courses.setText("Tutor's Courses");
        }
        else{
            View_Transcript.setVisibility(View.INVISIBLE);
            ViewTrans.setVisibility(View.INVISIBLE);
            tv_Courses.setText("Lecturer's Courses");
        }

        email = tutorNum + "@students.wits.ac.za";
        System.out.println("INFO"+tutorNum+" "+tutorName);
        email_but=(Button) findViewById(R.id.email_but);

        ViewTrans=(ImageView) findViewById(R.id.ViewTranscript);
        ViewTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String download_url = new BackgroundWorker(tutorProfile.this).doInBackground(new String[]{"transcript",tutorNum});
                System.out.println("CHECKING "+download_url);
                try {
                    if(download_url.equals("[[null]]") || download_url.startsWith("<br")){
                        Toast.makeText(tutorProfile.this,"The user has not uploaded a transcript.",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        JSONArray ja = new JSONArray(download_url);
                        download_url = ja.get(0).toString().substring(2, ja.getString(0).length() - 2);
                        System.out.println("TRANSCRIPT " + download_url);
                        Intent browser_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_url));
                        startActivity(browser_intent);
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }


            }
        });

        try {

            //The code below recieves input from other classes to use the BackgroundWorker to send Booking/Claim form information to the server

            //The URL to send data to the server when creating a booking/claim form
            login_url = "http://lamp.ms.wits.ac.za/~s1601745/get_ValidCourses.php";
            String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(tutorName, "UTF-8") + "&" + URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(tutorNum, "UTF-8");

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
            Log.i("hello", "[" + result + "]");

             courses = ja.getString("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> new_courses;
        if (!courses.equals("[]")) {
            new_courses = BackgroundWorker.GetCourses(courses);

            // System.out.println(new_courses);
            ArrayList<String> coursesList = new ArrayList<>();
            for (int i = 0; i < new_courses.size(); i++) {
                coursesList.add(new_courses.get(i) + "-" + Course_corr(new_courses.get(i)));
            }

            //declaring the textviews
            nameTV = (TextView) findViewById(R.id.tv_Name);
            stud_numTV = (TextView) findViewById(R.id.tv_stud_num2);
            emailTV = (TextView) findViewById(R.id.tv_email2);


            //setting textviews with the informatin we recieve from the global variables
            nameTV.setText(tutorName);
            stud_numTV.setText(tutorNum);
            emailTV.setText(email);

            //declare adapter for listview with simple layout
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, coursesList);
            listview = findViewById(R.id.lv2);
            listview.setAdapter(adapter);   //update listview with information in array
        }
        setNavigationViewListener();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getSelectedItem().toString().equals("Tutor")) {
            selectedRole = "0";
        } else if (parent.getSelectedItem().toString().equals("Lecturer")) {
            selectedRole = "1";
        } else if (parent.getSelectedItem().toString().equals("Admin")) {
            selectedRole = "2";
        }
        else if(parent.getSelectedItem().toString().equals("Lecturer & Admin")){
            selectedRole="3";
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void email(View view){
        Intent emailIntent=new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{tutorNum+"@students.wits.ac.za"});
        emailIntent.setType("text/plain");
        startActivity(emailIntent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.View_Everything: {
                break;
            }

            case R.id.Logout: {
                Intent myIntent = new Intent(tutorProfile.this, LoginActivity.class);
                tutorProfile.this.startActivity(myIntent);
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

        navigationView.setNavigationItemSelectedListener( tutorProfile.this);
    }
}
