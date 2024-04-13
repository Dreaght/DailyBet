package com.megadev.dailybet.utils.parse;

public class ParsePlaceholder {
    public static String parseWithBraces(String configString, String[] replacements, Object[] values) {
        String parsedString = configString;
        for (int i = 0; i < replacements.length; i++) {
            parsedString = parsedString.replace("%" + replacements[i] + "%", String.valueOf(values[i]));
        }
        return parsedString;
    }
}
