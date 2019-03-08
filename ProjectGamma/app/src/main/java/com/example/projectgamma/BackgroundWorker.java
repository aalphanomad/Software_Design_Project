package com.example.projectgamma;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url;
        String reg_url;
        if (type.equals("reg")) {
            try {

                reg_url = "http://lamp.ms.wits.ac.za/~s1601745/create.php/";

                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


                String name = params[1];
                String stu_num = params[2];
                String email = params[3];
                String password = params[4];

                String course1;
                String course2;
                String course3;
                String course4;
                String course5;

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
        } else if (type == "login") {
            try {
                String stu_num = params[1];
                String password = params[2];
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/signin.php";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
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


        } else if (type == "booking") {
            try {
                String name = params[1];
                String student_num = params[2];
                String course = params[3];
                String date = params[4];
                String venue = params[5];
                String duration = params[6];
                login_url = "http://lamp.ms.wits.ac.za/~s1601745/booking.php";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(student_num, "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8") + "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" + URLEncoder.encode("venue", "UTF-8") + "=" + URLEncoder.encode(venue, "UTF-8") + "&" + URLEncoder.encode("duration", "UTF-8") + "=" + URLEncoder.encode(duration, "UTF-8");
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
        return null;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }


    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject ja = new JSONObject(result);

            if (ja.get("result").toString().equals("0")) {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_LONG).show();
            } else {
                String stud_num = ja.getString("student_num");
                String name = ja.getString("name");
                String admin = ja.getString("admin");

                if (admin.equals("0")) {
                    Intent i = new Intent(context, HomeActivity.class);
                    i.putExtra("name", name);
                    i.putExtra("stud_num", stud_num);
                    context.startActivity(i);
                } else {
                    Intent i = new Intent(context, mainQR.class);
                    i.putExtra("name", name);
                    context.startActivity(i);
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
