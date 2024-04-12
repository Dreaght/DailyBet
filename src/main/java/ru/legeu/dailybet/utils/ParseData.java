package ru.legeu.dailybet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ParseData {
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
        String time = new SimpleDateFormat("hh:mm").format(new Date());
        return getDateTimeFromString(time);
    }

    public static Date getDateTimeFromString(String time) throws ParseException {
        String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        return getDateTimeFromString(time, date);
    }

    public static Date getDateTimeFromString(String time, String date) throws ParseException {
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("hh:mm dd.MM.yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

        return timeDateFormat.parse(timeFormat.format(time) + dateFormat.format(date));
    }

    public static Date getTimezonedDate(Date date, String timeZoneStr) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneStr));
        String formattedDate = sdf.format(date);

        try {
            return sdf.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
