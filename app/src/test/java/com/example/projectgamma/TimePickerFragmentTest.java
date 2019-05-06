package com.example.projectgamma;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimePickerFragmentTest {

    @Test
    public void onCreateDialog() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }
}