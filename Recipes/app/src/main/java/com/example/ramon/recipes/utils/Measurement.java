package com.example.ramon.recipes.utils;

public enum Measurement {
    IMPERIAL, METRIC;

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

            "Celsius",
            "celsius",
            "°C"
    };

    public static Measurable getMeasurement(String givenUnit) {
        for (int k = 0; k < namesList.length; k++) {
            if (givenUnit.matches(namesList[k])) {
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
                if (k < 58) return Temperature.FAHRENHEIT;
                return Temperature.CELSIUS;
            }
        }
        return null;
    }

    // COMPLETED: figure out how to return a list of relevant measurements

    public interface Measurable {
        public String[] getNamesList();
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

        Liquid(int value, Measurement type, String[] names) {
            this.value = value;
            this.type = type;
            this.names = names;
        }

        public int getValue() {
            return value;
        }

        public Measurement getType() {
            return type;
        }

        public String[] getNames() {
            return names;
        }

        @Override
        public String[] getNamesList() {
            return getNames();
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

        Weight(int value, Measurement type, String[] names) {
            this.value = value;
            this.type = type;
            this.names = names;
        }

        public int getValue() {
            return value;
        }

        public Measurement getType() {
            return type;
        }

        public String[] getNames() {
            return names;
        }

        @Override
        public String[] getNamesList() {
            return getNames();
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
                "°F"
        }),

        //Metric
        CELSIUS(0, METRIC, new String[]{
                "Celsius",
                "celsius",
                "°C"
        });

        private final int value;
        private final Measurement type;
        private final String[] names;

        Temperature(int value, Measurement type, String[] names) {
            this.value = value;
            this.type = type;
            this.names = names;
        }

        public int getValue() {
            return value;
        }

        public Measurement getType() {
            return type;
        }

        public String[] getNames() {
            return names;
        }

        @Override
        public String[] getNamesList() {
            return getNames();
        }
    }

    public static Measurable[][] getLists() {
        Measurable[][] lists = {Liquid.values(), Weight.values(), Temperature.values()};
        return lists;
    }
}