package com.example.projectgamma;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class adminViewTutors extends AppCompatActivity {

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
/*
            JSONObject ja = new JSONObject(result);
            lecturers = ja.getString("lecturers").split(",");
            tutors = ja.getString("tutors").split(",");

            qrGenerator.Global.formatter(tutors);
            qrGenerator.Global.formatter(lecturers);
            //the adapter initialised for our tutor array to show all lecturers of the specific course in the listview
            if(!lecturers[0].equals("[]")  ) {    //if array has lecturers then fill it onto the listview
                adapterOfLecturers = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lecturers);
                listview = findViewById(R.id.lv_lecturers);
                listview.setAdapter(adapterOfLecturers);
*/



        Log.i("tagconvertstr", "[" + result + "]");

        JSONObject ja = new JSONObject(result);
            lecturers = ja.getString("lecturers").split(",");
            tutors = ja.getString("tutors").split(",");
            qrGenerator.Global.formatter(tutors);
            qrGenerator.Global.formatter(lecturers);
        //the adapter initialised for our tutor array to show all lecturers of the specific course in the listview
        if(!lecturers[0].equals("[]") && !tutors[0].equals("[]")) {
            adapterOfLecturers = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lecturers);
            lecturers = qrGenerator.Global.formatter(lecturers);
            listview = findViewById(R.id.lv_lecturers);
            listview.setAdapter(adapterOfLecturers);

            adapterOfTutors = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tutors);
            listview = findViewById(R.id.lv_tutors);
            listview.setAdapter(adapterOfTutors);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(adminViewTutors.this, "Please wait a moment.", Toast.LENGTH_LONG).show();
                    // we obtain the url of the pdf report
                    final String student_no = qrGenerator.Global.GetStudent_Num();
                    String[] params = {"transcript", student_no};
                    String download_url = new BackgroundWorker(adminViewTutors.this).doInBackground(params);
                    Log.d("URL", "onClick: " + download_url);
                    // we create a new intent which receives the url and opens it in the browser
                    // where the user can then download it
                    // we do this since it is easier than managing the downloading and viewing ourselves
                    try
                    {
                        Intent browser_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_url));
                        startActivity(browser_intent);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"We can't seem to find that users transcript. Sorry",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
            else{

                Message=findViewById(R.id.MessageForLecturer);
                Message.setText("It's Empty Here...");
            }
            if(!tutors[0].equals("[]")){
                adapterOfTutors = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tutors);
                listview = findViewById(R.id.lv_tutors);
                listview.setAdapter(adapterOfTutors);

            }
            else{//if there are no lecturers and tutors then send the message that it is empty


                Message2=findViewById(R.id.MessageForTutors);
                Message2.setText("It's Empty Here...");
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}