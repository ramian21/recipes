package com.example.ramon.recipes.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void regexWorks() throws Exception {
        boolean test = Utils.checkAgainstUnitPreference("350°F", 0);
        assertEquals(true, test);
    }
}