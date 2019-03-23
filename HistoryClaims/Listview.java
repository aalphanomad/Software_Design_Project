package com.example.projectgamma;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Listview extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        String type="history" ;

        String course = qrGenerator.Global.GetCourse();
        String date = qrGenerator.Global.GetDate();
        String venue = qrGenerator.Global.GetVenue();
        String duration = "2hrs";
        //BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        //backgroundWorker.execute(type,course,date, venue, duration);

        String[] test = {date, course, venue, duration};

        ListAdapter adapter = new CustomAdapter(this, test);
        ListView list = (ListView)findViewById(R.id.lv);
        list.setAdapter(adapter);
    }
}
