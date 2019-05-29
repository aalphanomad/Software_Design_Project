package com.example.projectgamma;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

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

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;

public class tutorProfile extends AppCompatActivity {
    String name, stud_num, email;
    String[] courses;
    ArrayList<String> arrayListofCourses = null;
    public static TextView  nameTV, stud_numTV, emailTV;
    ListView listview;
    ArrayAdapter<String> adapter = null;
    String tutorNum;
    String tutorName;
    String result, login_url;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tutorprofile);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
button=findViewById(R.id.Done_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(tutorProfile.this, MyTutors.class);
                tutorProfile.this.startActivity(i);
            }
        });
        Bundle bundle = getIntent().getExtras();
       // tutorNum = bundle.getString("tutorNum");
        //tutorName = bundle.getString("tutorName");
        email = tutorNum+"@students.wits.ac.za";

        try {

            //The code below recieves input from other classes to use the BackgroundWorker to send Booking/Claim form information to the server

            //The URL to send data to the server when creating a booking/claim form
            login_url = "http://lamp.ms.wits.ac.za/~s1601745/fetching2.php";
            URL url = new URL(login_url);
            //The code below initializes an HTP POST request
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            //Creating the URL in order to send data for generating a claim form to the server
            //String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(tutorName, "UTF-8") + "&" + URLEncoder.encode("student_num", "UTF-8")+"="+ URLEncoder.encode(tutorNum, "UTF-8")  ;
            //bufferedWriter.write(post_data);
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
           // JSONObject ja = new JSONObject(result);
            Log.i("hello", "[" + result + "]");

            //courses = ja.getString("courses").split(",");
           // System.out.println(Arrays.asList(courses));

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*courses[0] = courses[0].substring(12, courses[0].length());
        courses[1] = courses[1].substring(11, courses[1].length());
        courses[2] = courses[2].substring(11, courses[2].length());
        courses[3] = courses[3].substring(11, courses[3].length());
        courses[4] = courses[4].substring(11, courses[4].length() - 1);*/

/*courses=qrGenerator.Global.formatter(courses);
        System.out.println(Arrays.asList(courses));


        arrayListofCourses = new ArrayList<String>(Arrays.asList(courses));
        arrayListofCourses.removeAll(Collections.singletonList("ul"));
        for(int i=0;i<arrayListofCourses.size();i++){
            arrayListofCourses.set(i,arrayListofCourses.get(i)+" "+MainActivity.Global.Course_corr(arrayListofCourses.get(i)));
        }*/


        //declaring the textviews
        nameTV = (TextView)findViewById(R.id.tv_Name);
        stud_numTV = (TextView)findViewById(R.id.tv_stud_num2);
        emailTV = (TextView)findViewById(R.id.tv_email2);


        //setting textviews with the informatin we recieve from the global variables
        nameTV.setText(tutorName);
        stud_numTV.setText(tutorNum);
        emailTV.setText(email);

        //declare adapter for listview with simple layout
        /*adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListofCourses);
        listview = findViewById(R.id.lv2);
        listview.setAdapter(adapter);   //update listview with information in array*/
    }
}