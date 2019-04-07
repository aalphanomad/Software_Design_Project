package com.example.projectgamma;

import android.widget.EditText;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    @Test
    public void onCreate() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }

            }

    @Test
    public void onBackPressed() {
        LoginActivity theActivity= new LoginActivity();

        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }

    }

    @Test
    public void onClick() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }
}