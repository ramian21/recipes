package com.example.ramon.recipes.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class FractionTest {

    @Test
    public void parseFraction() {
        Fraction fraction = new Fraction(1, 2);
        Fraction testFraction = Fraction.parseFraction("1/2");
        assertEquals(fraction, testFraction);
    }

    @Test
    public void createString() {
        Fraction fraction = new Fraction(1, 2);
        String string = fraction.toString();
        assertEquals("1/2", string);
    }
}