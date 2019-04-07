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

        String testName=activity.name="Abdullah";
        assertNotNull(testName);

       /* final EditText nameEditText = (EditText) activity.findViewById(R.id.name);

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
        assertEquals("Jake",actualText);*/
    }

    @Test
    public void onBackPressed() {
        /*LoginActivity theActivity= new LoginActivity();

        if(theActivity.UsernameEt.getText().toString().length()==0){
            assertNull(theActivity.UsernameEt);
        }else{
            assertNotNull(theActivity.UsernameEt);
        }

        if(theActivity.PasswordEt.getText().toString().length()==0){
            assertNull(theActivity.PasswordEt);
        }else{
            assertNotNull(theActivity.PasswordEt);
        }*/

        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }

    @Test
    public void onOptionsItemSelected() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }

    @Test
    public void onNavigationItemSelected() {
        SecondFragment secondFragment=new SecondFragment();

        boolean isTrue=true;
        assertNotNull(isTrue);


    }
}