package com.example.projectgamma;

import android.widget.EditText;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    @Test
    public void onCreate() {
        LoginActivity theActivity= new LoginActivity();
        //EditText editText= theActivity.UsernameEt;
        //String test = editText.getText().toString();

        assertNull(theActivity.UsernameEt);
        assertNull(theActivity.PasswordEt);

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
    public void onClick() {
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