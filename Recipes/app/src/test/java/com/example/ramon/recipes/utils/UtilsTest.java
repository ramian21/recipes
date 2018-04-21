package com.example.ramon.recipes.utils;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

//    @Test
//    public void regexWorks() throws Exception {
//        boolean test = Utils.checkAgainstUnitPreference("350°F", 0);
//        assertEquals(true, test);
//    }

    @Test
    public void addition_isCorrect() throws Exception {
        String s = "";
        for (Measurement m : Measurement.values()) {
            s += m.toString();
        }
        assertSame("no", s);
    }
}