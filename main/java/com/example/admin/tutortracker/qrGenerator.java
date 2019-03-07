package com.example.admin.tutortracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DateFormat;
import java.util.Calendar;

public class qrGenerator extends AppCompatActivity {

    private ImageView imageView;
    public static TextView  resultTV0, resultTV1, resultTV2, resultTV3, resultTV4, resultTV5, resultTV6;
    String valueHour, valueMin1, valueMin2, valueVenue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrgenerator);

        Calendar calender = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calender.getTime());


        Bundle bundle=getIntent().getExtras();
        resultTV0 = (TextView)findViewById(R.id.tvCourse);
        resultTV1 = (TextView)findViewById(R.id.tv1);
        resultTV2= (TextView)findViewById(R.id.tv2);
        resultTV4 = (TextView)findViewById(R.id.tvDate);
        resultTV5 = (TextView)findViewById(R.id.tvVenue);
        resultTV6 = (TextView)findViewById(R.id.tvDur);

        if (getIntent().getStringExtra("venue") != null && getIntent().getStringExtra("hr") != null && getIntent().getStringExtra("min1") != null && getIntent().getStringExtra("min2") != null) {
            //value1 = bundle.getString("e1");
            //value2 = bundle.getString("e2");
            valueVenue= bundle.getString("venue");
            valueHour= bundle.getString("hr");
            valueMin1= bundle.getString("min1");
            valueMin2= bundle.getString("min2");
        }

        //resultTV1.setText("Name: "+value1);
        //resultTV2.setText("Student No: "+value2);
        //resultTV3.setText("Payroll No: "+value3);
        resultTV4.setText("Date: " + currentDate);
        resultTV5.setText("Venue: " + valueVenue);
        resultTV6.setText("Duration Of Lecture: "+valueHour+" hr : " + valueMin1+valueMin2+" min");

    }

    public void generate(View v) {
        imageView = findViewById(R.id.imageView);


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode("test", BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void doneGen(View view) {
        Intent intent=new Intent(this,homePage.class);

        //final String subject = "Coms";

        intent.putExtra("venue", valueVenue);
        intent.putExtra("hr", valueHour);
        intent.putExtra("min1", valueMin1);
        intent.putExtra("min2", valueMin2);
        startActivity(intent);
    }

}
