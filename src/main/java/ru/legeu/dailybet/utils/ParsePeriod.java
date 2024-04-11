package ru.legeu.dailybet.utils;

public class ParsePeriod {
    public static long getPeriodFromString(String periodStr) {
        long period;
        char unit = periodStr.charAt(periodStr.length() - 1);

        switch (unit) {
            case 'h' -> period = getPeriodFromString();
            case 'm' -> period = getDateTimeFromString(args[0], args[1]);
            default -> period = 0;
        }
        return period;
    }

    public long parseLong(String periodStr) {
        return Long.parseLong(periodStr.length() - 1)
    }

    public long getSecondsOfHours(long hours) {

    }
}
