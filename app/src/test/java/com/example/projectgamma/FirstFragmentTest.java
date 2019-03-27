package com.example.projectgamma;

import android.widget.Button;

import org.junit.Test;

import static org.junit.Assert.*;

public class FirstFragmentTest {

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
    public void onNavigationItemSelected() {
        FirstFragment firstFragment=new FirstFragment();

        Button button=(Button) firstFragment.findViewById(R.id.MyCourses);
        Button button1=(Button) firstFragment.findViewById(R.id.MySchedule);
        Button button2=(Button) firstFragment.findViewById(R.id.Claim);
        Button button3=(Button) firstFragment.findViewById(R.id.settings);
        Button button4=(Button) firstFragment.findViewById(R.id.Logout);

        assertNotNull(button);
        assertNotNull(button1);
        assertNotNull(button2);
        assertNotNull(button3);
        assertNotNull(button4);
    }
}