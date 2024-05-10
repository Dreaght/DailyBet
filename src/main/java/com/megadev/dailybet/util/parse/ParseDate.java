package com.megadev.dailybet.util.parse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ParseDate {
    public static Date getDateTimeFromString(String[] args) throws ParseException {
        Date date;
        switch (args.length) {
            case 1 -> date = getDateTimeFromString(args[0]);
            case 2 -> date = getDateTimeFromString(args[0], args[1]);
            default -> date = getDateTimeFromString();
        }
        return date;
    }

    public static Date getDateTimeFromString() throws ParseException {
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return getDateTimeFromString(time);
    }

    public static Date getDateTimeFromString(String time) throws ParseException {
        String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        return getDateTimeFromString(time, date);
    }

    public static Date getDateTimeFromString(String time, String date) throws ParseException {
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        Date parsedTime = timeFormat.parse(time);
        Date parsedDate = dateFormat.parse(date);

        String combinedDateTimeString = timeFormat.format(parsedTime) + " " + dateFormat.format(parsedDate);
        return timeDateFormat.parse(combinedDateTimeString);
    }

    public static Date getTimezonedDate(Date date, String timeZoneStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        String formattedDate = sdf.format(date);

        try {
            return sdf.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long difference(Date date1, Date date2) {
        long milliseconds1 = date1.getTime();
        long milliseconds2 = date2.getTime();

        long differenceMillis = milliseconds2 - milliseconds1;
        return differenceMillis;
    }
}
