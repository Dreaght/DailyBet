package ru.legeu.dailybet.utils.parse;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

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
        String time = "07:27";
        String date = "12.04.2024";

        Date dateTime = ParseData.getDateTimeFromString(time, date);
        System.out.println("Parsed date and time: " + dateTime);
    }
}