package com.example.ramon.recipes.utils;

public enum Measurement {
    IMPERIAL, METRIC, NEITHER;

    private static String[] namesList = {
            "teaspoon",
            "teaspoons",
            "tsp",
            "ts",
            "t",
            "tspn",

            "tablespoon",
            "tablespoons",
            "tbsp",
            "T",
            "tbls",
            "Tb",

            "fluid ounce",
            "fluid ounces",
            "fl oz",

            "cup",
            "cups",
            "c",
            "C",

            "pint",
            "pints",
            "pt",
            "pts",

            "quart",
            "quarts",
            "qt",
            "qts",

            "half gallon",
            "half gallons,",
            "half gal",
            "half gals",

            "gallon",
            "gallons",
            "gal",
            "gals",

            "milliliter",
            "milliliters",
            "mL",
            "ml",

            "liter",
            "liters",
            "L",

            "ounce",
            "ounces",
            "oz",

            "pound",
            "pounds",
            "lb",
            "lbs",

            "gram",
            "grams",
            "g",

            "kilogram",
            "kilograms",
            "kg",

            "Fahrenheit",
            "fahrenheit",
            "°F",
            "F",

            "Celsius",
            "celsius",
            "°C",
            "C",

            "second",
            "seconds",
            "sec",
            "secs",
            "s",

            "minute",
            "minutes",
            "min",
            "mins",
            "m",

            "hour",
            "hours",
            "hr",
            "hrs",
            "h"
    };


    public static Measurable getMeasurement(String givenUnit) {
        for (int k = 0; k < namesList.length; k++) {
            if (namesList[k].matches("^" + givenUnit)) {
                if (k < 6) return Liquid.TEASPOON;
                if (k < 12) return Liquid.TABLESPOON;
                if (k < 15) return Liquid.FLUID_OUNCE;
                if (k < 19) return Liquid.CUP;
                if (k < 23) return Liquid.PINT;
                if (k < 27) return Liquid.QUART;
                if (k < 31) return Liquid.HALF_GALLON;
                if (k < 35) return Liquid.GALLON;
                if (k < 39) return Liquid.MILLILITER;
                if (k < 42) return Liquid.LITER;
                if (k < 45) return Weight.OUNCE;
                if (k < 49) return Weight.POUND;
                if (k < 52) return Weight.GRAM;
                if (k < 55) return Weight.KILOGRAM;
                if (k < 59) return Temperature.FAHRENHEIT;
                if (k < 63) return Temperature.CELSIUS;
                if (k < 68) return Time.SECOND;
                if (k < 73) return Time.MINUTE;
                return Time.HOUR;
            }
        }
        return null;
    }


    public interface Measurable {
        Measurement getType();

        int getValue();

        String[] getNames();

        double convertToOtherUnit(double initialValue);

        Measurable getOtherBaseUnit();

    }

    public enum Liquid implements Measurable {

        /*****************************
         * List of liquid measurements
         ****************************/

        //Imperial
        TEASPOON(1, IMPERIAL, new String[]{
                "teaspoon",
                "teaspoons",
                "tsp",
                "ts",
                "t",
                "tspn"}),
        TABLESPOON(3, IMPERIAL, new String[]{
                "tablespoon",
                "tablespoons",
                "tbsp",
                "T",
                "tbls",
                "Tb"
        }),
        FLUID_OUNCE(6, IMPERIAL, new String[]{
                "fluid ounce",
                "fluid ounces",
                "fl oz"
        }),
        CUP(48, IMPERIAL, new String[]{
                "cup",
                "cups",
                "c",
                "C"
        }),
        PINT(96, IMPERIAL, new String[]{
                "pint",
                "pints",
                "pt",
                "pts"
        }),
        QUART(192, IMPERIAL, new String[]{
                "quart",
                "quarts",
                "qt",
                "qts",
        }),
        HALF_GALLON(384, IMPERIAL, new String[]{
                "half gallon",
                "half gallons,",
                "half gal",
                "half gals"
        }),
        GALLON(768, IMPERIAL, new String[]{
                "gallon",
                "gallons",
                "gal",
                "gals"
        }),

        //Metric
        MILLILITER(1, METRIC, new String[]{
                "milliliter",
                "milliliters",
                "mL",
                "ml"
        }),
        LITER(1000, METRIC, new String[]{
                "liter",
                "liters",
                "L"
        });

        private final int value;
        private final Measurement type;
        private final String[] names;
        private static final double TSP_TO_ML = 4.92892;
        private static final double ML_TO_TSP = 0.202884;

        Liquid(int value, Measurement type, String[] names) {
            this.value = value;
            this.type = type;
            this.names = names;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public double convertToOtherUnit(double initialValue) {
            if (type.equals(Measurement.IMPERIAL)) {
                return value * TSP_TO_ML * initialValue;
            } else {
                return value * ML_TO_TSP * initialValue;
            }
        }

        @Override
        public Measurable getOtherBaseUnit() {
            if (type.equals(Measurement.IMPERIAL)) {
                return MILLILITER;
            } else {
                return TEASPOON;
            }
        }

        @Override
        public Measurement getType() {
            return type;
        }

        @Override
        public String[] getNames() {
            return names;
        }


    }

    public enum Weight implements Measurable {

        /*****************************
         * List of Weight measurements
         ****************************/

        //Imperial
        OUNCE(1, IMPERIAL, new String[]{
                "ounce",
                "ounces",
                "oz"
        }),
        POUND(16, IMPERIAL, new String[]{
                "pound",
                "pounds",
                "lb",
                "lbs"
        }),

        //Metric
        GRAM(1, METRIC, new String[]{
                "gram",
                "grams",
                "g"
        }),
        KILOGRAM(1000, METRIC, new String[]{
                "kilogram",
                "kilograms",
                "kg"
        });

        private final int value;
        private final Measurement type;
        private final String[] names;
        private static final double G_TO_OZ = 0.035274;
        private static final double OZ_TO_G = 28.3495;

        Weight(int value, Measurement type, String[] names) {
            this.value = value;
            this.type = type;
            this.names = names;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public double convertToOtherUnit(double initialValue) {
            if (type.equals(Measurement.IMPERIAL)) {
                return initialValue * value * OZ_TO_G;
            } else {
                return initialValue * value * G_TO_OZ;
            }
        }

        @Override
        public Measurable getOtherBaseUnit() {
            if (type.equals(Measurement.IMPERIAL)) {
                return GRAM;
            } else {
                return OUNCE;
            }
        }


        @Override
        public Measurement getType() {
            return type;
        }

        @Override
        public String[] getNames() {
            return names;
        }


    }

    public enum Temperature implements Measurable {
        /**********************************
         * List of Temperature Measurements
         **********************************/

        //Imperial
        FAHRENHEIT(32, IMPERIAL, new String[]{
                "Fahrenheit",
                "fahrenheit",
                "°F",
                "F"
        }),

        //Metric
        CELSIUS(0, METRIC, new String[]{
                "Celsius",
                "celsius",
                "°C",
                "C"
        });

        private final int value;
        private final Measurement type;
        private final String[] names;
        private static final double F_TO_C_CONSTANT = 0.555555;
        private static final double C_TO_F_CONSTANT = 1.8;

        Temperature(int value, Measurement type, String[] names) {
            this.value = value;
            this.type = type;
            this.names = names;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public double convertToOtherUnit(double initialValue) {
            if (type.equals(Measurement.IMPERIAL)) {
                return (initialValue - 32) * F_TO_C_CONSTANT;
            } else {
                return initialValue * C_TO_F_CONSTANT + 32;
            }
        }

        @Override
        public Measurable getOtherBaseUnit() {
            if (type.equals(Measurement.IMPERIAL)) {
                return CELSIUS;
            } else {
                return FAHRENHEIT;
            }
        }

        @Override
        public Measurement getType() {
            return type;
        }

        @Override
        public String[] getNames() {
            return names;
        }

    }

    public enum Time implements Measurable {

        SECOND(1, NEITHER, new String[]{
                "second",
                "seconds",
                "sec",
                "secs",
                "s"
        }),

        MINUTE(60, NEITHER, new String[]{
                "minute",
                "minutes",
                "min",
                "mins",
                "m"
        }),

        HOUR(3600, NEITHER, new String[]{
                "hour",
                "hours",
                "hr",
                "hrs",
                "h"
        });

        private final int value;
        private final String[] names;
        private final Measurement type;

        Time(int value, Measurement type, String[] names) {
            this.value = value;
            this.type = type;
            this.names = names;
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        public double convertToOtherUnit(double initialValue) {
            return value;
        }

        @Override
        public Measurable getOtherBaseUnit() {
            return SECOND;
        }


        @Override
        public Measurement getType() {
            return type;
        }

        @Override
        public String[] getNames() {
            return names;
        }
    }
}