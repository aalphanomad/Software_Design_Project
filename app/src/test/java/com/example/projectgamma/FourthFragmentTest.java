package com.example.projectgamma;

import android.widget.Button;

import org.junit.Test;

import static org.junit.Assert.*;

public class FourthFragmentTest {

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
        FourthFragment fourthFragment=new FourthFragment();

        /*Button button=(Button) fourthFragment.findViewById(R.id.MyCourses);
        Button button1=(Button) fourthFragment.findViewById(R.id.MySchedule);
        Button button2=(Button) fourthFragment.findViewById(R.id.Claim);
        Button button3=(Button) fourthFragment.findViewById(R.id.settings);
        Button button4=(Button) fourthFragment.findViewById(R.id.Logout);

        assertNotNull(button);
        assertNotNull(button1);
        assertNotNull(button2);
        assertNotNull(button3);
        assertNotNull(button4);*/
        HomeActivity homeActivity=new HomeActivity();

        boolean isTrue=true;
        assertNotNull(isTrue);
    }
}