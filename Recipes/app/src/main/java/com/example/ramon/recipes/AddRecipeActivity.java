package com.example.ramon.recipes;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ramon.recipes.data.RecipeContract;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {

    private EditText mRecipeTitleEditText;
    private EditText mServingsEditText;
    private EditText mPrepTimeEditText;
    private EditText mCookTimeEditText;
    private EditText mIngredientsEditText;
    private EditText mDirectionsEditText;
    private Button mSubmitRecipeButton;

    private ArrayList<EditText> mIngredientList;
    private ArrayList<EditText> mDirectionList;

    private TextWatcher mIngredientTextChangeListener;
    private TextWatcher mDirectionTextChangeListener;

    private View.OnFocusChangeListener mTextChangeListenerRemover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        mIngredientList = new ArrayList<EditText>();
        mDirectionList = new ArrayList<EditText>();

        mRecipeTitleEditText = findViewById(R.id.et_recipe_title);
        mServingsEditText = findViewById(R.id.et_servings);
        mPrepTimeEditText = findViewById(R.id.et_prep_time);
        mCookTimeEditText = findViewById(R.id.et_cook_time);
        mIngredientsEditText = findViewById(R.id.et_ingredients);
        mDirectionsEditText = findViewById(R.id.et_directions);
        mSubmitRecipeButton = findViewById(R.id.btn_submit_recipe);

        mIngredientList.add(mIngredientsEditText);
        mDirectionList.add(mDirectionsEditText);


        // TODO: test removing empty EditTexts
        // TODO: create method to re-set text listener on latest EditText
        mTextChangeListenerRemover = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    EditText et = (EditText) view;
                    if (et.getText().toString().length() == 0) {
                        ((ViewGroup) (view.getParent())).removeView(view);
                        if (!mIngredientList.remove(et)) {
                            mDirectionList.remove(et);
                        }
                    }
                }
            }
        };

        mIngredientTextChangeListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                addNewIngredientEditText();
            }
        };

        mDirectionTextChangeListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                addNewDirectionEditText();
            }
        };

        attachListenerToNextIngredientEditText();
        attachListenerToNextDirectionEditText();

        mSubmitRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mRecipeTitleEditText.getText().toString();
                String servings = mServingsEditText.getText().toString();
                String prepTime = mPrepTimeEditText.getText().toString();
                String cookTime = mCookTimeEditText.getText().toString();
                //TODO: figure out how to pack all ingredients/directions into db
                String ingredients = "";
                for (EditText e : mIngredientList) {
                    ingredients = ingredients.concat(e.getText().toString()).concat("`");
                }
                String directions = "";
                for (EditText e : mDirectionList) {
                    directions = directions.concat(e.getText().toString()).concat("`");
                }

                ContentValues contentValues = new ContentValues();
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_TITLE, title);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_SERVINGS, servings);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_PREP_TIME, prepTime);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_COOK_TIME, cookTime);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_INGREDIENTS, ingredients);
                contentValues.put(RecipeContract.RecipeEntry.COLUMN_DIRECTIONS, directions);

                Uri uri = getContentResolver().insert(
                        RecipeContract.RecipeEntry.CONTENT_URI, contentValues);

                if (uri != null) {
                    Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
                }

                finish();
            }
        });
    }

    private void attachListenerToNextIngredientEditText() {
        EditText newEditText = mIngredientList.get(mIngredientList.size() - 1);
        newEditText.addTextChangedListener(mIngredientTextChangeListener);
        if (mIngredientList.size() > 1) {
            EditText oldEditText = mIngredientList.get(mIngredientList.size() - 2);
            oldEditText.removeTextChangedListener(mIngredientTextChangeListener);
            newEditText.setOnFocusChangeListener(mTextChangeListenerRemover);
        }
    }


    private void addNewIngredientEditText() {
        LinearLayout layout = findViewById(R.id.layout_ingredients);
        EditText nextIngredientEditText = new EditText(this);
        nextIngredientEditText.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        nextIngredientEditText.setHint(R.string.recipe_next_ingredient_hint);
        mIngredientList.add(nextIngredientEditText);
        attachListenerToNextIngredientEditText();
        layout.addView(nextIngredientEditText);
    }

    private void attachListenerToNextDirectionEditText() {
        EditText newEditText = mDirectionList.get(mDirectionList.size() - 1);
        newEditText.addTextChangedListener(mDirectionTextChangeListener);
        if (mDirectionList.size() > 1) {
            EditText oldEditText = mDirectionList.get(mDirectionList.size() - 2);
            oldEditText.removeTextChangedListener(mDirectionTextChangeListener);
            newEditText.setOnFocusChangeListener(mTextChangeListenerRemover);
        }
    }

    private void addNewDirectionEditText() {
        LinearLayout layout = findViewById(R.id.layout_directions);
        EditText nextDirectionEditText = new EditText(this);
        nextDirectionEditText.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        nextDirectionEditText.setHint(R.string.recipe_next_direction_hint);
        mDirectionList.add(nextDirectionEditText);
        attachListenerToNextDirectionEditText();
        layout.addView(nextDirectionEditText);
    }

}
