package com.example.ramon.recipes.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.ramon.recipes.R;

/**
 * Created by Ramon on 3/28/2018.
 */

public class Utils {


    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/

    public static void setListViewHeightBasedOnChildren(ListView listView) {

        //if the adapter of this ListView is null, return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        //begin creating the dimensions of the desired ListView
        //retain the current width of the ListView
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);

        //initialize the height to 0
        int totalHeight = 0;

        //initialize a temporary View to use as a reference to each View in the ListView
        View view = null;

        //iterate through each View in the ListView and add its height to totalHeight
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }

        //set the ListView's height param to totalHeight
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void formatUnits(String[] array, Context context) {
        for (int k = 0; k < array.length; k++) {
            if (!checkAgainstUnitPreference(array[k], context)) {
                array[k] = fixUnits(array[k], context);
            }
        }
    }

    private static String fixUnits(String entry, Context context) {

        //retrieve appropriate keywords from entry string
        String measurementWord = retrieveMeasurement(entry);
        String valueWord = retrieveValue(entry);
        String restOfTheEntry;
        String fixedValue;
        String fixedMeasurement;
        String fixedString;
        Fraction valueFraction;

        int index = entry.indexOf(measurementWord);

        if (index + measurementWord.length() >= entry.length()) {
            restOfTheEntry = "";
        } else {
            restOfTheEntry = entry.substring(index + measurementWord.length());
        }

        //parse the value string into a numeric fraction to use
        if (valueWord.contains("/")) {
            valueFraction = Fraction.parseFraction(valueWord);
        } else {
            double numerator = Double.parseDouble(valueWord);
            valueFraction = new Fraction(numerator);
        }

        Measurement.Measurable measurement = Measurement.getMeasurement(measurementWord);
        if (measurement == null) {
            return entry;
        }

        // TODO: check fraction/decimal preference

        double convertedUnit = measurement.convertToOtherUnit(valueFraction.getValue());
        fixedValue = "" + convertedUnit; //change this after fraction/decimal pref is created


        measurement = measurement.getOtherBaseUnit();
        String[] nameList = measurement.getNames();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (sharedPreferences.getString(context.getString(R.string.pref_unit_abbreviation_key), "")
                .equals(context.getString(R.string.pref_name_full_value))) {
            if (valueFraction.getValue() > 1) {
                fixedMeasurement = nameList[1];
            } else {
                fixedMeasurement = nameList[0];
            }
        } else {
            fixedMeasurement = nameList[2];
        }
        fixedString = fixedValue + " " + fixedMeasurement + restOfTheEntry;
        return fixedString;
    }


    public static boolean checkAgainstUnitPreference(String entry, Context context) {

        boolean isPreferred = false;

        String measureKeyWord = retrieveMeasurement(entry); //retrieve keyword
        Measurement.Measurable foundMeasurement = Measurement.getMeasurement(measureKeyWord); //match keyword to measurement
        if (foundMeasurement != null) { // COMPLETED: fix preference checking

            //retrieve unit measurement preference
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Measurement preferredUnitMeasurement;

            //if preference is imperial, set to imperial, else set to metric
            String prefChoice = sharedPreferences.getString(context.getString(R.string.pref_unit_choice_key), "");
            String impChoice = context.getString(R.string.pref_choice_imperial_value);
            if (prefChoice.equals(impChoice)) {
                preferredUnitMeasurement = Measurement.IMPERIAL;
            } else {
                preferredUnitMeasurement = Measurement.METRIC;
            }

            //check if measurement types match
            if (foundMeasurement.getType().equals(preferredUnitMeasurement)) {
                isPreferred = true;
            }
        }
        return isPreferred;
    }

    public static String retrieveValue(String entry) {
        String valueKeyWord = "";
        Pattern pattern = Pattern.compile("\\d*\\/?\\d.*\\d+");
        Matcher matcher = pattern.matcher(entry);

        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            valueKeyWord = entry.substring(start, end);
        } else {
            valueKeyWord = "1";
        }
        return valueKeyWord;
    }

    public static String retrieveMeasurement(String entry) {

        String measureKeyWord = "";
        Pattern pattern = Pattern.compile("(?<=\\d|\\s|°)[a-zA-Z]+");
        Matcher matcher = pattern.matcher(entry);

        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            measureKeyWord = entry.substring(start, end);
        }

        return measureKeyWord;
    }
}
