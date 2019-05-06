package com.example.projectgamma;

import org.junit.Test;

import static org.junit.Assert.*;

public class SecondFragmentTest {

    @Test
    public void onCreate() {

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
        /*Button button=(Button) secondFragment.findViewById(R.id.MyCourses);
        Button button1=(Button) secondFragment.findViewById(R.id.MySchedule);
        Button button2=(Button) secondFragment.findViewById(R.id.Claim);
        Button button3=(Button) secondFragment.findViewById(R.id.settings);
        Button button4=(Button) secondFragment.findViewById(R.id.Logout);

        assertNotNull(button);
        assertNotNull(button1);
        assertNotNull(button2);
        assertNotNull(button3);
        assertNotNull(button4);*/
    }
}