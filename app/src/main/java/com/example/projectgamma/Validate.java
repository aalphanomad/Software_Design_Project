package com.example.projectgamma;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.projectgamma.qrGenerator.Global.course;
import static com.example.projectgamma.qrGenerator.Global.date;
import static com.example.projectgamma.qrGenerator.Global.name;
import static com.example.projectgamma.qrGenerator.Global.student_num;
import static com.example.projectgamma.qrGenerator.Global.venue;
import static com.example.projectgamma.qrGenerator.Global.startTime;
import static com.example.projectgamma.qrGenerator.Global.endTime;

public class Validate extends AppCompatActivity {


    private ImageView imageView;
    public static TextView  resultTV0, resultTV1, resultTV2, resultTV4, resultTV5, resultTV6;
    String valueCourse,valueName,valueStud_num, valueVenue,valueDuration,valueDate,status;
    Button generate;

    String currentDate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrgenerator);


        valueName=qrGenerator.Global.GetName();
        valueStud_num=qrGenerator.Global.GetStudent_Num();
        Bundle bundle = getIntent().getExtras();
        //valueCourse=bundle.getString("course");
        /*valueDate=bundle.getString("date");
        valueVenue=bundle.getString("venue");
        valueDuration=bundle.getString("duration");
        status=bundle.getString("status");*/


        resultTV0 = (TextView)findViewById(R.id.Course_tv);
        resultTV1 = (TextView)findViewById(R.id.Name_tv);
        resultTV2= (TextView)findViewById(R.id.SN_tv);
        resultTV4 = (TextView)findViewById(R.id.Date_tv);
        resultTV5 = (TextView)findViewById(R.id.Venue_tv);
        resultTV6 = (TextView)findViewById(R.id.Dur_tv);
        generate=(Button)findViewById(R.id.Generate_but);

        //Gets the name,student_num,course selected,venue and duration

        //Sets the texts of the Labels
        resultTV0.setText("Course Tutored: "+valueCourse);
        resultTV1.setText("Name: "+valueName);
        resultTV2.setText("Student No: "+valueStud_num);
        resultTV4.setText("Date: "+valueDate);
        resultTV5.setText("Venue: "+valueVenue);
        resultTV6.setText("Time: "+valueDuration);

/*if(!status.equals("Status: Not Validated")){
    generate.setVisibility(View.INVISIBLE);
}*/

    }

    public void generate(View v) {
        imageView = findViewById(R.id.imageView);


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {



ArrayList data=new ArrayList();
data.add("verify");
data.add(valueName);
data.add(valueStud_num);
data.add(valueCourse);
data.add(valueDate);
data.add(valueVenue);



            BitMatrix bitMatrix = multiFormatWriter.encode(data.toString(), BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void doneGen(View view) {
//Sets the "Done" button to go back to the HomeScreen
        Intent intent=new Intent(this,HomeActivity.class);

        startActivity(intent);
    }

}