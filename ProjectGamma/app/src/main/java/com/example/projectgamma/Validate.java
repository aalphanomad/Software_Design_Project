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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
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

public class Validate extends AppCompatActivity {


    private ImageView imageView;
    public static TextView  resultTV0, resultTV1, resultTV2, resultTV4, resultTV5, resultTV6;
    String valueCourse,valueName,valueStud_num, valueVenue,valueDuration,valueDate,status;
    Button generate,Validate,Confirm;
EditText StudentNumber,Password;
    String currentDate;
    String ans;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrgenerator);


        valueName=qrGenerator.Global.GetName();
        valueStud_num=qrGenerator.Global.GetStudent_Num();
        Bundle bundle = getIntent().getExtras();
        valueCourse=bundle.getString("course");
        valueDate=bundle.getString("date");
        valueVenue=bundle.getString("venue");
        valueDuration=bundle.getString("duration");
        status=bundle.getString("status");



        resultTV0 = (TextView)findViewById(R.id.Course_tv);
        resultTV1 = (TextView)findViewById(R.id.Name_tv);
        resultTV2= (TextView)findViewById(R.id.SN_tv);
        resultTV4 = (TextView)findViewById(R.id.Date_tv);
        resultTV5 = (TextView)findViewById(R.id.Venue_tv);
        resultTV6 = (TextView)findViewById(R.id.Dur_tv);
        generate=(Button)findViewById(R.id.Generate_but);
          StudentNumber=(EditText)findViewById(R.id.StudentNumber);
          Password=(EditText) findViewById(R.id.Password);



         Validate=(Button) findViewById(R.id.Validate);
         Confirm=(Button) findViewById(R.id.Confirm);


        //Gets the name,student_num,course selected,venue and duration

        //Sets the texts of the Labels
        resultTV0.setText("Course Tutored: "+valueCourse);
        resultTV1.setText("Name: "+valueName);
        resultTV2.setText("Student No: "+valueStud_num);
        resultTV4.setText("Date: "+valueDate);
        resultTV5.setText("Venue: "+valueVenue);
        resultTV6.setText("Time: "+valueDuration);

if(!status.equals("Status: Not Validated")){
    Validate.setVisibility(View.INVISIBLE);
    generate.setVisibility(View.INVISIBLE);
}

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
            try{
                String login_url = "http://lamp.ms.wits.ac.za/~s1601745/is_lecturer.php?";
                String post_data = URLEncoder.encode("student_num", "UTF-8") + "=" + URLEncoder.encode(StudentNumber.getText().toString(), "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(LoginActivity.get_SHA_1_SecurePassword(Password.getText().toString(),StudentNumber.getText().toString()), "UTF-8") + "&" + URLEncoder.encode("course", "UTF-8") + "=" + URLEncoder.encode(valueCourse, "UTF-8");
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
            if(ans.equals("0")) {
                System.out.println("(PPPPP"+valueCourse+")");
                BackgroundWorker backgroundWorker = new BackgroundWorker(Validate.this);
                backgroundWorker.execute("verify", GetName(), GetStudent_Num(), valueCourse, valueDate, valueVenue);
            }
            else{
                Toast.makeText(this,"This is not a lecturer that lectures "+valueCourse,Toast.LENGTH_SHORT).show();
            }
        }
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