package com.example.ramon.recipes;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayRecipeActivity extends AppCompatActivity {

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

        // COMPLETED: figure out how to use resource arrays programmatically

        Intent parentIntent = getIntent();

        String id = parentIntent.getStringExtra(Intent.EXTRA_TEXT);

        Uri uri = RecipeContract.RecipeEntry.CONTENT_URI;
        mCursor = getContentResolver().query(uri, null, null, null, null);

        mCursor.moveToPosition(Integer.parseInt(id) - 1);   // -1 because _id starts at 1 but arrays start at 0 :upside_down:

        mDisplayTitleTextView.setText(mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TITLE)));

        String servingsSetText = getString(R.string.display_servings_prefix) + mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_SERVINGS));
        mDisplayServingsTextView.setText(servingsSetText);

        // TODO: do math to calculate length of time for prep time and cook time
        String prepTimeSetText = getString(R.string.display_prep_time_prefix) + mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_PREP_TIME));
        mDisplayPrepTimeTextView.setText(prepTimeSetText);

        String cookTimeSetText = getString(R.string.display_cook_time_prefix) + mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_COOK_TIME));
        mDisplayCookTimeTextView.setText(cookTimeSetText);

        String ingredientsFullString = mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_INGREDIENTS));
        mIngredientList = ingredientsFullString.split("`");

        // TODO: parse each entry of ingredient to find the measurement listed
        //          for each, check against imperial/metric preference
        //          if not preferred, convert
        //          if measurement not found, leave as is

        ArrayAdapter<String> ingredientListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                Arrays.asList(mIngredientList));
        mIngredientListView.setAdapter(ingredientListAdapter);
        Utils.setListViewHeightBasedOnChildren(mIngredientListView);

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
