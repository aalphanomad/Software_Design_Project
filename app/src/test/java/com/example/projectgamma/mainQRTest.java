package com.example.projectgamma;

import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;

public class mainQRTest {

    @Test
    public void onCreate() {
        mainQR mainQR=new mainQR();
        TextView textView=(TextView) mainQR.findViewById(R.id.Greeting);
        String actualText=textView.getText().toString();
        assertEquals("Welcome Back,",actualText);
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
    public void scanIn() {
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
}