package com.megadev.dailybet.util.parse;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

class ParseDataTest {

    @Test
    void getTimezonedDate() {
        String timeZoneStr = "GMT+3";
        Date timezonedDate = ParseData.getTimezonedDate(new Date(), timeZoneStr);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        String formattedDate = sdf.format(timezonedDate);

        System.out.println(formattedDate);
    }

    @Test
    void getDateTimeFromString() throws ParseException {
        String time = "07:27:12";
        String date = "12.04.2024";

        Date dateTime = ParseData.getDateTimeFromString(time, date);
        System.out.println("Parsed date and time: " + dateTime);
    }

    @Test
    void difference() {
        Date date1 = new Date();
        Date date2 = new Date();

        long diff = ParseData.difference(date1, date2);
        assert diff == 0;
    }
}
