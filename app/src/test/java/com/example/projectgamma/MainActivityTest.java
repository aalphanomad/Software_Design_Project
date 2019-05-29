package com.example.projectgamma;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
    LoginActivity myactivity;
    adminViewCourses viewCourses;
    Claim_Form claim_form;
    Claim_History claim_history;
    editUserProfile userProfile;
    HomeActivity homeActivity;
    LecturerHome lecturerHome;
    MainActivity mainActivity;
    MyAdapter myAdapter;
    MySchedule mySchedule;
    MyTutors myTutors;
    qrGenerator generator;
    qrScanner scanner;
    RegisterActivity registerActivity;
    SecondFragment secondFragment;
    TimePickerFragment fragment;
    tutorProfile profile;
    upload_transcript transcript;
    Validate validate;
    ViewProfile viewProfile;

    @Before
    public void setUp() throws Exception {
        myactivity= Robolectric.buildActivity(LoginActivity.class).setup().get();
        viewCourses=Robolectric.buildActivity(adminViewCourses.class).setup().get();
        userProfile=Robolectric.buildActivity(editUserProfile.class).setup().get();
        claim_form=Robolectric.buildActivity(Claim_Form.class).setup().get();//Note-JsonException
        claim_history=Robolectric.buildActivity(Claim_History.class).setup().get();//Note-JsonException
        homeActivity=Robolectric.buildActivity(HomeActivity.class).setup().get();
        lecturerHome=Robolectric.buildActivity(LecturerHome.class).setup().get();
        mainActivity=Robolectric.buildActivity(MainActivity.class).setup().get();
        mySchedule=Robolectric.buildActivity(MySchedule.class).setup().get();
        myTutors=Robolectric.buildActivity(MyTutors.class).setup().get();
        generator=Robolectric.buildActivity(qrGenerator.class).setup().get();
        scanner=Robolectric.buildActivity(qrScanner.class).setup().get();
        registerActivity=Robolectric.buildActivity(RegisterActivity.class).setup().get();
        secondFragment=Robolectric.buildActivity(SecondFragment.class).setup().get();
        profile=Robolectric.buildActivity(tutorProfile.class).setup().get();
        transcript=Robolectric.buildActivity(upload_transcript.class).setup().get();
        validate=Robolectric.buildActivity(Validate.class).setup().get();
        viewProfile=Robolectric.buildActivity(ViewProfile.class).setup().get();

    }
    @Test
    public  void  check(){
        assertNotNull(myactivity);
        myactivity.PasswordEt.setText("test");
        myactivity.UsernameEt.setText("1");
        myactivity.findViewById(R.id.signin_but).performClick();

        assertNotNull(viewCourses);




    }

    @After
    public void tearDown() throws Exception {
    }
}