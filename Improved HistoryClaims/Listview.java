package com.example.projectgamma;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Listview extends AppCompatActivity {

    ListView listview;
    //gets value from claims form generator for now
    String subject = qrGenerator.Global.GetCourse();
    String date = qrGenerator.Global.GetDate();
    String venue = qrGenerator.Global.GetVenue();
    String duration;

    //stores values into arrays to pass to the ArrayAdapter
    String[] mDate = {date};
    String[] mSubject = {subject};
    String[] mVenue = {venue};
    String[] mDuration = {duration};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listview = findViewById(R.id.lv);

        //call adapter class to take in our arrays
        MyAdapter adapter = new MyAdapter(this, mDate, mSubject, mVenue, mDuration);
        listview.setAdapter(adapter); //update listview with values passed through

        /*listview.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    Toast.makeText(Listview.this, "Not Validated", Toast.LENGTH_SHORT).show();
                }
            }

        });*/
    }

    class MyAdapter extends ArrayAdapter<String>{
        //global arrays for the class
        Context context;
        String rDate[];
        String rSubject[];
        String rVenue[];
        String rDuration[];

        //class will set above arrays to those passed in
        MyAdapter(Context c, String date[], String subject[], String venue[], String duration[]){
            super(c, R.layout.customlist, R.id.dateHistory, date);
            //set the global arrays of class to the those that have been given to the parameters 
            this.context = c;
            this.rDate = date;
            this.rSubject = subject;
            this.rVenue = venue;
            this.rDuration = duration;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            //using class to represent layout in xml
            LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //row will be rendered by creating view object in memory. 
            View row = inflater.inflate(R.layout.customlist, parent, false);

            //declare our textviews
            TextView theDate = row.findViewById(R.id.dateHistory);
            TextView theSubject = row.findViewById(R.id.subjectHistory);
            TextView theVenue = row.findViewById(R.id.venueHistory);
            TextView theDuration = row.findViewById(R.id.durationHistory);
            TextView theStatus = row.findViewById(R.id.status);

            //set our textviews with values from the arrays passed in
            theDate.setText(rDate[position]);
            theSubject.setText("Subject: " + rSubject[position]);
            theVenue.setText("Venue: " + rVenue[position]);
            theDuration.setText("Duration: " + rDuration[position]);
            //if tutor session has been validated then set text to:
            //theStatus.setText("Status: Validated");


            return row;
        }
    }
}
