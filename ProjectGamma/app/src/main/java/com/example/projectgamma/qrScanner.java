package com.example.projectgamma;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class qrScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView ScannerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if( ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);
            }

        }
    }

    @Override
    public void handleResult(Result result) {
        String type="booking";
String name= qrGenerator.Global.GetName();
String student_num=qrGenerator.Global.GetStudent_Num();
String date=qrGenerator.Global.GetDate();
String course=qrGenerator.Global.GetCourse();
String venue=qrGenerator.Global.GetVenue();
String duration=qrGenerator.Global.GetDuration();
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, name, student_num,date,course,venue,duration);
System.out.println("THE DATA!!!"+name+" "+student_num+" "+date+" "+course+" "+venue+" "+duration);
        Toast.makeText(this, "Confirmation Complete", Toast.LENGTH_LONG).show();

        onBackPressed();

    }

    protected void onPause(){
        super.onPause();
        ScannerView.stopCamera();
    }

    protected void onResume(){
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}