//package com.example.ramon.recipes.utils;
//
//public class ConversionUtils {
//
//
//
//    /**
//     * Conversion Methods
//     */
//
//    //convertToMetric
//    //convertToImperial
//    //convertLiquid
//    //convertTemperature
//    //convertWeight
//    //convertToBaseUnit
//    public static double convertToMetric(double amount, Measurement currentUnit) {
//
//        //check to see if unit is already in metric
//        //retrieve measurement preference (WIP)
//        if (currentUnit.equals(Measurement.METRIC)) return amount;
//
//        if()
//
//        double currentUnitInBaseMetric;
//        int desiredUnitMeasurement;
//
//        currentUnitInBaseMetric = convertToBaseUnit(amount, currentUnit, desiredUnitMeasurement);
//
//        return currentUnitInBaseMetric;
//    }
//
//    public static double convertToImperial(double amount, int currentUnit) {
//
//        //check to see if unit is already in imperial
//        //isolate the hundreds digit, 0 for imperial and 5 for metric
//        if (currentUnit / 100 % 10 == 0) return amount;
//
//        double currentUnitInBaseImperial;
//        int desiredUnitMeasurement;
//
//        switch (currentUnit / 1000) {
//            case 1: //liquid
//                desiredUnitMeasurement = TEASPOON;
//                break;
//            case 2: //weight
//                desiredUnitMeasurement = OUNCE;
//                break;
//            case 3: //temperature
//                desiredUnitMeasurement = FAHRENHEIT;
//                break;
//            default:
//                desiredUnitMeasurement = -1;
//        }
//        currentUnitInBaseImperial = convertToBaseUnit(amount, currentUnit, desiredUnitMeasurement);
//
//        return currentUnitInBaseImperial;
//    }
//
//    public static double convertLiquid(double amount, int currentUnit, int desiredUnit) {
//
//        if (currentUnit / 1000 != 1)
//            return -1;
//
//        double convertedUnit;
//        int baseUnit;
//
//        if (currentUnit / 100 % 10 == 0) {
//            baseUnit = TEASPOON;
//        } else {
//            baseUnit = MILLILITER;
//        }
//        convertedUnit = convertToBaseUnit(amount, currentUnit, baseUnit);
//
//        switch (desiredUnit) {
//            case TEASPOON:
//                break;
//            case TABLESPOON:
//                convertedUnit *= 3;
//                break;
//            case FLUID_OUNCE:
//                convertedUnit *= 15;
//                break;
//            case CUP:
//                convertedUnit *= 120;
//                break;
//            case PINT:
//                convertedUnit *= 240;
//                break;
//            case QUART:
//                convertedUnit *= 480;
//                break;
//            case HALF_GALLON:
//                convertedUnit *= 960;
//                break;
//            case GALLON:
//                convertedUnit *= 1920;
//                break;
//        }
//        return convertedUnit;
//    }
//
//
//    private static double convertToBaseUnit(double amount, int currentUnit, int desiredUnit) {
//
//        //check if type of unit desired is of same type as the given unit?
//        //code to do that
//
//        double returnVal;
//        //for liquids
//        double baseUnit;
//        switch (currentUnit) {
//            case TEASPOON:
//                baseUnit = amount;
//                break;
//            case TABLESPOON:
//                baseUnit = (amount * 3);
//                break;
//            case FLUID_OUNCE:
//                baseUnit = (amount * 15);
//                break;
//            case CUP:
//                baseUnit = (amount * 120);
//                break;
//            case PINT:
//                baseUnit = (amount * 240);
//                break;
//            case QUART:
//                baseUnit = (amount * 480);
//                break;
//            case GALLON:
//                baseUnit = (amount * 960);
//                break;
//            default:
//                baseUnit = -1;
//                break;
//        }
//
//        switch (desiredUnit) {
//            case TEASPOON:
//                returnVal = baseUnit;
//                break;
//            case TABLESPOON:
//                returnVal = (baseUnit * 3);
//                break;
//            case FLUID_OUNCE:
//                returnVal = (baseUnit * 15);
//                break;
//            case CUP:
//                returnVal = (baseUnit * 120);
//                break;
//            case PINT:
//                returnVal = (baseUnit * 240);
//                break;
//            case QUART:
//                returnVal = (baseUnit * 480);
//                break;
//            case HALF_GALLON:
//                returnVal = (baseUnit * 960);
//                break;
//            case GALLON:
//                returnVal = (baseUnit * 1920);
//                break;
//            default:
//                returnVal = baseUnit;
//        }
//
//
//        return returnVal;
//    }
//}
