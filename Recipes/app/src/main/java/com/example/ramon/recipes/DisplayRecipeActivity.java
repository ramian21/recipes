package com.example.ramon.recipes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ramon.recipes.data.RecipeContract;
import com.example.ramon.recipes.utils.Utils;

import java.util.Arrays;

public class DisplayRecipeActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView mDisplayTitleTextView;
    private TextView mDisplayServingsTextView;
    private TextView mDisplayPrepTimeTextView;
    private TextView mDisplayCookTimeTextView;
    private ListView mIngredientListView;
    private ListView mDirectionListView;
    private String[] mIngredientList;
    private String[] mDirectionList;
    private Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);

        mDisplayTitleTextView = findViewById(R.id.tv_display_recipe_title);
        mDisplayServingsTextView = findViewById(R.id.tv_serves);
        mDisplayPrepTimeTextView = findViewById(R.id.tv_prep_time);
        mDisplayCookTimeTextView = findViewById(R.id.tv_cook_time);
        mIngredientListView = findViewById(R.id.lv_ingredient_list);
        mDirectionListView = findViewById(R.id.lv_direction_list);

        updateText();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_unit_choice_key))) {
            boolean selectedImperial = sharedPreferences.getString(key, "").equals(getString(R.string.pref_choice_imperial_value));
            updateText();

        } else if (key.equals(getString(R.string.pref_unit_abbreviation_key))) {
            boolean selectedFullNames = sharedPreferences.getString(key, "").equals(getString(R.string.pref_name_full_value));
            updateText();
        }
    }

    private void updateText() {
        Intent parentIntent = getIntent();

        String id = parentIntent.getStringExtra(Intent.EXTRA_TEXT);

        Uri uri = RecipeContract.RecipeEntry.CONTENT_URI;
        mCursor = getContentResolver().query(uri, null, null, null, null);

        mCursor.moveToPosition(Integer.parseInt(id) - 1);   // -1 because _id starts at 1 but arrays start at 0 :upside_down:

        displayTitle();
        displayServings();
        displayPrepTime();
        displayCookTime();
        displayIngredients();
        displayDirections();
    }

    private void displayTitle() {
        mDisplayTitleTextView.setText(mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TITLE)));
    }

    private void displayServings() {
        String servingsSetText = getString(R.string.display_servings_prefix) + mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_SERVINGS));
        mDisplayServingsTextView.setText(servingsSetText);
    }

    // TODO: do math to calculate length of time for prep time and cook time
    // TODO: create a spinner to select unit of time measurement
    private void displayPrepTime() {
        String prepTimeSetText = getString(R.string.display_prep_time_prefix) + mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_PREP_TIME));
        mDisplayPrepTimeTextView.setText(prepTimeSetText);
    }

    private void displayCookTime() {
        String cookTimeSetText = getString(R.string.display_cook_time_prefix) + mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_COOK_TIME));
        mDisplayCookTimeTextView.setText(cookTimeSetText);
    }

    private void displayIngredients() {
        String ingredientsFullString = mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_INGREDIENTS));
        mIngredientList = ingredientsFullString.split("`");

        // COMPLETED: parse each entry of ingredient to find the measurement listed
        // COMPLETED  for each, check against imperial/metric preference
        //   TODO:   if not preferred, convert
        //          if measurement not found, leave as is

        Utils.formatUnits(mIngredientList, this);

        ArrayAdapter<String> ingredientListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                Arrays.asList(mIngredientList));
        mIngredientListView.setAdapter(ingredientListAdapter);
        Utils.setListViewHeightBasedOnChildren(mIngredientListView);
    }

    private void displayDirections() {
        String directionsFullString = mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_DIRECTIONS));
        mDirectionList = directionsFullString.split("`");
        ArrayAdapter<String> directionListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                Arrays.asList(mDirectionList));
        mDirectionListView.setAdapter(directionListAdapter);
        Utils.setListViewHeightBasedOnChildren(mDirectionListView);
    }
}
