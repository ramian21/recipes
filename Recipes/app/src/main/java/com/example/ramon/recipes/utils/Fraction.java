// TODO: write Fraction methods as needed

package com.example.ramon.recipes.utils;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private void simplify() {

    }

    public static Fraction parseFraction(String fractionString) {
        String[] numbers = fractionString.split("/");
        int num = Integer.parseInt(numbers[0]);
        int den = Integer.parseInt(numbers[1]);
        Fraction fraction = new Fraction(num, den);
        return fraction;
    }

    public String createString() {
        String s = numerator + "/" + denominator;
        return s;
    }

    public static int createFraction(double decimal) {
        long den = Math.round(1 / decimal);

        return 1;
    }

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

