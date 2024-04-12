package ru.legeu.dailybet.utils.parse;

public class ParsePeriod {
    public static long getPeriodFromString(String periodStr) {
        long period;
        char unit = periodStr.charAt(periodStr.length() - 1);

        switch (unit) {
            case 'h' -> period = getMillsFromHours(parseLong(periodStr));
            case 'm' -> period = getMillsFromMinutes(parseLong(periodStr));
            case 's' -> period = getMillsFromSeconds(parseLong(periodStr));
            default -> period = 0;
        }
        return period;
    }

    public static long parseLong(String periodStr) {
        return Long.parseLong(periodStr.substring(0, periodStr.length() - 1));
    }

    public static long getMillsFromHours(long hours) {
        return hours * 60 * 60 * 1000;
    }

    public static long getMillsFromMinutes(long minutes) {
        return minutes * 60 * 1000;
    }

    public static long getMillsFromSeconds(long seconds) {
        return seconds * 1000;
    }
}
