package com.example.projectgamma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class mainQR extends AppCompatActivity{

    Button scan;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view);
        TextView dummy;
        Intent i = getIntent();
        String test = i.getStringExtra("name");
        dummy = findViewById(R.id.Name_TB);
        scan = findViewById(R.id.Scan_button);
        dummy.setText(test.toUpperCase().charAt(0) +test.substring(1,test.length())+"!");


    }
public void onBackPressed(){

}

    public void scanIn(View view) {
        Intent i = new Intent(view.getContext(), qrScanner.class);
        startActivity(i);
    }

    public void doneScan(mainQR view) {
        Intent i=new Intent(mainQR.this, qrScanner.class);
        this.startActivity(i);

    }
}