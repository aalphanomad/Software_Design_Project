package com.example.projectgamma;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;
import static com.example.projectgamma.qrGenerator.Global.name;
import static com.example.projectgamma.qrGenerator.Global.role;
import static com.example.projectgamma.qrGenerator.Global.setGet_courses;
import static com.example.projectgamma.qrGenerator.Global.student_num;

public class editUserProfile extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    ListView listview;
    android.support.v7.app.AlertDialog myDialog;
    ArrayAdapter<String> adapter = null;
    ArrayList<String> arrayListofCourses = null;
    String[] courses,Cleancodes,Cleancourses;
    List<String>list=new ArrayList<>();
    boolean[] test;

    String item;

    private List<String> allCourses;
    ArrayList selectedItems = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        mDrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        //create array that takes thhe values of the courses the tutor contains
        Bundle bundle = getIntent().getExtras();

        courses=bundle.getStringArray("courses");

        System.out.println("received"+Arrays.toString(courses));
        //convert above array to arraylist so we can perform certain actions later on
        arrayListofCourses = new ArrayList<String>(Arrays.asList(courses));
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //declare adapter for listview with simple layout that will show the courses tutored
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListofCourses);
        listview = findViewById(R.id.lv3);
        listview.setAdapter(adapter);   //update listview
        setNavigationViewListener();


        //when a course in the listview is clicked the user will have the option to delete that specific course
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItem.trim().equals(arrayListofCourses.get(position).trim())) {
                    removeElement(selectedItem, position);  //remove funcion is called to remove certain course
                } else {
                    Toast.makeText(getApplicationContext(), "Error Removing Course", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void change(View view) {
        System.out.println("SELECTED"+selectedItems);
        String[]info;
        for(int i=0;i<selectedItems.size();i++){
            info=selectedItems.get(i).toString().split("-");
            addCourses(info[0]);
        }
        System.out.println("GET OUT");

        Toast.makeText(this,"Course(s) have successfully been added. They will now be reviewed by an admin",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(editUserProfile.this, HomeActivity.class);
        editUserProfile.this.startActivity(myIntent);


        }

        public void addCourses(String CourseCode){
        System.out.println("CC"+CourseCode);
            try{
                String login_url = "http://lamp.ms.wits.ac.za/~s1601745/add_course.php/";
                String post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(CourseCode, "UTF-8") + "&" + URLEncoder.encode("role", "UTF-8") + "=" + URLEncoder.encode(role, "UTF-8") + "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

//Retrieves the informations passed from other classes to BackgroundWorker if we are trying to register

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
               // JSONObject ja=new JSONObject(result);

                //Log.i("Adding", "[" + result + "]");



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    //function to remove the clicked on courses in the listview
    public void removeElement(final String selectedItem, final int position){
        System.out.println("GETTING REMOVED"+selectedItem);
        final String[]info=selectedItem.split("-");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove " + selectedItem + "?");   //if item is clicked ask user to confirm deletion
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayListofCourses.remove(position);    //remove item from arraylist
                listview.setAdapter(adapter);   //update the listview
                try{
                    String login_url = "http://lamp.ms.wits.ac.za/~s1601745/remove_course.php/";
                    String post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(info[0], "UTF-8") + "&" + URLEncoder.encode("role", "UTF-8") + "=" + URLEncoder.encode(role, "UTF-8") + "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

//Retrieves the informations passed from other classes to BackgroundWorker if we are trying to register

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String result = "";
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
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void getAllCourses(View view) {
        if(list.size()==0) {
            try {
                String login_url = "http://lamp.ms.wits.ac.za/~s1601745/get_all_courses.php";
                String post_data = "";
                //Initialize an HTTP POST connection to send data to the server
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

//Retrieves the informations passed from other classes to BackgroundWorker if we are trying to register

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                try {
                    JSONObject ja = new JSONObject(result);
                    String Allcourses = ja.getString("course_name").toString().substring(1, ja.get("course_name").toString().length() - 1).replace("\"", "");
                    String Allcodes = ja.getString("course_code").toString().substring(1, ja.get("course_code").toString().length() - 1).replace("\"", "");
                    Cleancourses = Allcourses.split(",");
                    Cleancodes = Allcodes.split(",");
                    for (int i = 0; i < Cleancourses.length; i++) {
                        list.add(Cleancodes[i] + "-(" + Cleancourses[i] + ")");
                    }
                    Collections.sort(list);
                    Arrays.sort(Cleancodes);
                    System.out.println(list);
                    test=new boolean[list.size()];
                    Arrays.fill(test,false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (
                    MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
for(int i=0;i<courses.length;i++){
    for(int j=0;j<Cleancourses.length;j++)
    if(list.get(j).equals(courses[i] )){
        test[j]=true;
    }
}

        android.support.v7.app.AlertDialog.Builder myBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        //create an array of Charsequence to take the values of available courses so we can use it in the drop down list
        selectedItems.clear();
        CharSequence[] The_courses = (CharSequence[]) list.toArray(new CharSequence[list.size()]);

        //make it final
        final CharSequence[] finalThe_courses = The_courses;
        myBuilder.setTitle("Proposed Courses").setMultiChoiceItems(The_courses, test, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                //Selects the labels of the courses seleted by the user
                selectedItems.clear();
                if (isChecked == true) {
                    test[position]=true;
                    //if a certain item is selected to add to courses tutored, add it to the arraylist that already contains current curses taken
                    if (arrayListofCourses.size() < 5) {
                        System.out.println("CHECK!"+String.valueOf(finalThe_courses[position]));
                        arrayListofCourses.add(String.valueOf(finalThe_courses[position]));
                    }

                    else{
                        Toast.makeText(getApplicationContext(),"You cannot add any more courses!",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    //if unchecked remove from arraylist or do nt even add it
                    arrayListofCourses.remove(String.valueOf(finalThe_courses[position]));
                }
            }
        });

        myBuilder.setPositiveButton("Selected Items", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayListofCourses.clear();
                //iterate through our updated arraylist containg current courses
                for(int i=0;i<test.length;i++){
                    if(test[i]==true){
                        selectedItems.add(list.get(i));
                        arrayListofCourses.add(list.get(i));
                    }
                }
                listview.invalidateViews();

            }
        });
        myDialog = myBuilder.create();
        myDialog.show();

    }
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
            case R.id.Activity_History: {
                Intent myIntent = new Intent(editUserProfile.this, Claim_Form.class);
                editUserProfile.this.startActivity(myIntent);
                break;
            }

            case R.id.Activity: {
                Intent myIntent = new Intent(editUserProfile.this, Claim_Form.class);
                editUserProfile.this.startActivity(myIntent);
                break;
            }

            case R.id.user_profile: {
                Intent myIntent = new Intent(editUserProfile.this, ViewProfile.class);
                editUserProfile.this.startActivity(myIntent);
                break;
            }
            case R.id.Logout: {
                Intent myIntent = new Intent(editUserProfile.this, LoginActivity.class);
                editUserProfile.this.startActivity(myIntent);
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

        navigationView.setNavigationItemSelectedListener( editUserProfile.this);
    }
}