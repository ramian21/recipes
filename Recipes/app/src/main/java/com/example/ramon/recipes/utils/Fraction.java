// TODO: write Fraction methods as needed

package com.example.ramon.recipes.utils;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    public Fraction(double decimal) {
        String asString = "" + decimal;
        int places = asString.length() - asString.indexOf('.') - 1;
        int zeroes = (int) Math.pow(10, places);
        numerator = (int) (decimal * zeroes);
        denominator = zeroes;
        simplify();
    }

    private void simplify() {
        int gcm = gcm(numerator, denominator);
        numerator /= gcm;
        denominator /= gcm;
    }

    private int gcm(int a, int b) {
        return b == 0 ? a : gcm(b, a % b);
    }

    public static Fraction parseFraction(String fractionString) {
        String[] numbers = fractionString.split("/");
        int num = Integer.parseInt(numbers[0]);
        int den = Integer.parseInt(numbers[1]);
        Fraction fraction = new Fraction(num, den);
        return fraction;
    }

    public String toString() {
        if (denominator == 1) {
            return "" + numerator;
        }
        String s = numerator + "/" + denominator;
        return s;
    }

    public double getValue() {
        double value = ((double) numerator / denominator);
        return value;
    }

//    public static int createFraction(double decimal) {
//        long den = Math.round(1 / decimal);
//
//        return 1;
//    }

    @Override
    public boolean equals(Object obj) {
        Fraction compareFraction = (Fraction) obj;
        if (this.numerator == compareFraction.numerator &&
                this.denominator == compareFraction.denominator) {
            return true;
        } else {
            return false;
        }
    }
}

