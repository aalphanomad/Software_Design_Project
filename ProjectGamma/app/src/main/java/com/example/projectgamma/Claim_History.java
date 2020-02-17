package com.example.projectgamma;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;
import static com.example.projectgamma.qrGenerator.Global.get_courses;


public class Claim_History extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener  {
    InputStream is = null;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    String[] dates, courses, start_time, end_time, venue, valid,activity;
    GridView gridView;
    String result, login_url;
    Button Report;
    TextView Message;
    Spinner months;
    String month;
    TextView select_month;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.claim_list);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        Message=findViewById(R.id.Message);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
         select_month=(TextView) findViewById(R.id.textView);
        months=(Spinner) findViewById(R.id.spinner);
        months.setOnItemSelectedListener(this);
        months.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                 month= parent.getItemAtPosition(position).toString();
                                                 System.out.println("MONTH "+month);

                                             }

                                             @Override
                                             public void onNothingSelected(AdapterView<?> parent) {

                                             }
                                         });
        ArrayList<String> options=new ArrayList<>();
        options.add("ALL");
        options.add("Jan");
        options.add("Feb");
        options.add("Mar");
        options.add("Apr");
        options.add("May");
        options.add("June");
        options.add("July");
        options.add("Aug");
        options.add("Sept");
        options.add("Oct");
        options.add("Nov");
        options.add("Dec");



        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        months.setAdapter(dataAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {

            //The code below recieves input from other classes to use the BackgroundWorker to send Booking/Claim form information to the server
            String name = qrGenerator.Global.GetName();
            String student_num = qrGenerator.Global.GetStudent_Num();

            //The URL to send data to the server when creating a booking/claim form
            login_url = "http://lamp.ms.wits.ac.za/~s1601745/fetching.php";
            URL url = new URL(login_url);
            //The code below initializes an HTP POST request
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            //Creating the URL in order to send data for generating a claim form to the server
            String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_num", "UTF-8")+"="+ URLEncoder.encode(student_num, "UTF-8")  ;
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
            Log.i("tagconvertstr", "[" + result + "]");

            dates=ja.getString("dates").split(",");
            courses=ja.getString("courses").split(",");
            start_time=ja.getString("start_time").split(",");
            end_time=ja.getString("end_time").split(",");
            venue=ja.getString("venue").split(",");
            valid=ja.getString("valid").split(",");
            activity=ja.getString("activity").split(",");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        gridView = findViewById(R.id.lv);
if(Arrays.toString(dates).contains("DATE")==true){
            for (int i = 0; i < dates.length; i++) {
                dates[i] = dates[i].substring(9, dates[i].length() - 2);
                courses[i] = courses[i].substring(11, courses[i].length() - 2);
                venue[i] = venue[i].substring(10, venue[i].length() - 2);
                valid[i] = valid[i].substring(10, valid[i].length() - 2);
                start_time[i] = start_time[i].substring(15, start_time[i].length() - 2);
                end_time[i] = end_time[i].substring(13, end_time[i].length() - 2);
                activity[i] = activity[i].substring(13, activity[i].length() - 2);

            }

            //get rid of inverted commas on the end of the arrays
            dates[0] = dates[0].substring(1, dates[0].length());//1st element = 2nd char of first element to the 1st element's last char
            dates[dates.length - 1] = dates[dates.length - 1].substring(0, dates[dates.length - 1].length() - 1);

            courses[0] = courses[0].substring(1, courses[0].length());
            courses[courses.length - 1] = courses[courses.length - 1].substring(0, courses[courses.length - 1].length() - 1);

            venue[0] = venue[0].substring(1, venue[0].length());
            venue[venue.length - 1] = venue[venue.length - 1].substring(0, venue[venue.length - 1].length() - 1);

            valid[0] = valid[0].substring(1, valid[0].length());
            valid[valid.length - 1] = valid[valid.length - 1].substring(0, valid[valid.length - 1].length() - 1);

            start_time[0] = start_time[0].substring(1, start_time[0].length());
            start_time[start_time.length - 1] = start_time[start_time.length - 1].substring(0, start_time[start_time.length - 1].length() - 1);

            end_time[0] = end_time[0].substring(1, end_time[0].length());
            end_time[end_time.length - 1] = end_time[end_time.length - 1].substring(0, end_time[end_time.length - 1].length() - 1);

    activity[0] = activity[0].substring(1, activity[0].length());
    activity[activity.length - 1] = activity[activity.length - 1].substring(0, activity[activity.length - 1].length() - 1);

            //call adapter class to take in our arrays
    MyAdapter adapter = new MyAdapter(this, dates, courses, venue, start_time, end_time, valid,activity);
    gridView.setAdapter(adapter);


    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selected = ((TextView) view.findViewById(R.id.subjectHistory)).getText().toString();
            Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show();
        }
    });

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), Validate.class);
                    i.putExtra("date", ((TextView) view.findViewById(R.id.dateHistory)).getText().toString());
                    i.putExtra("course", ((TextView) view.findViewById(R.id.subjectHistory)).getText().toString());
                    i.putExtra("duration", ((TextView) view.findViewById(R.id.durationHistory)).getText().toString());
                    i.putExtra("venue", ((TextView) view.findViewById(R.id.venueHistory)).getText().toString());
                    i.putExtra("status", ((TextView) view.findViewById(R.id.status)).getText().toString());
                    i.putExtra("activity", ((TextView) view.findViewById(R.id.activityHistory)).getText().toString());
                    startActivity(i);
                }
            });


            String type = "fetching";
            String name = qrGenerator.Global.GetName();
            final String student_no = qrGenerator.Global.GetStudent_Num();
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, name, student_no);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Report = (Button) findViewById(R.id.Report_Button);
                Report.setVisibility(View.VISIBLE);
                months.setVisibility(View.VISIBLE);
                select_month.setVisibility(View.VISIBLE);
            Report.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    try {
                        Toast.makeText(Claim_History.this, "Please wait a moment.", Toast.LENGTH_LONG).show();
                        // we obtain the url of the pdf report
                        ArrayList<String>send=new ArrayList<String>();
                      if (month.equals("ALL"))
                      {
                          send.add("PDF");
                          send.add(student_no);
                        }
                        else{
                          send.add("PDF");
                          send.add(student_no);
                        send.add(month);
                        }
                        String download_url = new BackgroundWorker(Claim_History.this).doInBackground(send.toArray(new String[send.size()]));
                        Log.d("URL", "onClick: " + download_url);
                        // we create a new intent which receives the url and opens it in the browser
                        // where the user can then download it
                        // we do this since it is easier than managing the downloading and viewing ourselves
                        System.out.println("URL "+download_url);
                        if(download_url.startsWith("<br")){
Toast.makeText(Claim_History.this,"You do not have any claims for this month.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent browser_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(download_url));
                            startActivity(browser_intent);
                        }

                        /*
                        Intent myIntent = new Intent(Claim_History.this, ViewPDF.class);
                        myIntent.putExtra("URL",download_url);
                        Claim_History.this.startActivity(myIntent);
                        */

                    } catch (
                            ActivityNotFoundException e) {
                        Toast.makeText(Claim_History.this, "No application can handle this request."
                                + " Please install a web browser", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            });
        }
else{
    ImageView imgView=(ImageView) findViewById(R.id.imageView5);
    Drawable drawable  = getResources().getDrawable(R.drawable.mag_glass);
    imgView.setImageDrawable(drawable);
    Message.setText("It's Empty Here...");
        }

        setNavigationViewListener();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
System.out.println("HELLO");


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
            case R.id.Activity_History: {

                break;
            }


            case R.id.Activity: {
                if(!Arrays.toString(get_courses).equals("null")) {
                    Intent myIntent = new Intent(Claim_History.this, Claim_Form.class);
                    Claim_History.this.startActivity(myIntent);

                    break;
                }
                else{
                    Toast.makeText(this,"Once your course-selection has been approved by an admin, you will be able to create claims.",Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            case R.id.user_profile: {
                    Intent myIntent = new Intent(Claim_History.this, ViewProfile.class);
                    Claim_History.this.startActivity(myIntent);
                    break;


            }
            case R.id.Logout: {
                Intent myIntent = new Intent(Claim_History.this, LoginActivity.class);
                Claim_History.this.startActivity(myIntent);
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
        navUserEmail.setText(GetStudent_Num() + "@students.wits.ac.za");
        navigationView.setNavigationItemSelectedListener( Claim_History.this);

    }

}