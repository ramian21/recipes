package com.example.ramon.recipes;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramon.recipes.data.RecipeContract;

public class AddRecipeActivity extends AppCompatActivity {

    private EditText mRecipeTitleEditText;
    private EditText mServingsEditText;
    private EditText mPrepTimeEditText;
    private EditText mCookTimeEditText;
    private EditText mIngredientsEditText;
    private EditText mDirectionsEditText;
    private Button mSubmitRecipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        mRecipeTitleEditText = (EditText) findViewById(R.id.et_recipe_title);
        mServingsEditText = (EditText) findViewById(R.id.et_servings);
        mPrepTimeEditText = (EditText) findViewById(R.id.et_prep_time);
        mCookTimeEditText = (EditText) findViewById(R.id.et_cook_time);
        mIngredientsEditText = (EditText) findViewById(R.id.et_ingredients);
        mDirectionsEditText = (EditText) findViewById(R.id.et_directions);
        mSubmitRecipeButton = (Button) findViewById(R.id.btn_submit_recipe);
        mSubmitRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mRecipeTitleEditText.getText().toString();
                String servings = mServingsEditText.getText().toString();
                String prepTime = mPrepTimeEditText.getText().toString();
                String cookTime = mCookTimeEditText.getText().toString();
                String ingredients = mIngredientsEditText.getText().toString();
                String directions = mDirectionsEditText.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_TITLE, title);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_SERVINGS, servings);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_PREP_TIME, prepTime);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_COOK_TIME, cookTime);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_INGREDIENTS, ingredients);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_DIRECTIONS, directions);

                Uri uri = getContentResolver().insert(RecipeContract.RecipeEntry.CONTENT_URI, contentValues);

                if (uri != null) {
                    Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
                }

                finish();
            }
        });
    }


}
