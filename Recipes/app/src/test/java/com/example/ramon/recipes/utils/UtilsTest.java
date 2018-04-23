package com.example.ramon.recipes.utils;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void regexWorks() throws Exception {
        boolean test = Utils.checkAgainstUnitPreference("1 tablespoon of sugar", 0);
        assertEquals(true, test);
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