package com.example.ramon.recipes.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Ramon on 3/22/2018.
 */

public class RecipeContract {
    public static final String AUTHORITY = "com.example.ramon.recipes";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_RECIPES = "recipes";

    public static final class RecipeEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();

        public static final String TABLE_NAME = "recipes";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_SERVINGS = "servings";
        public static final String COLUMN_PREP_TIME = "prep_time";
        public static final String COLUMN_COOK_TIME = "cook_time";
        public static final String COLUMN_INGREDIENTS = "ingredients";
        public static final String COLUMN_DIRECTIONS = "directions";
        public static final String COLUMN_TAGS = "tags";
    }
}
