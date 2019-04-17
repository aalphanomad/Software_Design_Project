package com.example.projectgamma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String rDate[];
        String rCourses[];
        String rVenue[];
        String rStart[];
        String rEnd[];
        String rDuration[];
        String rValid[];
        //class will take in the above arrays

        MyAdapter(Context c, String date[], String courses[], String venue[], String start[], String end[], String valid[]){
            super(c, R.layout.customlist, R.id.dateHistory, date);
            this.context = c;
            this.rDate = date;
            this.rCourses = courses;
            this.rVenue = venue;
            this.rStart = start;
            this.rEnd = end;
            this.rValid = valid;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.customlist, parent, false);
            //declare our textviews
            TextView theDate = row.findViewById(R.id.dateHistory);
            TextView theSubject = row.findViewById(R.id.subjectHistory);
            TextView theVenue = row.findViewById(R.id.venueHistory);
            TextView theDuration = row.findViewById(R.id.durationHistory);
            TextView theStatus = row.findViewById(R.id.status);
            //set our textviews with values from the arrays passed in
            theDate.setText(rDate[position]);
            theSubject.setText("Course: " + rCourses[position]);
            theVenue.setText("Venue: " + rVenue[position]);
            theDuration.setText("Duration: " + rStart[position] + " - " + rEnd[position]);
            //if tutor session has been validated then set text to:
            if(rValid[position].equals("1")) {
                theStatus.setText("Status: Validated");
            }
            return row;
        }
    }
