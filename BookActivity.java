package com.example.projectgamma;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class BookActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
String date,time,duration,subject,add1,add2;
TextView et_duration,et_subject,et_add1,et_add2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_form);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        et_duration=(TextView)findViewById(R.id.Duration);
        et_subject=(TextView)findViewById(R.id.Subject);
        et_add1=(TextView)findViewById(R.id.Add1);
        et_add2=(TextView)findViewById(R.id.Add2);


        Button TimeBut = (Button) findViewById(R.id.Timebut);
        TimeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        Button book=(Button)findViewById(R.id.BookButton);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book();
            }
        });

    }

    public void book(){
        initialize();
        if(validate()==false)
            {
                Toast.makeText(this, "Booking has failed", Toast.LENGTH_SHORT).show();
            } else {
            OnProcess();
        }


    }
    public boolean validate() {
        boolean valid = true;
        if (duration.isEmpty() || duration.length() > 32) {
            et_duration.setError("Please enter the duration");
            valid = false;
        }

        if (subject.isEmpty()) {
            et_duration.setError("Please enter a subject");
            valid = false;
        }
        if (add1.isEmpty()) {
            et_add1.setError("Please enter a valid address");
            valid = false;
        }
        if (add2.isEmpty()) {
            et_add2.setError("Please enter a valid address");
            valid = false;
        }
        return valid;
    }





    public void OnProcess(){
        InputStream is = null;
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        //nameValuePairs.add(new BasicNameValuePair("STUDENT_ID", name));
        //nameValuePairs.add(new BasicNameValuePair("TUTOR_ID", name));
        nameValuePairs.add(new BasicNameValuePair("TIME", time));
        nameValuePairs.add(new BasicNameValuePair("DATE", date));
        nameValuePairs.add(new BasicNameValuePair("DURATION", duration));
        nameValuePairs.add(new BasicNameValuePair("SUBJECT", subject));
        nameValuePairs.add(new BasicNameValuePair("ADD_1", add1));
        nameValuePairs.add(new BasicNameValuePair("ADD_2", add2));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://lamp.ms.wits.ac.za/~s1601745/arrangement.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is=entity.getContent();
        } catch (ClientProtocolException e) {
            System.out.print("Error!");
        } catch (IOException e) {
            System.out.print("CHECK");
        }
        Intent intent = new Intent(BookActivity.this, HomeActivity.class);
        startActivity(intent);

    }






    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView) findViewById(R.id.ShowTime);
        time=hourOfDay+":"+minute;
        textView.setText(hourOfDay+":"+minute);
    }

    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");

    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        date=dateFormat.format(calendar.getTime());
        ((TextView) findViewById(R.id.showDate)).setText(dateFormat.format(calendar.getTime()));
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }

    }

    public void initialize() {
       duration = et_duration.getText().toString().trim();
        subject = et_subject.getText().toString().trim();
        add1 = et_add1.getText().toString().trim();
        add2 = et_add2.getText().toString().trim();
    }

}