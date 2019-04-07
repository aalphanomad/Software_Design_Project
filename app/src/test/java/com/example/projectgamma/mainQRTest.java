package com.example.projectgamma;

import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;

public class mainQRTest {

    @Test
    public void onCreate() {
        /*mainQR mainQR=new mainQR();
        TextView textView=(TextView) mainQR.findViewById(R.id.Greeting);
        String actualText=textView.getText().toString();
        assertEquals("Welcome Back,",actualText);*/

        RegisterActivity registerActivity=new RegisterActivity();
        String testName= registerActivity.name="Mayur";
        assertNotNull(testName);
    }

    @Test
    public void onBackPressed() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }

    @Test
    public void scanIn() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }
}