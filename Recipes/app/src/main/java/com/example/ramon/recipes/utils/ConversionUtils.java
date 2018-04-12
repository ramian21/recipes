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
    public static final int MILLILITER = 1501; //"milliliter";
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
    public static double convertToMetric(double amount, int currentUnit) {

        //check to see if unit is already in metric
        //isolate the hundreds digit, 0 for imperial and 5 for metric
        if (currentUnit / 100 % 10 == 5) return amount;

        double currentUnitInBaseMetric;
        int desiredUnitMeasurement;

        switch (currentUnit / 1000) {
            case 1: //liquid
                desiredUnitMeasurement = MILLILITER;
                break;
            case 2:
                desiredUnitMeasurement = GRAM;
                break;
            case 3:
                desiredUnitMeasurement = CELSIUS;
                break;
            default:
                desiredUnitMeasurement = -1;
        }
        currentUnitInBaseMetric = convertToBaseUnit(amount, currentUnit, desiredUnitMeasurement);

        return currentUnitInBaseMetric;
    }

    public static double convertToImperial(double amount, int currentUnit) {

        //check to see if unit is already in imperial
        //isolate the hundreds digit, 0 for imperial and 5 for metric
        if (currentUnit / 100 % 10 == 0) return amount;

        double currentUnitInBaseImperial;
        int desiredUnitMeasurement;

        switch (currentUnit / 1000) {
            case 1: //liquid
                desiredUnitMeasurement = TEASPOON;
                break;
            case 2: //weight
                desiredUnitMeasurement = OUNCE;
                break;
            case 3: //temperature
                desiredUnitMeasurement = FAHRENHEIT;
                break;
            default:
                desiredUnitMeasurement = -1;
        }
        currentUnitInBaseImperial = convertToBaseUnit(amount, currentUnit, desiredUnitMeasurement);

        return currentUnitInBaseImperial;
    }

    public static double convertLiquid(double amount, int currentUnit, int desiredUnit) {

        if (currentUnit / 1000 != 1)
            return -1;

        double convertedUnit;
        int baseUnit;

        if (currentUnit / 100 % 10 == 0) {
            baseUnit = TEASPOON;
        } else {
            baseUnit = MILLILITER;
        }
        convertedUnit = convertToBaseUnit(amount, currentUnit, baseUnit);

        switch (desiredUnit) {
            case TEASPOON:
                break;
            case TABLESPOON:
                convertedUnit *= 3;
                break;
            case FLUID_OUNCE:
                convertedUnit *= 15;
                break;
            case CUP:
                convertedUnit *= 120;
                break;
            case PINT:
                convertedUnit *= 240;
                break;
            case QUART:
                convertedUnit *= 480;
                break;
            case HALF_GALLON:
                convertedUnit *= 960;
                break;
            case GALLON:
                convertedUnit *= 1920;
                break;
        }
        return convertedUnit;
    }


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
            case HALF_GALLON:
                returnVal = (baseUnit * 960);
                break;
            case GALLON:
                returnVal = (baseUnit * 1920);
                break;
            default:
                returnVal = baseUnit;
        }


        return returnVal;
    }
}
