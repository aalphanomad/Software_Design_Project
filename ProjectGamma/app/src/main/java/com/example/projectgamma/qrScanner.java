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

    String[] The_details=new String[8];
String test=result.toString();
String type="verify";
The_details=test.split(",");
String valueName=The_details[1];
String valueStud_num=The_details[2];
String valueCourse=The_details[3];
String valueDate=The_details[4];
String valueVenue=The_details[5];
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, valueName, valueStud_num, valueCourse,valueDate, valueVenue);

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