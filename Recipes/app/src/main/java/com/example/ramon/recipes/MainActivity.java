package com.example.ramon.recipes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.ramon.recipes.data.RecipeContract;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, RecipeAdapter.ListItemClickListener {

    private static final int RECIPE_LOADER_ID = 0;
    public static final String[] RECIPE_COLUMNS = {
            RecipeContract.RecipeEntry._ID,
            RecipeContract.RecipeEntry.COLUMN_TITLE,
            RecipeContract.RecipeEntry.COLUMN_SERVINGS,
            RecipeContract.RecipeEntry.COLUMN_PREP_TIME,
            RecipeContract.RecipeEntry.COLUMN_COOK_TIME,
            RecipeContract.RecipeEntry.COLUMN_INGREDIENTS,
            RecipeContract.RecipeEntry.COLUMN_DIRECTIONS,
    };

    private RecipeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int mPosition = RecyclerView.NO_POSITION;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mRecyclerView = findViewById(R.id.rv_recipes);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new RecipeAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        showLoading();

        getSupportLoaderManager().initLoader(RECIPE_LOADER_ID, null, this);

        // setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case RECIPE_LOADER_ID:
                Uri queryUri = RecipeContract.RecipeEntry.CONTENT_URI;
                String sortOrder = RecipeContract.RecipeEntry.COLUMN_TITLE;
                return new CursorLoader(this, queryUri, RECIPE_COLUMNS,
                        null, null, sortOrder);
            default:
                throw new RuntimeException("Loader Not Implemented: " + id);

        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
        if (mPosition == RecyclerView.NO_POSITION) {
            mPosition = 0;
        }
        mRecyclerView.smoothScrollToPosition(mPosition);
        if (data == null) return;
        if (data.getCount() != 0) showRecipeList();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(int recipeId) {

        if (recipeId > 0 && recipeId <= 26) {
            return;
        }
        Intent displayRecipeIntent = new Intent(this, DisplayRecipeActivity.class);
        displayRecipeIntent.putExtra(Intent.EXTRA_TEXT, "" + recipeId);
        startActivity(displayRecipeIntent);
    }


    private void showRecipeList() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id) {
            case R.id.action_settings:
                Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(startSettingsActivity);
                return true;
            case R.id.action_delete:
                Uri uri = RecipeContract.RecipeEntry.CONTENT_URI;
                getContentResolver().delete(uri, RecipeContract.RecipeEntry._ID + ">?", new String[]{"26"});
                return true;
            case R.id.action_export:
                exportToTextFile();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    // TODO: methods to read a recipe.txt file
    // COMPLETED: methods to write to a recipe.txt file
    // TODO: method to "share recipe" through email, text, etc

    private void exportToTextFile() {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput
                    ("recipes.txt", Context.MODE_PRIVATE));
            // code to iterate through each recipe and write to file
            String[][] recipeData = getRecipeDataToExport();

            for (int k = 0; k < recipeData.length; k++) {
                outputStreamWriter.write(recipeData[k][0] + ", serves " + recipeData[k][1]);
                outputStreamWriter.write("\ntags: ");

                String[] tags = recipeData[k][6].split("`");
                for (String entry : tags) {
                    outputStreamWriter.write(entry + ", ");
                }

                outputStreamWriter.write("\nPrep Time " + recipeData[k][2] + ", Cook Time: " +
                        recipeData[k][3] + "\n\n");

                outputStreamWriter.write("Ingredients: \n");
                String[] ingredients = recipeData[k][4].split("`");
                for (String entry : ingredients) {
                    outputStreamWriter.write(entry + "\n");
                }

                outputStreamWriter.write("\nDirections: ");
                String[] directions = recipeData[k][5].split("`");
                outputStreamWriter.write("\n");
                for (String entry : directions) {
                    outputStreamWriter.write(entry + "\n");
                }
                outputStreamWriter.write("\n---\n");
            }

            outputStreamWriter.close();
        } catch (Exception e) {
            Log.e("Exception", "File write failed");
        }
    }

    private void readFromFile() {
        String fileText = "";
        try {
            InputStream inputStream = openFileInput("recipes.txt");
        } catch (FileNotFoundException e) {
            Log.e("Exception", "File not found");
        }
    }

    private String[][] getRecipeDataToExport() {
        Cursor cursor = getContentResolver().query(RecipeContract.RecipeEntry.CONTENT_URI,
                null,
                RecipeContract.RecipeEntry._ID + ">?",
                new String[]{"26"},
                RecipeContract.RecipeEntry.COLUMN_TITLE + " ASC");
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();

        String[][] recipeData = new String[cursor.getCount()][];

        for (int k = 0; k < cursor.getCount(); k++) {
            String titleString = cursor.getString(
                    cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TITLE));
            String servingString = cursor.getString(
                    cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_SERVINGS));
            String prepTimeString = cursor.getString(
                    cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_PREP_TIME));
            String cookTimeString = cursor.getString(
                    cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_COOK_TIME));
            String ingredientString = cursor.getString(
                    cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_INGREDIENTS));
            String directionString = cursor.getString(
                    cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_DIRECTIONS));
            String tagString = cursor.getString(
                    cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_TAGS));

            String[] individualRecipeData = {titleString,
                    servingString,
                    prepTimeString,
                    cookTimeString,
                    ingredientString,
                    directionString,
                    tagString};

            recipeData[k] = individualRecipeData;
            cursor.moveToNext();
        }

        return recipeData;
    }
}
