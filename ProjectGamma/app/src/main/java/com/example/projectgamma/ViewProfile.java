package com.example.projectgamma;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.common.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;

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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;
import static com.example.projectgamma.qrGenerator.Global.get_courses;


public class ViewProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    ArrayList<String> the_courses=new ArrayList<>();

    String name, stud_num, email;
    String[] courses;
    public static TextView  nameTV, stud_numTV, emailTV;
    ListView listview;
    ArrayAdapter<String> adapter = null;
    ImageView ViewTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();
ViewTrans=(ImageView) findViewById(R.id.ViewTranscript);
        ViewTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String download_url = new BackgroundWorker(ViewProfile.this).doInBackground(new String[]{"transcript",qrGenerator.Global.GetStudent_Num()});
                System.out.println("CHECKING "+download_url);
                try {
                    if(download_url.equals("[[null]]")){
                        Toast.makeText(ViewProfile.this,"You have not uploaded a transcript.",Toast.LENGTH_SHORT).show();
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
        //recieving account details of tutor from global variables set in qrGenerator function
        name = qrGenerator.Global.GetName();
        stud_num = qrGenerator.Global.GetStudent_Num();
        email = stud_num+"@students.wits.ac.za";
        courses = qrGenerator.Global.Get_Courses();//
        try{
        String login_url = "http://lamp.ms.wits.ac.za/~s1601745/generic_select.php/";
          String   post_data = URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode("USER_COURSE_ALLOC", "UTF-8") + "&" + URLEncoder.encode("target", "UTF-8") + "=" + URLEncoder.encode("COURSE,CONFIRMED", "UTF-8") + "&" + URLEncoder.encode("filter", "UTF-8") + "=" + URLEncoder.encode("STUDENT_NUM", "UTF-8") + "&" + URLEncoder.encode("value", "UTF-8") + "=" + URLEncoder.encode(stud_num, "UTF-8");
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

//Retrieves the informations passed from other classes to BackgroundWorker if we are trying to register

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            JSONArray ja= new JSONArray(result);
        //    Log.i("PENDING ", "[" + result + "]");
            //String info=result.substring(2,result.length()-2);
            String storage[]=result.split("\\],\\[");

            System.out.println("COURSES "+storage.length);
                   // JSONArray test=ja.;
            //System.out.println("PENDING"+test.get(0));
            for(int i=0;i<storage.length;i++){
                JSONArray test=ja.getJSONArray(i);
                if (test.get(1).toString().equals("1")) {
                    the_courses.add(test.get(0)+"-"+BackgroundWorker.Course_corr(test.getString(0)));
                }
                else{
                    the_courses.add(test.get(0)+"-"+BackgroundWorker.Course_corr(test.getString(0))+" (Pending Confirmation)");
                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(JSONException e) {
            e.printStackTrace();
        }

            // System.out.println()

        //declaring the textviews
        nameTV = (TextView)findViewById(R.id.tv_Name);
        stud_numTV = (TextView)findViewById(R.id.tv_stud_num2);
        emailTV = (TextView)findViewById(R.id.tv_email2);

        //setting textviews with the informatin we recieve from the global variables
        nameTV.setText(name);
        stud_numTV.setText(stud_num);
        emailTV.setText(email);

        //declare adapter for listview with simple layout
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, the_courses);
        listview = findViewById(R.id.lv2);
        listview.setAdapter(adapter);   //update listview with information in array
    }

    public void upload_transcript(View view){
        Intent myIntent = new Intent(ViewProfile.this, upload_transcript.class);
        ViewProfile.this.startActivity(myIntent);
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
                Intent myIntent = new Intent(ViewProfile.this, Claim_History.class);
                ViewProfile.this.startActivity(myIntent);
                break;
            }

            case R.id.Activity: {
                if(!Arrays.toString(courses).equals("null")) {
                    Intent myIntent = new Intent(ViewProfile.this, Claim_Form.class);
                    ViewProfile.this.startActivity(myIntent);
                    break;
                }
                else{
                    Toast.makeText(this,"Once your course-selection has been approved by an admin, you will be able to create claims.",Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            case R.id.user_profile: {
                if(!Arrays.toString(get_courses).equals("null")) {
                    break;
                }
                else{
                    Toast.makeText(this,"Once your course-selection has been approved by an admin, you will be able to view your profile.",Toast.LENGTH_SHORT).show();
                    break;
                }            }
            case R.id.Logout: {
                Intent myIntent = new Intent(ViewProfile.this, LoginActivity.class);
                ViewProfile.this.startActivity(myIntent);
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
        navigationView.setNavigationItemSelectedListener( ViewProfile.this);

    }

    //button to start editing courses
    public void edit(View view) {

        Intent i = new Intent(ViewProfile.this, editUserProfile.class);
        System.out.println("BEFORE"+the_courses);
        i.putExtra("courses",the_courses.toArray(new String[the_courses.size()]));

        ViewProfile.this.startActivity(i);
    }

    //button to take us home
    public void home(View view) {
        Intent i = new Intent(ViewProfile.this, HomeActivity.class);
        ViewProfile.this.startActivity(i);
    }
}