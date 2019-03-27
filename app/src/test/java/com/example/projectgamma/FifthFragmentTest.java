package com.example.projectgamma;

import android.widget.Button;

import org.junit.Test;

import static org.junit.Assert.*;

public class FifthFragmentTest {

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
    public void onOptionsItemSelected() {
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
        FifthFragment fifthFragment=new FifthFragment();

        Button button=(Button) fifthFragment.findViewById(R.id.MyCourses);
        Button button1=(Button) fifthFragment.findViewById(R.id.MySchedule);
        Button button2=(Button) fifthFragment.findViewById(R.id.Claim);
        Button button3=(Button) fifthFragment.findViewById(R.id.settings);
        Button button4=(Button) fifthFragment.findViewById(R.id.Logout);

        assertNotNull(button);
        assertNotNull(button1);
        assertNotNull(button2);
        assertNotNull(button3);
        assertNotNull(button4);
    }
}