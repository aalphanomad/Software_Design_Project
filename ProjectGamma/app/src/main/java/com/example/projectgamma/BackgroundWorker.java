package com.example.projectgamma;

//Android Import Statements

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BackgroundWorker extends AsyncTask<String, Void, String> {


    private Context context;
    private String type;
    static Boolean lecturer=false;
    String role;

    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    String[] Global;

    @Override
    protected String doInBackground(String... params) {

        //Determines whether the backgroundWorker is being implemented for a registration,logon or booking(String type)
        type = params[0];
        String login_url = null;
        String post_data = null;
        //The below is executed if we are trying to use BackgroundWorker for registering
        try {
            if (type.equals("reg")) {
                Global = params;
//Specifies the URL in order to register
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/create.php/";
                String name = params[1];
                String stu_num = params[2];
                String email = params[3];
                String password = params[4];
                 role=params[5];

                String course1;
                String course2;
                String course3;
                String course4;
                String course5;
                System.out.println("Sent!" + role);
//The below creates the appropriate URL to send data to the server depending on the number of courses selected

                post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(stu_num, "UTF-8") + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                if (params.length == 7) {
                    course1 = params[6];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("role", "UTF-8") + "=" + URLEncoder.encode(role, "UTF-8");

                }
                if (params.length == 8) {
                    course1 = params[6];
                    course2 = params[7];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("role", "UTF-8") + "=" + URLEncoder.encode(role, "UTF-8");

                }
                if (params.length == 9) {
                    course1 = params[6];
                    course2 = params[7];
                    course3 = params[8];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("course3", "UTF-8") + "=" + URLEncoder.encode(course3, "UTF-8") + "&" + URLEncoder.encode("role", "UTF-8") + "=" + URLEncoder.encode(role, "UTF-8");

                }
                if (params.length == 10) {
                    course1 = params[6];
                    course2 = params[7];
                    course3 = params[8];

                    course4 = params[9];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("course3", "UTF-8") + "=" + URLEncoder.encode(course3, "UTF-8") + "&" + URLEncoder.encode("course4", "UTF-8") + "=" + URLEncoder.encode(course4, "UTF-8") + "&" + URLEncoder.encode("role", "UTF-8") + "=" + URLEncoder.encode(role, "UTF-8");

                }
                if (params.length == 11) {
                    course1 = params[6];
                    course2 = params[7];
                    course3 = params[8];
                    course4 = params[9];
                    course5 = params[10];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("course3", "UTF-8") + "=" + URLEncoder.encode(course3, "UTF-8") + "&" + URLEncoder.encode("course4", "UTF-8") + "=" + URLEncoder.encode(course4, "UTF-8") + "&" + URLEncoder.encode("course5", "UTF-8") + "=" + URLEncoder.encode(course5, "UTF-8") + "&" + URLEncoder.encode("role", "UTF-8") + "=" + URLEncoder.encode(role, "UTF-8");

                }
            } else if (type.equals("verify")) {
                String name = params[1];
                String student_num = params[2];
                String course = params[3];
                String date = params[4];
                String venue = params[5];
                // venue = venue.substring(0, venue.length() - 1);

                System.out.println("VERIFY"+Arrays.toString(params));

                //The URL below is used to send data to the server in order to login
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/verify.php";
                post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8") + "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" + URLEncoder.encode("venue", "UTF-8") + "=" + URLEncoder.encode(venue, "UTF-8");


            } else if (type.equals("login")) {

                String stu_num = params[1];
                String password = params[2];

                //The URL below is used to send data to the server in order to login
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/signin.php";
                post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(stu_num, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            } else if (type.equals("booking")) {

                //The code below receives input from other classes to use the BackgroundWorker to send Booking/Claim form information to the server
                String name = qrGenerator.Global.GetName();
                String student_num = qrGenerator.Global.GetStudent_Num();
                String course = params[3];
                String date = params[4];
                String venue = params[5];
                String startTime = params[6];
                String endTime = params[7];
                String activity = params[8];
                String[] test1 = startTime.split(":");
                String[] test2 = endTime.split(":");
                if (test1[0].length() == 1) {
                    startTime = "0" + startTime;
                }
                if (test2[0].length() == 1) {
                    endTime = "0" + endTime;
                }
                System.out.println("Name"+name);
                System.out.println("Studentnumber"+student_num);
                System.out.println("Claim   "+Arrays.toString(params));
                String valid = "0";
                //The URL to send data to the server when creating a booking/claim form
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/booking.php";
                post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8") + "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" + URLEncoder.encode("venue", "UTF-8") + "=" + URLEncoder.encode(venue, "UTF-8") + "&" + URLEncoder.encode("chkStartTime", "UTF-8") + "=" + URLEncoder.encode(startTime, "UTF-8") + "&" + URLEncoder.encode("chkEndTime", "UTF-8") + "=" + URLEncoder.encode(endTime, "UTF-8") + "&" + URLEncoder.encode("activity", "UTF-8") + "=" + URLEncoder.encode(activity, "UTF-8") + "&" + URLEncoder.encode("valid", "UTF-8") + "=" + URLEncoder.encode(valid, "UTF-8");

            } else if (type.equals("PDF")) {
                String student_num = params[1];
                //The URL to send data to the server when creating a booking/claim form
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/select_booking.php?";

                if(params.length==2) {
                    post_data = "table=BOOKINGS&target=*&student_num=" + student_num;
                }
                else{
                    post_data = "table=BOOKINGS&target=*&student_num=" + student_num+"&month="+params[2];


                }
            }
            else if (type.equals("transcript")) {
                String stud_num = params[1];
                //The URL to send data to the server when creating a booking/claim form
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/generic_select.php?";
                post_data = "table=USER_INFORMATION&target=TRANSCRIPT&filter=STUDENT_NUM&value="+stud_num;
            } else if (type.equals("get courses")) {
                String name = params[1];
                String student_num = params[2];
                System.out.println("DATA"+Arrays.toString(params));

                login_url = "http://lamp.ms.wits.ac.za/~s1601745/get_ValidCourses.php?";
                post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8");

            } else if (type.equals("edit courses")) {
                String student_num = params[1];
                List<String> Test = new ArrayList<String>(Arrays.asList(params));
                String course1 = Test.get(2);
                String course2 = Test.get(3);
                String course3 = Test.get(4);
                String course4 = Test.get(5);
                String course5 = Test.get(6);

                login_url = "http://lamp.ms.wits.ac.za/~s1601745/update_details.php?";

                if (course2 == null) {
                    post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8");
                } else if (course3 == null) {
                    post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8");
                } else if (course4 == null) {
                    post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("course3", "UTF-8") + "=" + URLEncoder.encode(course3, "UTF-8");
                } else if (course5 == null) {
                    post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("course3", "UTF-8") + "=" + URLEncoder.encode(course3, "UTF-8") + "&" + URLEncoder.encode("course4", "UTF-8") + "=" + URLEncoder.encode(course4, "UTF-8");
                    System.out.println("TEST5 " + post_data);

                } else {
                    post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("course3", "UTF-8") + "=" + URLEncoder.encode(course3, "UTF-8") + "&" + URLEncoder.encode("course4", "UTF-8") + "=" + URLEncoder.encode(course4, "UTF-8") + "&" + URLEncoder.encode("course5", "UTF-8") + "=" + URLEncoder.encode(course5, "UTF-8");
                    System.out.println("TEST6 " + post_data);
                }


            }
            else if(type=="is_lecturer"){
                String StudentNumber=params[1];
                String Password=params[2];
                String Course=params[3];
                System.out.println("THE DATA"+StudentNumber+Password+Course);
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/is_lecturer.php?";
                post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(StudentNumber, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(Course, "UTF-8");



            }
            else if(type.equals("change role")){
                String stud_num=params[1];
                String oldRole=params[2];
                String newRole=params[3];

                System.out.println("ROLES "+stud_num+":"+oldRole+":"+newRole);

                if(newRole.equals("2")){
                    if(oldRole.equals("0") || oldRole.equals("1")){
                        login_url = "http://lamp.ms.wits.ac.za/~s1601745/set_admin.php?";
                        post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(stud_num, "UTF-8");


                    }
                }
                else if(newRole.equals("1")){
                    if(oldRole.equals("0")){
                        login_url = "http://lamp.ms.wits.ac.za/~s1601745/make_lecturer.php?";
                        post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(stud_num, "UTF-8");

                    }

                    else if(oldRole.equals("3") && qrGenerator.Global.GetRole().equals("4")){
                        login_url = "http://lamp.ms.wits.ac.za/~s1601745/make_lecturer.php?";
                        post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(stud_num, "UTF-8");
                    }
                }
                if(newRole.equals("0")){
                    if(oldRole.equals("1")){
                        System.out.println("HEEEEE");
                        login_url = "http://lamp.ms.wits.ac.za/~s1601745/make_tutor.php?";
                        post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(stud_num, "UTF-8");
                    }
                }
                else if(newRole.equals("3")){
                    login_url = "http://lamp.ms.wits.ac.za/~s1601745/make_lecturer_admin.php?";
                    post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(stud_num, "UTF-8");
                }

            }

//Initialize an HTTP POST connection to send data to the server
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
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        //The code below is executed if we are using the BackgroundWorker to login


//The code below is executed if we are using BackgroundWorker to create a booking(generating a claim form)

        return null;
    }

    @Override
    protected void onPreExecute() {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

//Executed after receiving data from the server

    @SuppressLint("WrongThread")
    @Override
    protected void onPostExecute(String result) {
        try {

            if (type == "reg") {
                JSONObject ja = new JSONObject(result);
                Log.i("tagconvertstr", "[" + result + "]");

                if (ja.get("result").toString().equals("1")) {
                    Toast.makeText(context, "This is a Duplicate Student Number", Toast.LENGTH_LONG).show();

                } else {
//Sets the name and student number of the user
                    qrGenerator.Global.setName(Global[1]);
                    qrGenerator.Global.setStudent_num(Global[2]);
                    qrGenerator.Global.setGet_courses(null);
                    if(role.equals("0")) {
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(context, MyTutors.class);
                        context.startActivity(intent);
                    }
                }
            }
            if (type == "is_lecturer") {

                JSONObject ja=new JSONObject(result);
                System.out.println("LECTURER?B"+ja.getString("result"));
                lecturer = ja.getString("result").equals("0");
            }

            if (type == "login") {
                //Converts the result from the server to JSON format

                JSONObject ja = new JSONObject(result);
                Log.i("tagconvertstr", "[" + result + "]");

                //If the value returned from the server is "0",implies that the login is unsuccessful
                if (ja.get("result").toString().equals("0")) {
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_LONG).show();
                } else {
                    //If the result from the server implies the login was successful,accept the student number and name from the server and determine whether they are an role or not
                    String stud_num = ja.getString("student_num");
                    String name = ja.getString("name");
                    String role = ja.getString("role");
                    qrGenerator.Global.setName(name);
                    qrGenerator.Global.setStudent_num(stud_num);
                    qrGenerator.Global.setRole(role);
                    JSONObject jp=new JSONObject(result);
                    String courses = jp.get("courses").toString();
                    // Log.i("WhyNotWorking", "[" + courses + "]");

                    System.out.println("FETCHED" + courses);
                    ArrayList<String> new_courses;
                    if(!courses.equals("[null]")) {
                        new_courses = GetCourses(courses);

                        // System.out.println(new_courses);
                        ArrayList<String> coursesList = new ArrayList<>();

                        for (int i = 0; i < new_courses.size(); i++) {
                            System.out.println(new_courses.get(i));
                            System.out.println(Course_corr((new_courses.get(i))));
                            coursesList.add(new_courses.get(i) + "-" + Course_corr(new_courses.get(i)));
                        }
                        qrGenerator.Global.setGet_courses(coursesList.toArray(new String[coursesList.size()]));
                    }

                    //If role="0",implies the the user a not a tutor(therefore a tutor)
                    if (role.equals("0")) {
                        Intent i = new Intent(context, HomeActivity.class);
                        //Send the name and student number of the student to the home Activity
                        i.putExtra("name", name);
                        i.putExtra("stud_num", stud_num);


                        context.startActivity(i);
                    } else if (role.equals("1")) {
                        //If the role is a tutor(role="1",they will be directed to a homescreen which will allow them to scan QR Codes
                        Intent i = new Intent(context, MyTutors.class);
                        //Send the name to the lecturers_name home screen
                        qrGenerator.Global.setName(name);
                        qrGenerator.Global.setStudent_num(stud_num);
                        context.startActivity(i);
                    } else if(role.equals("3")){


                        qrGenerator.Global.setName(name);
                        qrGenerator.Global.setStudent_num(stud_num);
                        Intent i = new Intent(context, LecturerAdmin.class);
                        context.startActivity(i);

                    }
                    else {
                        //If the role is a tutor(role="1",they will be directed to a homescreen which will allow them to scan QR Codes
                        Intent i = new Intent(context, adminViewCourses.class);
                        //Send the name to the lecturers_name home screen

                        context.startActivity(i);
                    }
                }
            } else if (type == "verify") {

                Log.i("tagconvertstr", "[" + result + "]");

                //Converts the result from the server to JSON format
                JSONObject ja = new JSONObject(result);


                if (ja.get("result").toString().equals("0")) {
                    Toast.makeText(context, "Confirmation Successful", Toast.LENGTH_LONG).show();
                    if(qrGenerator.Global.GetRole().equals("0")) {
                        Intent i = new Intent(context, HomeActivity.class);
                        context.startActivity(i);
                    }
                    else   if(qrGenerator.Global.GetRole().equals("1") || qrGenerator.Global.GetRole().equals("1")) {
                        Intent i = new Intent(context, MyTutors.class);
                        context.startActivity(i);
                    }

                } else {
                    Toast.makeText(context, "Confirmation Unsuccessful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, qrScanner.class);
                    context.startActivity(i);

                }
            } else if (type == "booking") {
                //Log.i("tagconvertstr", "[" + result + "]");

                JSONObject ja = new JSONObject(result);
                if (ja.get("result").toString().equals("0")) {
                    Toast.makeText(context, "Booking Successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, qrGenerator.class);
                    context.startActivity(i);
                } else {
                    Toast.makeText(context, "This is a duplicate claim! Please try again!", Toast.LENGTH_LONG).show();


                }
            } else if (type == "get courses") {
                JSONObject ja = new JSONObject(result);
                String courses = ja.get("result").toString();
                //Log.i("WhyNotWorking", "[" + result + "]");

                ArrayList<String> new_courses;
                if(!courses.equals("[]")) {
                    new_courses = GetCourses(courses);

                    // System.out.println(new_courses);
                    ArrayList<String> coursesList = new ArrayList<>();
                    for (int i = 0; i < new_courses.size(); i++) {
                        coursesList.add(new_courses.get(i) + "-" + Course_corr(new_courses.get(i)));
                    }
                    System.out.println("WHHHY!!!"+coursesList);
                    qrGenerator.Global.setGet_courses(coursesList.toArray(new String[coursesList.size()]));

                    //Intent intent7 = new Intent("INTENT_7").putExtra("get courses", coursesList.toArray(new String[coursesList.size()]));
                    //LocalBroadcastManager.getInstance(context).sendBroadcast(intent7);
                }else{
                    qrGenerator.Global.setGet_courses(new String[10]);

                    //Intent intent7 = new Intent("INTENT_7").putExtra("get courses", "[]");
                    //LocalBroadcastManager.getInstance(context).sendBroadcast(intent7);
                }
            } else if (type.equals("edit courses")) {
                Intent i = new Intent(context, HomeActivity.class);
                context.startActivity(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String Course_corr(String course_code) {

        String course_name=null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            String login_url = "http://lamp.ms.wits.ac.za/~s1601745/generic_select.php?";
            String post_data = URLEncoder.encode("table", "UTF-8") + "=" + URLEncoder.encode("COURSES", "UTF-8") + "&" + URLEncoder.encode("target", "UTF-8") + "=" + URLEncoder.encode("COURSE_NAME", "UTF-8") + "&" + URLEncoder.encode("filter", "UTF-8") + "=" + URLEncoder.encode("COURSE_CODE", "UTF-8") + "&" + URLEncoder.encode("value", "UTF-8") + "=" + URLEncoder.encode(course_code, "UTF-8");


            //Initialize an HTTP POST connection to send data to the server
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

            System.out.println("CC2");

            JSONArray ja = new JSONArray(result);
            course_name="("+ja.getString(0).substring(2,ja.getString(0).length()-2)+")";
            System.out.println("COURSE"+course_name);




        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return course_name;


    }
    // This populates a drop down menu with the courses that the tutor is assigned
    public static ArrayList<String> GetCourses (String FromDB){

// Notification.show(ans);
        ArrayList<String> new_courses = new ArrayList<String>();

        String[] courses1 = FromDB.split(",");
        for (int i = 0; i < courses1.length; i++) {
            String[] courses2 = courses1[i].split(":");
            if (i == courses1.length - 1) {
                if (!courses2[1].substring(0, courses2[1].length() - 2).equals("null")) {
                    new_courses.add(courses2[1].substring(1, courses2[1].length() - 3));

                }
            } else {
                if (!courses2[1].equals("null"))
                    new_courses.add(courses2[1].substring(1, courses2[1].length() - 2));
            }
        }
        return new_courses;
    }




    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}