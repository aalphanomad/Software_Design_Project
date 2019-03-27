package com.example.projectgamma;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class qrGenerator extends AppCompatActivity {

    //A Global class which makes it veryeasy to get and set the information of the user such as the
    //users name,student number,courses tutoring,the date,etc

    public static class Global{
        public static String name;
        public static String student_num;
        public static String course;
        public static String date;
        public static String venue;
        public static String startTime, endTime;


        //Get functions
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
        public static String GetStartTime(){
            return startTime;
        }
        public static String GetEndTime(){
            return endTime;
        }
            //Set functions
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
        public static void setStartTime(String startTime){
            Global.startTime=startTime;
        }
        public static void setEndTime(String endTime){
            Global.endTime=endTime;
        }
    }





    private ImageView imageView;
    public static TextView  resultTV0, resultTV1, resultTV2, resultTV3, resultTV4, resultTV5, resultTV6;
    String valueCourse,valueName,valueStud_num, valueVenue,valueStartTime, valueEndTime;

    String currentDate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrgenerator);

        //Gets the current date
        Calendar calender = Calendar.getInstance();
         currentDate = DateFormat.getDateInstance().format(calender.getTime());

//Assigns components(textviews) to variables
valueCourse=qrGenerator.Global.GetCourse();
valueName=qrGenerator.Global.GetName();
valueStud_num=qrGenerator.Global.GetStudent_Num();
valueVenue=qrGenerator.Global.GetVenue();
valueStartTime=qrGenerator.Global.GetStartTime();
valueEndTime=qrGenerator.Global.GetEndTime();

        resultTV0 = (TextView)findViewById(R.id.Course_tv);
        resultTV1 = (TextView)findViewById(R.id.Name_tv);
        resultTV2= (TextView)findViewById(R.id.SN_tv);
        resultTV4 = (TextView)findViewById(R.id.Date_tv);
        resultTV5 = (TextView)findViewById(R.id.Venue_tv);
        resultTV6 = (TextView)findViewById(R.id.Dur_tv);

        //Gets the name,student_num,course selected,venue and duration

        //Sets the texts of the Labels
        resultTV0.setText("Course Tutored: "+valueCourse);
        resultTV1.setText("Name: "+valueName);
        resultTV2.setText("Student No: "+valueStud_num);
        resultTV4.setText("Date: "+currentDate);
        resultTV5.setText("Venue: "+valueVenue);
        resultTV6.setText("Time: "+valueStartTime + " - " + valueEndTime);
//ADD START  AND END TIME

        //Sets the respective values
        valueStartTime=valueStartTime.replaceAll("\\s+","");
        valueStartTime=valueStartTime.replaceAll("\\s+","");
        Global.setStartTime(valueStartTime);
        Global.setEndTime(valueEndTime);







    }

    public void generate(View v) {
        imageView = findViewById(R.id.imageView);


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            String type="booking" ;
//Displays a Toast if the Confirmation is successful
            //Toast.makeText(this, "Confirmation Complete", Toast.LENGTH_LONG).show();
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, valueName, valueStud_num, valueCourse,currentDate, valueVenue,valueStartTime,valueEndTime);




            BitMatrix bitMatrix = multiFormatWriter.encode("test", BarcodeFormat.QR_CODE, 500, 500);
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