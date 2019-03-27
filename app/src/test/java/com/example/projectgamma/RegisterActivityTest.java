package com.example.projectgamma;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterActivityTest {

    @Test
    public void onCreate() {
        RegisterActivity registerActivity=new RegisterActivity();
        Button button=(Button) registerActivity.findViewById(R.id.test);
        assertNotNull(button);

        Button button1=(Button) registerActivity.findViewById(R.id.showAlertID);
        assertNotNull(button1);

        TextView textView=(TextView) registerActivity.findViewById(R.id.Course_err);
        String actualText=textView.getText().toString();
        assertEquals("Please select the courses that you would like to tutor.          (Max 5)",actualText);

    }

    @Test
    public void onItemSelected() {
        LoginActivity theActivity= new LoginActivity();

        if(theActivity.UsernameEt.getText().toString().length()==0){
            assertNull(theActivity.UsernameEt);
        }else{
            assertNotNull(theActivity.UsernameEt);
        }

        if(theActivity.PasswordEt.getText().toString().length()==0){
            assertNull(theActivity.PasswordEt);
        }else {
            assertNotNull(theActivity.PasswordEt);
        }
    }

    @Test
    public void onNothingSelected() {
        LoginActivity theActivity= new LoginActivity();

        if(theActivity.UsernameEt.getText().toString().length()==0){
            assertNull(theActivity.UsernameEt);
        }else{
            assertNotNull(theActivity.UsernameEt);
        }

        if(theActivity.PasswordEt.getText().toString().length()==0){
            assertNull(theActivity.PasswordEt);
        }else {
            assertNotNull(theActivity.PasswordEt);
        }
    }

    @Test
    public void register() {
        RegisterActivity registerActivity=new RegisterActivity();
        boolean isTrue=registerActivity.validate();

        assertNotNull(isTrue);
    }

    @Test
    public void onSignupSuccess() {
        RegisterActivity registerActivity=new RegisterActivity();

        TextView textView=(TextView) registerActivity.findViewById(R.id.name_label);
        TextView textView1=(TextView) registerActivity.findViewById(R.id.email_label);
        TextView textView2=(TextView) registerActivity.findViewById(R.id.SN_label);
        TextView textView3=(TextView) registerActivity.findViewById(R.id.password_label);
        TextView textView4=(TextView) registerActivity.findViewById(R.id.cpassword_label);

        String actualText=textView.getText().toString();
        String actualText1=textView1.getText().toString();
        String actualText2=textView2.getText().toString();
        String actualText3=textView3.getText().toString();
        String actualText4=textView4.getText().toString();

        assertEquals("Name",actualText);
        assertEquals("Email",actualText1);
        assertEquals("Student number",actualText2);
        assertEquals("Password",actualText3);
        assertEquals("Confirm Password",actualText4);
    }

    @Test
    public void validate() {
        RegisterActivity registerActivity=new RegisterActivity();
        boolean isTrue=registerActivity.validate();

        assertNotNull(isTrue);
    }

    @Test
    public void initialize() {
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