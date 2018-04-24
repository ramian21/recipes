package com.example.ramon.recipes.utils;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void regexWorks() throws Exception {

        String[] test = Utils.checkAgainstUnitPreference("350°F", 0);
        assertEquals("F", test[1]);

    }

//    @Test
//    public void addition_isCorrect() throws Exception {
//        String s = "";
//        for (Measurement m : Measurement.values()) {
//            s += m.toString();
//        }
//        assertSame("no", s);
//    }

    @Test
    public void measurement_found() throws Exception {
        String input = "celsius";
        assertEquals(Measurement.Temperature.CELSIUS, Measurement.getMeasurement(input));
    }
}