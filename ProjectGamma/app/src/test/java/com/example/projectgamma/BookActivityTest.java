package com.example.projectgamma;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookActivityTest {

    /*

    @Test
    public void book() {
        String duration, subject, add1, add2,date;
        duration = "1hrs:00mins";
        subject = "COMS1016";
        date = "10 Mar 2019";
        add1 = "msl006";
        add2 = "";
        InputStream is = null;
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        //nameValuePairs.add(new BasicNameValuePair("STUDENT_ID", name));
        //nameValuePairs.add(new BasicNameValuePair("TUTOR_ID", name));
        nameValuePairs.add(new BasicNameValuePair("TIME", time));
        nameValuePairs.add(new BasicNameValuePair("DATE", date));
        nameValuePairs.add(new BasicNameValuePair("DURATION", duration));
        nameValuePairs.add(new BasicNameValuePair("SUBJECT", subject));
        nameValuePairs.add(new BasicNameValuePair("ADD_1", add1));
        nameValuePairs.add(new BasicNameValuePair("ADD_2", add2));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://lamp.ms.wits.ac.za/~s1601745/arrangement.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (ClientProtocolException e) {
            System.out.print("Error!");
        } catch (IOException e) {
            System.out.print("CHECK");
        }

    }
         */


    @Test
    public void validate() {
        String duration, subject, add1, add2;
        duration = "1h30";
        subject = "COMS1016";
        add1 = "";
        add2 = "";

        assertFalse("not failing empty fields",validate_help(duration,subject,add1,add2));

        add1 = "sadsa";
        add2 = "asdasd";

        assertTrue("failing valid inputs",validate_help(duration,subject,add1,add2));
    }

    boolean validate_help(String duration,String subject,String add1,String add2) {

        boolean valid = true;
        if (duration.isEmpty() || duration.length() > 32) {
            valid = false;
        }

        if (subject.isEmpty()) {
            valid = false;
        }
        if (add1.isEmpty()) {
            valid = false;
        }
        if (add2.isEmpty()) {
            valid = false;
        }
        return valid;
    }



    /*

    @Test
    public void onProcess() {
        String date,time,duration,subject,add1,add2;
        /*
        date = ;
        time = ;
        duration = ;
        subject = ;
        add1 = ;
        add2 = ;
        InputStream is = null;
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        //nameValuePairs.add(new BasicNameValuePair("STUDENT_ID", name));
        //nameValuePairs.add(new BasicNameValuePair("TUTOR_ID", name));
        nameValuePairs.add(new BasicNameValuePair("TIME", time));
        nameValuePairs.add(new BasicNameValuePair("DATE", date));
        nameValuePairs.add(new BasicNameValuePair("DURATION", duration));
        nameValuePairs.add(new BasicNameValuePair("SUBJECT", subject));
        nameValuePairs.add(new BasicNameValuePair("ADD_1", add1));
        nameValuePairs.add(new BasicNameValuePair("ADD_2", add2));
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://lamp.ms.wits.ac.za/~s1601745/arrangement.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is=entity.getContent();
        } catch (ClientProtocolException e) {
            System.out.print("Error!");
        } catch (IOException e) {
            System.out.print("CHECK");
        }
    }
        */

                }