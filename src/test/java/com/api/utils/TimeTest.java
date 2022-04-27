package com.api.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {
    
    @Test
    void differenceTimeBetweenTest(){
        String time1 = "1:53:13,42";
        String time2 = "1:54:14,53";

        String difference = Time.differenceBetween(time1, time2);

        assertEquals("61,11s", difference);
    }

    @Test
    void differenceTimeBetweenLessThan1MinTest(){
        String time1 = "1:53:13,42";
        String time2 = "1:54:05,53";

        String difference = Time.differenceBetween(time1, time2);

        assertEquals("52,11s", difference);
    }

    @Test
    void differenceTimeBetweenWithSameHourAndMinTest(){
        String time1 = "1:54:05,42";
        String time2 = "1:54:13,53";

        String difference = Time.differenceBetween(time1, time2);

        assertEquals("8,11s", difference);
    }

    @Test
    void differenceTimeBetweenWithMore1HourTest(){
        String time1 = "1:53:05,42";
        String time2 = "2:54:13,53";

        String difference = Time.differenceBetween(time1, time2);

        assertEquals("3668,11s", difference);
    }

}
