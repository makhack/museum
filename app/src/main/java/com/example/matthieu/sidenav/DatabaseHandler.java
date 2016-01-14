package com.example.matthieu.sidenav;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Amine on 14-Jan-16.
 */
public class DatabaseHandler extends SQLiteOpenHelper{
    // TABLE THEME
    public static final String THEME_KEY = "theme_id";
    public static final String THEME_NAME = "name";
    public static final String THEME_IMG = "img";
    public static final String THEME_DESCRIPTION = "description";

    public static final String THEME_TABLE_NAME = "Theme";
    public static final String THEME_TABLE_CREATE =
            "CREATE TABLE " + THEME_TABLE_NAME + " ("
            + THEME_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + THEME_NAME + " TEXT, "
            + THEME_IMG + " INTEGER, "
            + THEME_DESCRIPTION + " TEXT);";

    public static final String THEME_TABLE_DROP = "DROP TABLE IF EXISTS " + THEME_TABLE_NAME + ";";
    // END _TABLE THEME

    // TABLE ITEM
    public static final String ITEM_KEY = "item_id";
    public static final String ITEM_NAME = "name";
    public static final String ITEM_THEME_ID = "theme_id";
    public static final String ITEM_IMG = "img";
    public static final String ITEM_DESCRIPTION = "description";
    public static final String ITEM_LATITUDE = "latitude";
    public static final String ITEM_LONGITUDE = "longitude";

    public static final String ITEM_TABLE_NAME = "Item";
    public static final String ITEM_TABLE_CREATE =
            "CREATE TABLE " + ITEM_TABLE_NAME + " ("
            + ITEM_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ITEM_NAME + " TEXT, "
            + ITEM_THEME_ID + " INTEGER, "
            + ITEM_IMG + " INTEGER, "
            + ITEM_DESCRIPTION + " INTEGER, "
            + ITEM_LATITUDE + " DOUBLE, "
            + ITEM_LONGITUDE + " DOUBLE);";

    public static final String ITEM_TABLE_DROP = "DROP TABLE IF EXISTS " + ITEM_TABLE_NAME + ";";
    // END _TABLE ITEM

    // TABLE FAVORITES
    public static final String FAV_KEY = "fav_id";
    public static final String FAV_ITEM_ID = "item_id";

    public static final String FAV_TABLE_NAME = "Favorites";
    public static final String FAV_TABLE_CREATE =
            "CREATE TABLE " + FAV_TABLE_NAME + " ("
            + FAV_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FAV_ITEM_ID + " INTEGER);";

    public static final String FAV_TABLE_DROP = "DROP TABLE IF EXISTS " + FAV_TABLE_NAME + ";";
    // END _TABLE FAVORITES

    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(THEME_TABLE_CREATE);
        db.execSQL(ITEM_TABLE_CREATE);
        db.execSQL(FAV_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(THEME_TABLE_DROP);
        db.execSQL(ITEM_TABLE_DROP);
        db.execSQL(FAV_TABLE_DROP);
        onCreate(db);
    }
}
