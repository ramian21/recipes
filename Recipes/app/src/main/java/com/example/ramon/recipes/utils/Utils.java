package com.example.ramon.recipes.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.ramon.recipes.utils.Measurement.getLists;

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
            if (!checkAgainstUnitPreference(s, 0)) {
                fixUnits(s);
            }
        }
    }

    private static void fixUnits(String entry) {

    }

    public static boolean checkAgainstUnitPreference(String entry, int preference) {
        boolean isPreferred = true;

        //regex for word immediately after a number until next whitespace
        //isPreferred = entry.matches(".*\\d+\\s*°*[a-zA-Z]+\\s*");
        isPreferred = entry.matches("(?<=\\d\\s)[a-zA-Z]+");

        // TODO: find out how to retrieve the measurement keyword from the entry

        Measurement.Measurable measurement = Measurement.getMeasurement(entry);

        return isPreferred;
    }
}
