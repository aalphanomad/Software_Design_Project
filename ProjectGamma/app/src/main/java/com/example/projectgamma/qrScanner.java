package com.example.projectgamma;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

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
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);
            }

        }
    }

    @Override
    public void handleResult(Result result) {
    /*String type = "booking";
        String name = qrGenerator.Global.GetName();
        System.out.println("HELLO"+name);
        String student_num = qrGenerator.Global.GetStudent_Num();
        String date = qrGenerator.Global.GetDate();
        String course = qrGenerator.Global.GetCourse();
        String venue = qrGenerator.Global.GetVenue();
        String duration = qrGenerator.Global.GetDuration();
        System.out.println("THE DATA!!!" + name + " " + student_num + " " + date + " " + course + " " + venue + " " + duration);
*/
    String[] arr=new String[8];
String test=result.toString();
arr=test.split(",");
    String type="booking" ;

    String name=arr[0].substring(1,arr[0].length());
    String student_num=arr[1];
    String course=arr[2];
    String date=arr[3]+arr[4];
    String venue=arr[5];
    String duration=arr[6].substring(0,arr[6].length()-1);
        System.out.println("THE DATA!!!" + name + " " + student_num + " " + date + " " + course + " " + venue + " " + duration);

        Toast.makeText(this, "Confirmation Complete", Toast.LENGTH_LONG).show();
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, name, student_num, course,date, venue, duration);


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