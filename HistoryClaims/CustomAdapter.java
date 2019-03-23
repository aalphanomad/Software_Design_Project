package com.example.projectgamma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String> {



    public CustomAdapter(Context context, String[] test) {
        super(context, R.layout.customlist, test);  //receiving input such as course, date, etc.
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.customlist, parent, false); //setting our custom made view which will be returned
        //getting specific deatils from array using getView method
        String date = getItem(0);   
        String subject = getItem(1);
        String venue = getItem(2);
        String duration = getItem(3);
        System.out.println(getItem(0));
        //declaring textviews
        TextView tvDate = (TextView) customView.findViewById(R.id.dateHistory);
        TextView tvSubject = (TextView) customView.findViewById(R.id.subjectHistory);
        TextView tvVenue = (TextView) customView.findViewById(R.id.venueHistory);
        TextView tvDuration = (TextView) customView.findViewById(R.id.durationHistory);
        TextView tvStatus = (TextView) customView.findViewById(R.id.status);
        //setting textviews with appropriate information
        tvDate.setText("Date: " + date);
        tvSubject.setText("Subject: " + subject);
        tvVenue.setText("Venue: " + venue);
        tvDuration.setText("Duration: " + duration);
        tvStatus.setText("Status: Not Verified");

        return customView;
    }
}
