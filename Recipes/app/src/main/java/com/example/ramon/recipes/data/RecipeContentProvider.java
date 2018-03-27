package com.example.ramon.recipes.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by Ramon on 3/22/2018.
 */

public class RecipeContentProvider extends ContentProvider {
    public static final int RECIPES = 100;
    public static final int RECIPE_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private RecipeDbHelper mRecipeDbHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(RecipeContract.AUTHORITY, RecipeContract.PATH_RECIPES, RECIPES);
        uriMatcher.addURI(RecipeContract.AUTHORITY, RecipeContract.PATH_RECIPES + "/#", RECIPE_WITH_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mRecipeDbHelper = new RecipeDbHelper(getContext());
        return true;
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mRecipeDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case RECIPES:
                long id = db.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(RecipeContract.RecipeEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }


    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mRecipeDbHelper.getReadableDatabase();

        int match = sUriMatcher.match(uri);
        Cursor retCursor;

        switch (match) {
            case RECIPES:
                retCursor = db.query(RecipeContract.RecipeEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case RECIPE_WITH_ID:
                String id = uri.getPathSegments().get(1);
                String selectClause = RecipeContract.RecipeEntry._ID + "=?";
                retCursor = db.query(RecipeContract.RecipeEntry.TABLE_NAME, projection, selectClause,
                        new String[]{id}, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mRecipeDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        int rowsDeleted;

        switch (match) {
            case RECIPES:
                rowsDeleted = db.delete(RecipeContract.RecipeEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case RECIPE_WITH_ID:
                String id = uri.getPathSegments().get(1);
                String selectClause = RecipeContract.RecipeEntry._ID + "=?";
                rowsDeleted = db.delete(RecipeContract.RecipeEntry.TABLE_NAME, selectClause,
                        new String[]{id});
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mRecipeDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        int tasksUpdated;

        switch (match) {
            case RECIPE_WITH_ID:
                String id = uri.getPathSegments().get(1);
                String selectionClause = RecipeContract.RecipeEntry._ID + " = ?";
                tasksUpdated = db.update(RecipeContract.RecipeEntry.TABLE_NAME, contentValues, selectionClause, new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
        if (tasksUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return tasksUpdated;
    }


    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case RECIPES:
                return "vnd.android.cursor.dir" + "/" + RecipeContract.AUTHORITY + "/" + RecipeContract.PATH_RECIPES;
            case RECIPE_WITH_ID:
                return "vnd.android.cursor.item" + "/" + RecipeContract.AUTHORITY + "/" + RecipeContract.PATH_RECIPES;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }
}
