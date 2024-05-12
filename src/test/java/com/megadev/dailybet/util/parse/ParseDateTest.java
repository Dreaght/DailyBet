package com.megadev.dailybet.util.parse;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

class ParseDateTest {

    @Test
    void getTimezonedDate() {
        String timeZoneStr = "GMT+3";

        Date timezonedDate = ParseDate.getTimezonedDate(new Date(), timeZoneStr);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        String formattedDate = sdf.format(timezonedDate);

        System.out.println(formattedDate);

    }

    @Test
    void getDateTimeFromString() throws ParseException {
        String time = "07:27:12";
        String date = "12.04.2024";

        Date dateTime = ParseDate.getDateTimeFromString(time, date);
        System.out.println("Parsed date and time: " + dateTime);
    }

    @Test
    void difference() {
        Date date1 = new Date();
        Date date2 = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(date2);
        c.add(Calendar.DATE, 1);
        date2 = c.getTime();

        long diff = ParseDate.difference(date1, date2);
        System.out.println(diff);
    }

    @Test
    void getDateTimeFromStringWithTime() throws ParseException {
        String time = "07:27:12";

        Date dateTime = ParseDate.getDateTimeFromString(time);
        System.out.println("Parsed date and time: " + dateTime);
    }

    @Test
    void getDateTimeWithoutArgs() throws ParseException {
        Date dateTime = ParseDate.getDateTimeFromString();
        System.out.println("Parsed date and time: " + dateTime);
    }
}
