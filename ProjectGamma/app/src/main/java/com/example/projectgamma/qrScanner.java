package com.example.projectgamma;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

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

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.projectgamma.qrGenerator.Global.course;


public class qrScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView ScannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);
            }

        }
    }

    @Override
    public void handleResult(Result result) {

    String[] The_details=new String[8];
String test=result.toString();
System.out.println("HEEY"+test);
String type="verify";
The_details=test.split(",");
String valueName=The_details[1].substring(1);
String valueStud_num=The_details[2].substring(1);
String valueCourse=The_details[3].substring(1);
String valueDate=The_details[4].substring(1);
String valueVenue=The_details[5].substring(1,The_details[5].length()-1);
String output;
                try{

                    String login_url = "http://lamp.ms.wits.ac.za/~s1601745/get_courses.php?";
                    String post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(qrGenerator.Global.GetStudent_Num(), "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(valueCourse, "UTF-8") ;
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
                    output = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        output += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    try {
                        JSONObject ja = new JSONObject(output);
                        String ans=ja.getString("result").toString();
                        System.out.println(ans+"THIS WAS RETURNED");
                        System.out.println(valueCourse);
                        System.out.println("["+'"'+valueCourse+'"'+"]");
                        if(ans.equals("["+'"'+valueCourse+'"'+"]")){
                            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                            backgroundWorker.execute(type, valueName, valueStud_num, valueCourse,valueDate, valueVenue);
                        }
                        else {
                            Toast.makeText(this,"Confirmation Failed. Please get a Lecturer for this course to validate the claim", Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();


                }

        onBackPressed();

    }

    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }

    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}