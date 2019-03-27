package com.example.projectgamma;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Test;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class HomeActivityTest {

    @Test
    public void onCreate() {
        HomeActivity activity=new HomeActivity();

        final EditText nameEditText = (EditText) activity.findViewById(R.id.name);

        // Send string input value
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        String actualText=nameEditText.getText().toString();
        assertEquals("Jake",actualText);
    }

    @Test
    public void onBackPressed() {
        LoginActivity theActivity= new LoginActivity();

        if(theActivity.UsernameEt.getText().toString().length()==0){
            assertNull(theActivity.UsernameEt);
        }else{
            assertNotNull(theActivity.UsernameEt);
        }

        if(theActivity.PasswordEt.getText().toString().length()==0){
            assertNull(theActivity.PasswordEt);
        }else{
            assertNotNull(theActivity.PasswordEt);
        }
    }

    @Test
    public void onOptionsItemSelected() {
        LoginActivity theActivity= new LoginActivity();

        if(theActivity.UsernameEt.getText().toString().length()==0){
            assertNull(theActivity.UsernameEt);
        }else{
            assertNotNull(theActivity.UsernameEt);
        }

        if(theActivity.PasswordEt.getText().toString().length()==0){
            assertNull(theActivity.PasswordEt);
        }else{
            assertNotNull(theActivity.PasswordEt);
        }
    }

    @Test
    public void onNavigationItemSelected() {
        HomeActivity homeActivity=new HomeActivity();

        Button button=(Button) homeActivity.findViewById(R.id.MyCourses);
        Button button1=(Button) homeActivity.findViewById(R.id.MySchedule);
        Button button2=(Button) homeActivity.findViewById(R.id.Claim);
        Button button3=(Button) homeActivity.findViewById(R.id.settings);
        Button button4=(Button) homeActivity.findViewById(R.id.Logout);

        assertNotNull(button);
        assertNotNull(button1);
        assertNotNull(button2);
        assertNotNull(button3);
        assertNotNull(button4);


    }
}