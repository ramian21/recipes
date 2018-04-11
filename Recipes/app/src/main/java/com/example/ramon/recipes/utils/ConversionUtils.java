package com.example.ramon.recipes.utils;

public class ConversionUtils {

    /*****************************
     * List of liquid measurements
     ****************************/

//Imperial

    public static final int TEASPOON = 1001; //"teaspoon";
    public static final int TABLESPOON = 1002; //"tablespoon";
    public static final int FLUID_OUNCE = 1003; //"fluid-ounce";
    public static final int CUP = 1004; //"cup";
    public static final int PINT = 1005; //"pint";
    public static final int QUART = 1006; //"quart";
    public static final int HALF_GALLON = 1007; //"half-gallon";
    public static final int GALLON = 1008; //"gallon";

    //Metric
    public static final int MILLILETER = 1501; //"milliliter";
    public static final int LITER = 1502; //"liter";


    /*****************************
     * List of Weight measurements
     ****************************/

//Imperial

    public static final int OUNCE = 2001; //"ounce";
    public static final int POUND = 2002; //"pound";

//Metric

    public static final int GRAM = 2501; //"gram";
    public static final int KILOGRAM = 2502; //"kilogram";

    /**********************************
     * List of Temperature Measurements
     **********************************/

//Imperial

    public static final int FAHRENHEIT = 3001; //"fahrenheit";

//Metric

    public static final int CELSIUS = 3501; //"celsius";

    /**
     * Conversion Methods
     */

    //convertToMetric
    //convertToImperial
    //convertLiquid
    //convertTemperature
    //convertWeight
    //convertToBaseUnit
//    public static double convertToMetric(double amount, int currentUnit) {
//        if (currentUnit / 100 % 10 == 5) return amount;
//
//        double currentUnitInMilliliters;
//        convertToBaseUnit(amount, currentUnit, MILLILETER);
//
//    }


    private static double convertToBaseUnit(double amount, int currentUnit, int desiredUnit) {

        //check if type of unit desired is of same type as the given unit?
        //code to do that

        double returnVal;
        //for liquids
        double baseUnit;
        switch (currentUnit) {
            case TEASPOON:
                baseUnit = amount;
                break;
            case TABLESPOON:
                baseUnit = (amount * 3);
                break;
            case FLUID_OUNCE:
                baseUnit = (amount * 15);
                break;
            case CUP:
                baseUnit = (amount * 120);
                break;
            case PINT:
                baseUnit = (amount * 240);
                break;
            case QUART:
                baseUnit = (amount * 480);
                break;
            case GALLON:
                baseUnit = (amount * 960);
                break;
            default:
                baseUnit = -1;
                break;
        }

        switch (desiredUnit) {
            case TEASPOON:
                returnVal = baseUnit;
                break;
            case TABLESPOON:
                returnVal = (baseUnit * 3);
                break;
            case FLUID_OUNCE:
                returnVal = (baseUnit * 15);
                break;
            case CUP:
                returnVal = (baseUnit * 120);
                break;
            case PINT:
                returnVal = (baseUnit * 240);
                break;
            case QUART:
                returnVal = (baseUnit * 480);
                break;
            case GALLON:
                returnVal = (baseUnit * 960);
                break;
            default:
                returnVal = baseUnit;
        }


        return returnVal;
    }
}
