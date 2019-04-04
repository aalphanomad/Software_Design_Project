package com.example.projectgamma;

//Android Import Statements

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
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


public class BackgroundWorker extends AsyncTask<String, Void, String> {


    private Context context;
    private String type;

    BackgroundWorker(Context ctx) {
        context = ctx;

    }


    @Override
    protected String doInBackground(String... params) {
        //Determines whether the backgroundWorker is being implemented for a registration,logon or booking(String type)
        type = params[0];
        String login_url = null;
        String post_data=null ;
        //The below is executed if we are trying to use BackgroundWorker for registering
        try {
            if (type.equals("reg")) {

//Specifies the URL in order to register
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/create.php/";
                String name = params[1];
                String stu_num = params[2];
                String email = params[3];
                String password = params[4];

                String course1;
                String course2;
                String course3;
                String course4;
                String course5;
//The below creates the appropriate URL to send data to the server depending on the number of courses selected

                post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("studentnum", "UTF-8") + "=" + URLEncoder.encode(stu_num, "UTF-8") + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" + URLEncoder.encode("admin", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
                if (params.length == 6) {
                    course1 = params[5];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8");

                }
                if (params.length == 7) {
                    course1 = params[5];
                    course2 = params[6];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8");

                }
                if (params.length == 8) {
                    course1 = params[5];
                    course2 = params[6];
                    course3 = params[7];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("course3", "UTF-8") + "=" + URLEncoder.encode(course3, "UTF-8");

                }
                if (params.length == 9) {
                    course1 = params[5];
                    course2 = params[6];
                    course3 = params[7];

                    course4 = params[8];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("course3", "UTF-8") + "=" + URLEncoder.encode(course3, "UTF-8") + "&" + URLEncoder.encode("course4", "UTF-8") + "=" + URLEncoder.encode(course4, "UTF-8");

                }
                if (params.length == 10) {
                    course1 = params[5];
                    course2 = params[6];
                    course3 = params[7];
                    course4 = params[8];
                    course5 = params[9];
                    post_data = post_data + "&" + URLEncoder.encode("course1", "UTF-8") + "=" + URLEncoder.encode(course1, "UTF-8") + "&" + URLEncoder.encode("course2", "UTF-8") + "=" + URLEncoder.encode(course2, "UTF-8") + "&" + URLEncoder.encode("course3", "UTF-8") + "=" + URLEncoder.encode(course3, "UTF-8") + "&" + URLEncoder.encode("course4", "UTF-8") + "=" + URLEncoder.encode(course4, "UTF-8") + "&" + URLEncoder.encode("course5", "UTF-8") + "=" + URLEncoder.encode(course5, "UTF-8");

                }
            } else if (type.equals("verify")) {
                String name = params[1];
                String student_num = params[2];
                String course = params[3];
                String date = params[4];
                String venue = params[5];

                //The URL below is used to send data to the server in order to login
                 login_url = "http://lamp.ms.wits.ac.za/~s1601745/verify.php";
                 post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("studentnum", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8") + "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" + URLEncoder.encode("venue", "UTF-8") + "=" + URLEncoder.encode(venue, "UTF-8");


            } else if (type.equals("login")) {
                String stu_num = params[1];
                String password = params[2];
                //The URL below is used to send data to the server in order to login
                 login_url = "http://lamp.ms.wits.ac.za/~s1601745/signin.php";
                post_data = URLEncoder.encode("studentnum", "UTF-8") + "=" + URLEncoder.encode(stu_num, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            } else if (type.equals("booking")) {
                //The code below receives input from other classes to use the BackgroundWorker to send Booking/Claim form information to the server
                String name = qrGenerator.Global.GetName();
                String student_num = qrGenerator.Global.GetStudent_Num();
                String course = params[3];
                String date = params[4];
                String venue = params[5];
                String startTime = params[6];
                String endTime = params[7];
                String valid = "0";
                System.out.println("Pencil "+name+" "+student_num+" "+course+" "+date+" "+venue+" "+startTime+" "+endTime);
                //The URL to send data to the server when creating a booking/claim form
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/booking.php";
                post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_no", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8") + "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" + URLEncoder.encode("venue", "UTF-8") + "=" + URLEncoder.encode(venue, "UTF-8") + "&" + URLEncoder.encode("chkStartTime", "UTF-8") + "=" + URLEncoder.encode(startTime, "UTF-8") + "&" + URLEncoder.encode("chkEndTime", "UTF-8") + "=" + URLEncoder.encode(endTime, "UTF-8") + "&" + URLEncoder.encode("valid", "UTF-8") + "=" + URLEncoder.encode(valid, "UTF-8");

            } else if (type.equals("fetching")) {
                String name = params[1];
                String student_num = params[2];

                //The URL to send data to the server when creating a booking/claim form
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/fetching.php";
                post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_no", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8");

            }
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

    @Override
    protected void onPostExecute(String result) {
        try {
            if (type == "login") {
                //Converts the result from the server to JSON format
                JSONObject ja = new JSONObject(result);
                //If the value returned from the server is "0",implies that the login is unsuccessful
                if (ja.get("result").toString().equals("0")) {
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_LONG).show();
                } else {
                    //If the result from the server implies the login was successful,accept the student number and name from the server and determine whether they are an admin or not
                    String stud_num = ja.getString("student_num");
                    String name = ja.getString("name");
                    String admin = ja.getString("admin");
                    qrGenerator.Global.setName(name);
                    qrGenerator.Global.setStudent_num(stud_num);


                    //If admin="0",implies the the user a not a lecturer(therefore a tutor)
                    if (admin.equals("0")) {
                        Intent i = new Intent(context, HomeActivity.class);
                        //Send the name and student number of the student to the home Activity
                        i.putExtra("name", name);
                        i.putExtra("stud_num", stud_num);

                        context.startActivity(i);
                    } else {
                        //If the admin is a lecturer(admin="1",they will be directed to a homescreen which will allow them to scan QR Codes
                        Intent i = new Intent(context, mainQR.class);
                        //Send the name to the lecturers home screen
                        i.putExtra("name", name);
                        qrGenerator.Global.setName(name);
                        qrGenerator.Global.setStudent_num(stud_num);
                        context.startActivity(i);
                    }
                }
            } else if (type == "verify") {
                //Converts the result from the server to JSON format
                JSONObject ja = new JSONObject(result);
                System.out.println("Table "+ja.get("result").toString());
                if (ja.get("result").toString().equals("0")) {
                    Toast.makeText(context, "Confirmation Successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context,HomeActivity.class);
                    context.startActivity(i);
                } else {
                    Toast.makeText(context, "Confirmation Unsuccessful", Toast.LENGTH_LONG).show();

                }
            } else if (type == "booking") {
                JSONObject ja = new JSONObject(result);
                System.out.println("The result "+ja.get("result").toString());
                if (ja.get("result").toString().equals("0")) {
                    Toast.makeText(context, "Booking Successful", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, qrGenerator.class);
                    context.startActivity(i);
                } else {
                    Toast.makeText(context, "This is a duplicate claim! Please try again!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context, Claim_Form.class);
                    context.startActivity(i);

                }
            } else if (type == "fetching") {
                JSONObject ja = new JSONObject(result);

                String[] dates = ja.getString("dates").split(",");
                String[] courses = ja.getString("courses").split(",");
                String[] start_time = ja.getString("start_time").split(",");
                String[] end_time = ja.getString("end_time").split(",");
                String[] venue = ja.getString("venue").split(",");
                String[] valid = ja.getString("valid").split(",");

                Intent intent1 = new Intent("INTENT_1").putExtra("dates", dates);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);

                Intent intent2 = new Intent("INTENT_2").putExtra("courses", courses);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent2);

                Intent intent3 = new Intent("INTENT_3").putExtra("start_time", start_time);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent3);

                Intent intent4 = new Intent("INTENT_4").putExtra("end_time", end_time);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent4);

                Intent intent5 = new Intent("INTENT_5").putExtra("venue", venue);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent5);

                Intent intent6 = new Intent("INTENT_6").putExtra("valid", valid);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent6);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
