package com.example.projectgamma;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static org.junit.Assert.*;

public class BackgroundWorkerTest {

    @Test
    public void doInBackground() {
        // login
        try {
            String stu_num="1";
            System.out.println("test!"+stu_num);
            String password="test";
            String login_url = "http://lamp.ms.wits.ac.za/~s1601745/signin.php";
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("studentnum", "UTF-8") + "=" + URLEncoder.encode(stu_num, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            assertEquals("{\"result\":\"1\",\"name\":\"admin\",\"student_num\":\"1\",\"admin\":\"0\"}", result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            fail("Malformed URL Exception");
        }  catch (IOException e) {
            e.printStackTrace();
            fail("IOException");
        }


    }
}