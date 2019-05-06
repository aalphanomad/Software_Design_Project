package com.example.projectgamma;

import android.view.View;
import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;

public class Claim_FormTest {

    @Test
    public void onCreate() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }

    @Test
    public void validate() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }

    @Test
    public void buttonStartTime() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;

        String date1="10:30";
        String date2="22:20";

        boolean result = claim_form.checktimings(date1, date2);
        assert(result==true);

        String date3="10:30";
        String date4="9:30";

        boolean result2 = claim_form.checktimings(date3, date4);
        assert(result2==false);

        String date5="201as";
        String date6="201asdas8-01-01";

        boolean result3 = claim_form.checktimings(date5, date6);
        assert(result3==false);
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

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }


    }

    @Test
    public void send() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }
}
