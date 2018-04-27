package com.example.ramon.recipes.utils;

import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static void formatUnits(String[] array) {
        for (String s : array) {
            if (!checkAgainstUnitPreference(s)) {
                fixUnits(s);
            }
        }
    }

    private static void fixUnits(String entry) {

    }

    public static String retrieveValue(String entry) {
        String valueKeyWord = "";

        return valueKeyWord;
    }

    public static boolean checkAgainstUnitPreference(String entry) {

        boolean isPreferred = false;

        String measureKeyWord = retrieveMeasurement(entry); //retrieve keyword
        Measurement.Measurable measurement = Measurement.getMeasurement(measureKeyWord); //match keyword to measurement
        if (measurement != null) { // TODO: finish preferences and retrieve preference to match to entry keyword

            SharedPreferences sharedPreferences = 


            if (measurement.getType().equals(Measurement.IMPERIAL)) {
                isPreferred = true;
            }
        }
        return isPreferred;
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
