package com.megadev.dailybet.utils.parse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParsePlaceholderTest {

    @Test
    void parseWithBraces() {
        String expected = "Value: 100";
        String actual = ParsePlaceholder.parseWithBraces("Value: %VALUE%",
                new String[]{"VALUE"},
                new Object[]{100});

        assertEquals(expected, actual);
    }
}