    package com.example.projectgamma;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


    public class MyList extends AppCompatActivity {
        ListView listviewmain;
        String[] names = {"Amy", "John"};
        InputStream is = null;
        String line = null;
        String result = null;
        String temp = "";
        String[] arr;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tutorlist);
           // populateListView();
            registerClickCallBack();
            listviewmain = (ListView) findViewById(R.id.listviewmain);
            Intent intent = getIntent();
            int grade=Integer.parseInt(intent.getStringExtra("GRADE"));
            ArrayAdapter<String>adapter=new ArrayAdapter<String>(MyList.this, android.R.layout.simple_list_item_1, names);
            listviewmain.setAdapter(adapter);
            listviewmain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
switch (position){
    case 0:
        Intent HomeIntent0 = new Intent(MyList.this, BookActivity.class);
        startActivity(HomeIntent0);
        finish();
        break;
    case 1:
        Intent HomeIntent1 = new Intent(MyList.this, BookActivity.class);
        startActivity(HomeIntent1);
        finish();
        break;
        case 2:
        Intent HomeIntent2 = new Intent(MyList.this, BookActivity.class);
        startActivity(HomeIntent2);
        finish();
        break;
        case 3:
        Intent HomeIntent3 = new Intent(MyList.this, BookActivity.class);
        startActivity(HomeIntent3);
        finish();
        break;
        case 4:
        Intent HomeIntent4 = new Intent(MyList.this, BookActivity.class);
        startActivity(HomeIntent4);
        finish();
        break;
}

                }
            });
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://lamp.ms.wits.ac.za/~s1601745/MyTutors.php");
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            } catch (Exception e) {
                System.out.println("Error");
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                    sb.append(line + "\n");
                result = sb.toString();
                is.close();
                System.out.print("Here is the data");
                System.out.print(result);

            } catch (Exception e) {
                System.out.print("Error 2");
            }
            try {
                JSONArray jArray = new JSONArray(result);
                int count = jArray.length();
                for (int i = 0; i < count; i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    temp += json_data.getString("USERNAME") + ":";
                }
                arr = temp.split(":");
                listviewmain.setAdapter(new ArrayAdapter<String>(MyList.this, android.R.layout.simple_list_item_1, arr));
            } catch (Exception e) {
                System.out.print("Error");
            }

        }


        private void registerClickCallBack() {
            ListView list = (ListView) findViewById(R.id.listviewmain);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> paret, View viewClicked, int position, long id) {

                    /*if (position == 0) {
                        Intent HomeIntent = new Intent(com.example.projectgamma.MyList.this, LoginActivity.class);
                        startActivity(HomeIntent);
                        finish();
                    }
                    */

                }

            });


        }

        /*private void populateListView() {
            String[] Grades = {"Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.tutoritems, Grades);
            ListView list = (ListView) findViewById(R.id.listviewmain);
            list.setAdapter(adapter);
        }
        */
    }








