package com.example.projectgamma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Selecting which xml res file is called when calling the Claim_form class
        setContentView(R.layout.aboutus);
        Button back=(Button) findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(AboutUs.this, LoginActivity.class);
                AboutUs.this.startActivity(i);
            }
        });
    }
}
