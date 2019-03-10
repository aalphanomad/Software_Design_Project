package com.example.projectgamma;

import org.junit.Test;

import static org.junit.Assert.*;

public class Claim_FormTest {

    @Test
    public void onCreate() {
    }

    @Test
    public void increase() {
        int hour = 0;
        int min1 = 0;
        int min2 = 0;

        min1 += 3;

        if (min1 == 6) {
            min1 = 0;
            hour += 1;
        }

        String out = (hour + " hrs : " + min1 + min2 + " min");

        assertEquals("time string is formatted incorrectly","0 hrs : 30 min",out);
    }

    @Test
    public void decrease() {
        int hour = 1;
        int min1 = 0;
        int min2 = 0;

        int prevmin1 = min1;
        min1 -= 3;

        if (hour > 0 && min1 - 3 == 0) {
            min1 = 0;
        } else if (hour > 0 && prevmin1 == 0) {
            hour -= 1;
            min1 = 3;
        }

        if (min1 - 3 < 0) {
            min1 = 0;
        }


        String out = (hour + " hrs : " + min1 + min2 + " min");

        assertEquals("time string is formatted incorrectly","0 hrs : 30 min",out);
    }

    @Test
    public void send() {
    }
}