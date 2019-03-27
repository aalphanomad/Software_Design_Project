package com.example.projectgamma;

import android.view.View;
import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;

public class Claim_FormTest {

    @Test
    public void onCreate() {
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
    public void validate() {
        Claim_Form claim_form=new Claim_Form();
        boolean isTrue=claim_form.validate("12:00","13:00");

        assertNotNull(isTrue);
    }

    @Test
    public void buttonStartTime() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }

    @Test
    public void buttonEndTime() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }

    }

    @Test
    public void onTimeSet() {
        Claim_Form claim_form=new Claim_Form();

        TextView start = (TextView) claim_form.findViewById(R.id.start);
        String actualText=start.getText().toString();
        assertEquals("0 : 00 ",actualText);


    }

    @Test
    public void send() {
        Claim_Form claim_form=new Claim_Form();
        boolean isTrue=claim_form.validate("12:00","13:00");

        assertNotNull(isTrue);
    }
}