package com.example.ramon.recipes;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ramon.recipes.data.RecipeContract;

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

        // TODO: figure out how to use resource arrays programmatically
        // mIngredientList = findViewById(R.array.list_ingredients_labels);

        Intent parentIntent = getIntent();

        String id = parentIntent.getStringExtra(Intent.EXTRA_TEXT);

        Uri uri = RecipeContract.RecipeEntry.CONTENT_URI.buildUpon().appendPath(id).build();

        mCursor = getContentResolver().query(uri,
                null,
                null,
                null,
                null);

        mDisplayTitleTextView.setText(mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TITLE)));
        mDisplayServingsTextView.setText(mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_SERVINGS)));
        mDisplayPrepTimeTextView.setText(mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_PREP_TIME)));
        mDisplayCookTimeTextView.setText(mCursor.getString(
                mCursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_COOK_TIME)));


    }
}
