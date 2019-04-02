package com.example.projectgamma;

//Android Import Statements
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
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


     Context context;
    private AlertDialog alertDialog;
String type;
    BackgroundWorker(Context ctx) {
        context = ctx;

    }


    @Override
    protected String doInBackground(String... params) {
        //Determines whether the backgroundWorker is being imlemented for a registreation,logon or booking(String type)
        type = params[0];
        String login_url;
        String reg_url;
        //The below is executed if we are trying to use BackgroundWorker for registering
        if (type.equals("reg")) {
            try {
//Specifies the URL in order to register
                reg_url = "http://lamp.ms.wits.ac.za/~s1601745/create.php/";
//Initialize an HTTP POST connection to send data to the server
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

//Retrieves the informations passed from other classes to BackgroundWorker if we are trying to register
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

                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("studentnum", "UTF-8") + "=" + URLEncoder.encode(stu_num, "UTF-8") + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" + URLEncoder.encode("admin", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
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
        } else if (type == "verify") {
            try {
                //Accepts the input passed from other classes which will be used to verify
                String name = params[1];
                String student_num = params[2];
                String course = params[3];
                String date = params[4];
                String venue = params[5];
                String startTime = params[6];
                String endTime = params[7];
                String valid="1";
                //The URL below is used to send data to the server in order to login
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/verify.php";
                URL url = new URL(login_url);

                //The code below creates an HTTP POST connection
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //Creates the URL to send the data to the server in order to login
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8") + "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" + URLEncoder.encode("venue", "UTF-8") + "=" + URLEncoder.encode(venue, "UTF-8") + "&" + URLEncoder.encode("start_time", "UTF-8") + "=" + URLEncoder.encode(startTime, "UTF-8") + "&" + URLEncoder.encode("end_time", "UTF-8") + "=" + URLEncoder.encode(endTime, "UTF-8") + "&" + URLEncoder.encode("valid", "UTF-8") + "=" + URLEncoder.encode(valid, "UTF-8");

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



        } else if (type == "login") {
            try {
                //Accepts the input passed from other classes which will be used to login
                String stu_num = params[1];
                String password = params[2];
                //The URL below is used to send data to the server in order to login
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/signin.php";
                URL url = new URL(login_url);

                //The code below creates an HTTP POST connection
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //Creates the URL to send the data to the server in order to login
                String post_data = URLEncoder.encode("studentnum", "UTF-8") + "=" + URLEncoder.encode(stu_num, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

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

//The code below is executed if we are using BackgroundWorker to create a booking(generating a claim form)
        } else if (type == "booking") {
            try {

                //The code below recieves input from other classes to use the BackgroundWorker to send Booking/Claim form information to the server
                String name = params[1];
                String student_num = params[2];
                String course = params[3];
                String date = params[4];
                String venue = params[5];
                String startTime = params[6];
                String endTime = params[7];
                String valid = "0";
                System.out.println("WTF"+name+" "+student_num+" "+startTime+" "+endTime);
                //The URL to send data to the server when creating a booking/claim form
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/booking.php";
                URL url = new URL(login_url);
                //The code below initializes an HTP POST request
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //Creating the URL in order to send data for generating a claim form to the server
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_no", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8") + "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" + URLEncoder.encode("venue", "UTF-8") + "=" + URLEncoder.encode(venue, "UTF-8") + "&" + URLEncoder.encode("chkStartTime", "UTF-8") + "=" + URLEncoder.encode(startTime, "UTF-8") + "&" + URLEncoder.encode("chkEndTime", "UTF-8") + "=" + URLEncoder.encode(endTime, "UTF-8") + "&" + URLEncoder.encode("valid", "UTF-8") + "=" + URLEncoder.encode(valid, "UTF-8");
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

        }
        // this block deals with the generation and download of PDF's
        else if (type.equals("PDF"))
        {
            Log.d("PDF_STUFF", "doInBackground: entering pdf section");
            try {

                //The code below recieves input from other classes to use the BackgroundWorker to send Booking/Claim form information to the server
                String student_num = params[1];
                //The URL to send data to the server when creating a booking/claim form
                String target_url = "http://lamp.ms.wits.ac.za/~s1601745/select_booking.php?";
                URL url = new URL(target_url);
                //The code below initializes an HTP POST request
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                //the parameters here are parameters in the SQL_Query on the PHP file
                //Specifically it tells it to select all fields of all records of the table BOOKINGS
                //with student number provided
                String post_data = "table=BOOKINGS&target=*&student_number="+student_num;
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
                // The result we are expecting is a string which points to the PDF_report generated
                // When calling this method we want to open the report in the browser
                // so when calling this method start a new intent
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
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


                    //If admin="0",implies the the user a not a lecturer(therefore a tutor)
                    if (admin.equals("0")) {
                        Intent i = new Intent(context, HomeActivity.class);
                        //Send the name and student number of the student to the home Activity
                        i.putExtra("name", name);
                        i.putExtra("stud_num", stud_num);
                        //Set the values of the Global class containing the details of the user
                        qrGenerator.Global.setName(name);
                        qrGenerator.Global.setStudent_num(stud_num);
                        context.startActivity(i);
                    } else {
                        //If the admin is a lecturer(admin="1",they will be directed to a homescreen which will allow them to scan QR Codes
                        Intent i = new Intent(context, mainQR.class);
                        //Send the name to the lecturers home screen
                        i.putExtra("name", name);
                        qrGenerator.Global.setName(name);
                        context.startActivity(i);
                    }
                }
            }


            else if(type=="verify"){
    //Converts the result from the server to JSON format
    JSONObject ja = new JSONObject(result);
    if (ja.get("answer").toString().equals("Successful")) {
        Toast.makeText(context, "Confirmation Successful", Toast.LENGTH_LONG).show();
        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
    }
    else{
        Toast.makeText(context, "Confirmation Unsuccessful", Toast.LENGTH_LONG).show();

    }


}else if(type=="booking"){
                JSONObject ja = new JSONObject(result);
                System.out.println("THe truth "+ja.get("result").toString());
if(ja.get("result").toString().equals("0")){
    Toast.makeText(context, "Booking Successful", Toast.LENGTH_LONG).show();
    Intent i = new Intent(context, HomeActivity.class);
    context.startActivity(i);
}else{
    Toast.makeText(context, "This is a duplicate claim! Please try again!", Toast.LENGTH_LONG).show();

}



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
