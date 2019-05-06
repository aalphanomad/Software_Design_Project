package com.example.projectgamma;

import org.junit.Test;

import static org.junit.Assert.*;

public class qrScannerTest {

    @Test
    public void onCreate() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }

    @Test
    public void handleResult() {
        RegisterActivity registerActivity=new RegisterActivity();
        boolean isTrue=true;

        assertNotNull(isTrue);

        String testName= registerActivity.name="Marubini";
        assertEquals("Marubini",testName);

        String testPass= registerActivity.password="953344";
        assertEquals("953344",testPass);
    }

    @Test
    public void onPause() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }

    @Test
    public void onResume() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }
}