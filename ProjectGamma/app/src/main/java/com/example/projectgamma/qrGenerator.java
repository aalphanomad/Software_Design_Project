package com.example.projectgamma;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static com.example.projectgamma.qrGenerator.Global.GetName;
import static com.example.projectgamma.qrGenerator.Global.GetStudent_Num;
import static com.example.projectgamma.qrGenerator.Global.course;
import static com.example.projectgamma.qrGenerator.Global.date;
import static com.example.projectgamma.qrGenerator.Global.name;
import static com.example.projectgamma.qrGenerator.Global.student_num;
import static com.example.projectgamma.qrGenerator.Global.venue;
import static com.example.projectgamma.qrGenerator.Global.startTime;
import static com.example.projectgamma.qrGenerator.Global.endTime;

public class qrGenerator extends AppCompatActivity {

    //A Global class which makes it very easy to get and set the information of the user such as the
    //users name,student number,courses tutoring,the date,etc

    public static class Global{
        public static String name;
        public static String student_num;
        public static String course;
        public static String date;
        public static String venue;
        public static String startTime, endTime;
        public static String[] get_courses;
        public static String role;

        Boolean QR=false;



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
        public static String[] Get_Courses(){
            return get_courses;
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
        public static String GetRole(){return role;}
        public static String[] formatter(String[] arr){


            if(!arr[0].equals("[]")) {
                arr[0] = arr[0].substring(1);
                arr[arr.length - 1] = arr[arr.length - 1].substring(0, arr[arr.length - 1].length() - 1);

                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr[i].substring(1, arr[i].length() - 1);
                }
                return arr;
            }
            else{
                return arr;
            }
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
        public static void setGet_courses(String[] courses){
            Global.get_courses=courses;
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
        public static void setRole(String role){Global.role=role;}
    }





    private ImageView imageView;
    public static TextView  resultTV0, resultTV1, resultTV2, resultTV3, resultTV4, resultTV5, resultTV6;
    String valueCourse,valueName,valueStud_num, valueVenue,valueStartTime, valueEndTime;
    Button generate,Validate,Confirm;
    EditText StudentNumber,Password;
    String currentDate;
    String ans;
    CharSequence df;
    String[] CourseCode;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrgenerator);

        //Gets the current date
       // Calendar calender = Calendar.getInstance();
       // currentDate = DateFormat.getDateInstance().format(calender.getTime());
         df=  android.text.format.DateFormat.format("dd MMM yyyy",new java.util.Date());
        System.out.println("DATE"+df);
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
        StudentNumber=(EditText)findViewById(R.id.StudentNumber);
        Password=(EditText) findViewById(R.id.Password);



        Validate=(Button) findViewById(R.id.Validate);
        Confirm=(Button) findViewById(R.id.Confirm);

        //Gets the name,student_num,course selected,venue and duration

        //Sets the texts of the Labels
        resultTV0.setText("Course Tutored: "+valueCourse);
        resultTV1.setText("Name: "+valueName);
        resultTV2.setText("Student No: "+valueStud_num);
        resultTV4.setText("Date: "+df   );
        resultTV5.setText("Venue: "+valueVenue);
        resultTV6.setText("Time: "+valueStartTime + " - " + valueEndTime);
//ADD START  AND END TIME

        //Sets the respective values
        valueStartTime=valueStartTime.replaceAll("\\s+","");
        valueEndTime=valueEndTime.replaceAll("\\s+","");
        Global.setStartTime(valueStartTime);
        Global.setEndTime(valueEndTime);







    }
    public void Manual_Validation(View v){
        Toast.makeText(this,"Please Get a Lecturer to Validate this claim for you.",Toast.LENGTH_SHORT).show();
        StudentNumber.setVisibility(View.VISIBLE);
        Password.setVisibility((View.VISIBLE));
        Confirm.setVisibility((View.VISIBLE));
    }

    public void Testing(View v){

        Boolean valid=true;
        if(StudentNumber.length()==0){
            StudentNumber.setError("Pleas Enter a Student Number");
            valid=false;
        }
        if(Password.length()==0){
            Password.setError("Please Enter a Password");
            valid=false;
        }
        if(valid==true){
             CourseCode=course.split("-");
            try{
            String login_url = "http://lamp.ms.wits.ac.za/~s1601745/is_lecturer.php?";
            String post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(StudentNumber.getText().toString(), "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(LoginActivity.get_SHA_1_SecurePassword(Password.getText().toString(),StudentNumber.getText().toString()), "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(CourseCode[0], "UTF-8");
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
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                try {
                    JSONObject ja = new JSONObject(result);
 ans=ja.getString("result").toString();
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }
System.out.println("COURSE CODE"+CourseCode[0]);
    if(ans.equals("0")) {
        System.out.println("("+venue+")");
        BackgroundWorker backgroundWorker = new BackgroundWorker(qrGenerator.this);
        backgroundWorker.execute("verify", GetName(), GetStudent_Num(), CourseCode[0], df.toString(), venue);
    }
    else{
        Toast.makeText(this,"This is not a lecturer that lectures "+course,Toast.LENGTH_SHORT).show();
    }
        }
    }

    public void generate(View v) {
        imageView = findViewById(R.id.imageView);
        CourseCode=course.split("-");


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {


            ArrayList data=new ArrayList();
            data.add("verify");
            data.add(valueName);
            data.add(valueStud_num);
            data.add(CourseCode[0]);
            data.add(df.toString());
            data.add(valueVenue);

            System.out.println("BEFORE"+data);


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