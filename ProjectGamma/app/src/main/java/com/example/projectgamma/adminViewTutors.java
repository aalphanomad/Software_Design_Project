package com.example.projectgamma;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.Arrays;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;

public class adminViewTutors extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    ListView listview;
    ArrayAdapter<String> adapterOfLecturers = null;
    ArrayAdapter<String> adapterOfTutors = null;
    String courseName, lecturerName;
    TextView courseTV, lecturerTV;
    String result;
    String login_url = null;
    String post_data = null;
    String[] lecturers;
    String[] tutors;
    TextView Message, Message2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminviewtutors);

        //recieve value of string of the course that was clicked on in the previous activity
        Bundle bundle = getIntent().getExtras();
        courseName = bundle.getString("courseView");

        //set the textview to show the clicked on cours
        courseTV = (TextView) findViewById(R.id.tv_course2);
        courseTV.setText(courseName);

        Message=findViewById(R.id.Message);
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {

            //The code below recieves input from other classes to use the BackgroundWorker to send Booking/Claim form information to the server


            //The URL to send data to the server when creating a booking/claim form
            login_url = "http://lamp.ms.wits.ac.za/~s1601745/get_people.php";
            System.out.println(courseName);
            String[] dummy = courseName.split(" ");
            post_data = URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(dummy[0], "UTF-8");
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
            Log.i("tagconvertstr", "[" + result + "]");


            JSONObject ja = new JSONObject(result);
            lecturers = ja.getString("lecturers").split(",");
            tutors = ja.getString("tutors").split(",");
            System.out.println(Arrays.asList(lecturers));

            //the adapter initialised for our tutor array to show all lecturers of the specific course in the listview
            if(!lecturers[0].equals("[]")) {
                lecturers = qrGenerator.Global.formatter(lecturers);

                adapterOfLecturers = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lecturers);
                listview = findViewById(R.id.lv_lecturers);
                listview.setAdapter(adapterOfLecturers);
            }

            if(!tutors[0].equals("[]")){
                tutors=qrGenerator.Global.formatter(tutors);
                adapterOfTutors = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tutors);
                listview = findViewById(R.id.lv_tutors);
                listview.setAdapter(adapterOfTutors);

            }

            if(lecturers[0].equals("[]")){
                Message=findViewById(R.id.MessageForLecturer);
                Message.setText("It's Empty Here...");
            }
            if(tutors[0].equals("[]")){//if there are no lecturers and tutors then send the message that it is empty


                Message2=findViewById(R.id.MessageForTutors);
                Message2.setText("It's Empty Here...");
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.View_Everything: {
                break;
            }

            case R.id.Logout: {
                Intent myIntent = new Intent(adminViewTutors.this, LoginActivity.class);
                adminViewTutors.this.startActivity(myIntent);
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

        navigationView.setNavigationItemSelectedListener( adminViewTutors.this);
    }
}