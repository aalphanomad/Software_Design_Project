package com.example.projectgamma;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

//SORT UPLOADNIG FROM HAMZA
    private EditText et_name, et_SN, et_password, et_cpassword;
    private String name,email, Stu_Num, password, cpassword;
    TextView wind;
    Button regbtn;
    AlertDialog myDialog;
    private Spinner mySpinner;
    private List<String>courses;
    String item;
    TextView Course1,Course2,Course3,Course4,Course5;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Button showBtn = findViewById(R.id.showAlertID);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
        et_name = findViewById(R.id.name);
        et_SN = findViewById(R.id.SN_TB);
        et_password = findViewById(R.id.password);
        et_cpassword = findViewById(R.id.cpassword);
        regbtn = findViewById(R.id.test);
        wind = findViewById(R.id.AG_email);
        Course1 = findViewById(R.id.Course1);
        Course2 = findViewById(R.id.Course2);
        Course3 = findViewById(R.id.Course3);
        Course4 = findViewById(R.id.Course4);
        Course5 = findViewById(R.id.Course5);
        mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(this);
        courses = new ArrayList<String>();
        courses.add("COMS");
        courses.add("CAM");
        courses.add("STAT");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(dataAdapter);


        //CHECK HOW TO CONTINUOUSLY REFRESH!!!!
       // wind.setText(Stu_Num.toString() + "@students.wits.ac.za");
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();//call when the buttonEditText is clicked
            }


        });
        Thread refresh = new Thread() {
            @Override
            public void run(){
                while(!isInterrupted()){
                    try {
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                count++;
                                Stu_Num = et_SN.getText().toString().trim();

                                if(Stu_Num.length()!=0) {
                                    wind.setText(Stu_Num.toString() + "@students.wits.ac.za");
                                }
                                else{
                                    wind.setText(" "+"@students.wits.ac.za");
                                }

                                }

                        });
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        refresh.start();
    }



    @Override
    public void onItemSelected(AdapterView<?>parent,View view,int position,long id){
         item=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }

    private void showAlert() {
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
         CharSequence [] The_courses = new CharSequence[100];
         CharSequence[] COMS_Courses = {"COMS1018(IAP)", "COMS1019(BCO)", "COMS2020(MC)", "COMS2021(DBF)","COMS2022"};
        CharSequence[] CAM_Courses={"CAM1018","CAM1019","CAM2020","CAM2021","CAM2022"};
        if(item=="COMS"){
            The_courses=COMS_Courses;
        }
        else if(item=="CAM"){
            The_courses=CAM_Courses;
        }

        final ArrayList selectedItems = new ArrayList();
        final CharSequence[] finalThe_courses = The_courses;
        myBuilder.setTitle("Proposed Courses").setMultiChoiceItems(The_courses, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if (isChecked == true) {
                    selectedItems.add(finalThe_courses[position]);
                } else if (selectedItems.contains(position)) {
                    selectedItems.remove(Integer.valueOf(position));
                }
                if(selectedItems.size()>=1) {
                    Course1.setText(selectedItems.get(0).toString());
                }
                if(selectedItems.size()>=2) {
                    Course2.setText(selectedItems.get(1).toString());
                }
                if(selectedItems.size()>=3) {
                    Course3.setText(selectedItems.get(2).toString());
                }
                if(selectedItems.size()>=4) {
                    Course4.setText(selectedItems.get(3).toString());
                }
                if(selectedItems.size()>=5) {
                    Course5.setText(selectedItems.get(4).toString());
                }
            }
        });
        myBuilder.setPositiveButton("Selected Items", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();
                for (Object i : selectedItems) {
                    sb.append(i.toString() + "\n");
                }
            }
        });
        myDialog = myBuilder.create();
        myDialog.show();
    }


    public void register() {
        initialize();//initialize the input to string variables
        if (validate() == false) {
            Toast.makeText(this, "Signup has failed", Toast.LENGTH_SHORT).show();
        } else {
           // onSignupSuccess();
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void onSignupSuccess() {
//Check correspondence in database and allow entry
        InputStream is = null;
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("USERNAME", name));
        nameValuePairs.add(new BasicNameValuePair("PHONE", Stu_Num));
        nameValuePairs.add(new BasicNameValuePair("EMAIL", email));
        nameValuePairs.add(new BasicNameValuePair("PASSWORD", password));


        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://lamp.ms.wits.ac.za/~s1601745/insert_student.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (ClientProtocolException e) {
            System.out.print("Error!");
        } catch (IOException e) {
            System.out.print("CHECK");
        }

        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public boolean validate() {
        boolean valid = true;
        if (name.isEmpty() || name.length() > 32) {
            et_name.setError("Please enter a valid name");
            valid = false;
        }

        if (password.isEmpty()) {
            et_password.setError("Please enter a valid password");
            valid = false;
        }
        if (cpassword.isEmpty()) {
            et_cpassword.setError("Please enter a valid password");
            valid = false;
        }
        if (Stu_Num.isEmpty() || Stu_Num.length() > 10) {
            et_SN.setError("Please enter your Student Number");
            valid = false;
        }
        if (cpassword.isEmpty()) {
            et_cpassword.setError("Please confirm your password");
        }
        if (!password.equals(cpassword)) {
            et_cpassword.setError("Passwords do not correspond");
            valid = false;
        }

        return valid;
    }

    public void initialize() {
        name = et_name.getText().toString().trim();
        Stu_Num = et_SN.getText().toString().trim();
        password = et_password.getText().toString().trim();
        cpassword = et_cpassword.getText().toString().trim();
    }
}


