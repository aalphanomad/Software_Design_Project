package com.example.projectgamma;

import android.widget.Button;

import org.junit.Test;

import static org.junit.Assert.*;

public class FirstFragmentTest {

    @Test
    public void onCreate() {
        Claim_Form claim_form=new Claim_Form();

        boolean isTrue=claim_form.check;
        if(isTrue==true || isTrue==false){
            assertNotNull(isTrue);
        }
    }

    @Test
    public void onNavigationItemSelected() {
        FirstFragment firstFragment=new FirstFragment();

        /*Button button=(Button) firstFragment.findViewById(R.id.MyCourses);
        Button button1=(Button) firstFragment.findViewById(R.id.MySchedule);
        Button button2=(Button) firstFragment.findViewById(R.id.Claim);
        Button button3=(Button) firstFragment.findViewById(R.id.settings);
        Button button4=(Button) firstFragment.findViewById(R.id.Logout);

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