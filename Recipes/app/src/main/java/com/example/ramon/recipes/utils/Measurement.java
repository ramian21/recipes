package com.example.ramon.recipes.utils;

public enum Measurement {
    IMPERIAL, METRIC;

    public enum Liquid {

        /*****************************
         * List of liquid measurements
         ****************************/

        //Imperial
        TEASPOON(1, IMPERIAL, new String[] {
                "teaspoon",
                "teaspoons",
                "tsp",
                "ts",
                "t",
                "tspn"}), //"teaspoon";
        TABLESPOON(3, IMPERIAL, new String[] {
                "tablespoon",
                "tablespoons",
                "tbsp",
                "T",
                "tbls",
                "Tb"
        }), //"tablespoon";
        FLUID_OUNCE(6, IMPERIAL, new String[] {
                "fluid ounce",
                "fluid ounces",
                "fl oz"
        }), //"fluid-ounce";
        CUP(48, IMPERIAL), //"cup";
        PINT(96, IMPERIAL), //"pint";
        QUART(192, IMPERIAL), //"quart";
        HALF_GALLON(384, IMPERIAL), //"half-gallon";
        GALLON(768, IMPERIAL), //"gallon";

        //Metric
        MILLILITER(1, METRIC), //"milliliter";
        LITER(1000, METRIC); //"liter";

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
    }

    public enum Weight {
        /*****************************
         * List of Weight measurements
         ****************************/

//Imperial

        OUNCE(1, IMPERIAL), //"ounce";

        POUND(16, IMPERIAL), //"pound";

//Metric

        GRAM(1, METRIC), //"gram";

        KILOGRAM(1000, METRIC); //"kilogram";

        private final int value;
        private final Measurement type;

        Weight(int value, Measurement type) {
            this.value = value;
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public Measurement getType() {
            return type;
        }
    }

    public enum Temperature {
        /**********************************
         * List of Temperature Measurements
         **********************************/

    //Imperial

        FAHRENHEIT(32, IMPERIAL), //"fahrenheit";

//Metric

        CELSIUS(0, METRIC); //"celsius";

        private final int value;
        private final Measurement type;

        Temperature(int value, Measurement type) {
            this.value = value;
            this.type = type;
        }

        public int getValue() {
            return value;
        }

        public Measurement getType() {
            return type;
        }
    }
}