package com.example.projectgamma;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import static com.example.projectgamma.qrGenerator.Global.duration;
import static com.example.projectgamma.qrGenerator.Global.name;
import static com.example.projectgamma.qrGenerator.Global.student_num;
import static com.example.projectgamma.qrGenerator.Global.venue;

public class qrGenerator extends AppCompatActivity {

    public static class Global{
        public static String name;
        public static String student_num;
        public static String course;
        public static String date;
        public static String venue;
        public static String duration;

        public static String GetName() {
            return name;
        }
            public static String GetStudent_Num(){
                return student_num;
            }
            public static String GetCourse(){
                return course;
            }
            public static String GetDate(){
                return date;
            }
            public static String GetVenue(){
                return venue;
            }
            public static String GetDuration(){
                return duration;
            }
        public static void setName(String name){
            Global.name=name;
        }
        public static void setStudent_num(String student_num){
            Global.student_num=student_num;
        }
        public static void setCourse(String course){
            Global.course=course;
        }
        public static void setDate(String date){
            Global.date=date;
        }
        public static void setVenue(String venue){
            Global.venue=venue;
        }
        public static void setDuration(String duration){
            Global.duration=duration;
        }
        }





    private ImageView imageView;
    public static TextView  resultTV0, resultTV1, resultTV2, resultTV3, resultTV4, resultTV5, resultTV6;
    String valueCourse,valueName,valueStud_num, valueVenue,valueTime;

    String currentDate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrgenerator);

        Calendar calender = Calendar.getInstance();
         currentDate = DateFormat.getDateInstance().format(calender.getTime());


        Bundle bundle=getIntent().getExtras();
        resultTV0 = (TextView)findViewById(R.id.Course_tv);
        resultTV1 = (TextView)findViewById(R.id.Name_tv);
        resultTV2= (TextView)findViewById(R.id.SN_tv);
        resultTV4 = (TextView)findViewById(R.id.Date_tv);
        resultTV5 = (TextView)findViewById(R.id.Venue_tv);
        resultTV6 = (TextView)findViewById(R.id.Dur_tv);
        valueName=bundle.getString("name");
        valueStud_num=bundle.getString("student_num");
        valueCourse=bundle.getString("course");
            valueVenue= bundle.getString("venue");
        valueTime=bundle.getString("time");

        Global.setName(valueName);
        Global.setStudent_num(valueStud_num);
        Global.setCourse(valueCourse);
        Global.setDate(currentDate);
        Global.setCourse(valueCourse);
        Global.setVenue(valueVenue);
        Global.setDuration(valueTime);

 resultTV0.setText("Course Tutored: "+valueCourse);
 resultTV1.setText("Name: "+valueName);
 resultTV2.setText("Student No: "+valueStud_num);
 resultTV4.setText("Date: "+currentDate);
 resultTV5.setText("Venue: "+valueVenue);
 resultTV6.setText("Duration of Session: "+valueTime);





    }

    public void generate(View v) {
        imageView = findViewById(R.id.imageView);


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            Global.setName(valueName);
            Global.setStudent_num(valueStud_num);
            Global.setCourse(valueCourse);
            Global.setDate(currentDate);
            Global.setCourse(valueCourse);
            Global.setVenue(valueVenue);
            Global.setDuration(valueTime);
            ArrayList arr = new ArrayList( );
            arr.add(valueName);
            arr.add(valueStud_num);
            arr.add(valueCourse);
            arr.add(currentDate);
            arr.add(valueVenue);
            arr.add(valueTime);
            for(int i =0;i<arr.size();i++){
                System.out.println(arr.get(i)+" ");
            }
            BitMatrix bitMatrix = multiFormatWriter.encode(String.valueOf(arr), BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void doneGen(View view) {

        Intent intent=new Intent(this,HomeActivity.class);

        startActivity(intent);
    }

}