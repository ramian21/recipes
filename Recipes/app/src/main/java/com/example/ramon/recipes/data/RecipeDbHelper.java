package com.example.ramon.recipes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ramon.recipes.data.RecipeContract.RecipeEntry;

/**
 * Created by Ramon on 3/22/2018.
 */

public class RecipeDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recipesdb.db";
    private static final int VERSION = 3;


    RecipeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " + RecipeEntry.TABLE_NAME + " (" +
                RecipeEntry._ID + " INTEGER PRIMARY KEY, " +
                RecipeEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                RecipeEntry.COLUMN_SERVINGS + " INTEGER, " +
                RecipeEntry.COLUMN_PREP_TIME + " INTEGER, " +
                RecipeEntry.COLUMN_COOK_TIME + " INTEGER, " +
                RecipeEntry.COLUMN_INGREDIENTS + " TEXT NOT NULL, " +
                RecipeEntry.COLUMN_DIRECTIONS + " TEXT NOT NULL, " +
                RecipeEntry.COLUMN_TAGS + " TEXT" +
                ");";
        db.execSQL(CREATE_TABLE);
        addHeaders(db);

        // COMPLETED: add letter headers to database after creation
        // COMPLETED: add a column for search tags
        //       also probably look up how onUpgrade() works
    }

    private void addHeaders(SQLiteDatabase db) {
        for (int k = (int) 'A'; k < (int) 'A' + 26; k++) {
            db.execSQL("INSERT INTO " + RecipeEntry.TABLE_NAME + " (" +
                    RecipeEntry.COLUMN_TITLE + ", " +
                    RecipeEntry.COLUMN_INGREDIENTS + ", " +
                    RecipeEntry.COLUMN_DIRECTIONS + ") " +
                    "VALUES (" +
                    "\"" + (char) k + "\"" + ", " + "\"\", " + "\"\"" +
                    ");");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + RecipeEntry.TABLE_NAME + " ADD COLUMN " +
                    RecipeEntry.COLUMN_TAGS + " TEXT;");
        }
        if (oldVersion < 3) {
            addHeaders(db);
        }
    }
}
