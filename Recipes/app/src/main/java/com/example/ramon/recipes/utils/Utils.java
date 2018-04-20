package com.example.ramon.recipes.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ramon on 3/28/2018.
 */

public class Utils {

// TODO: finish moving string array of names to Measurement.java


    private final String[] CUP = {
            "cup",
            "cups",
            "c",
            "C"
    };

    private final String[] PINT = {
            "pint",
            "pints",
            "pt",
            "pts"
    };

    private final String[] QUART = {
            "quart",
            "quarts",
            "qt",
            "qts",
    };

    private final String[] HALF_GALLON = {
            "half gallon",
            "half gallons,",
            "half gal",
            "half gals"
    };

    private final String[] GALLON = {
            "gallon",
            "gallons",
            "gal",
            "gals"
    };

    private final String[] MILLILITER = {
            "milliliter",
            "milliliters",
            "mL",
            "ml"
    };

    private final String[] LITER = {
            "liter",
            "liters",
            "L"
    };

    private final String[] OUNCE = {
            "ounce",
            "ounces",
            "oz"
    };

    private final String[] POUND = {
            "pound",
            "pounds",
            "lb",
            "lbs"
    };

    private final String[] GRAM = {
            "gram",
            "grams",
            "g",
    };

    private final String[] KILOGRAM = {
            "kilogram",
            "kilograms",
            "kg"
    };

    private final String[] FAHRENHEIT = {
            "Fahrenheit",
            "fahrenheit",
            "°F"
    };

    private final String[] CELSIUS = {
            "Celsius",
            "celsius",
            "°C"
    };


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

    public static void formatUnits(String[] array) {
        for (String s : array) {
            if (!checkAgainstUnitPreference(s, 0)) {
                fixUnits(s);
            }
        }
    }

    private static void fixUnits(String entry) {

    }

    private static boolean checkAgainstUnitPreference(String entry, int preference) {
        boolean isPreferred = true;

        //regex for word immediately after a number until next whitespace
        isPreferred = entry.matches(".*\\d+\\s*°*[a-zA-Z]+\\s*");


        return isPreferred;
    }
}
