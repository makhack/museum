package com.example.matthieu.sidenav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amine on 14-Jan-16.
 */
public class FavoritesDAO extends BaseDAO{
    public static final String FAV_KEY = "fav_id";
    public static final String FAV_ITEM_ID = "item_id";

    public static final String FAV_TABLE_NAME = "Favorites";
    public static final String FAV_TABLE_CREATE =
            "CREATE TABLE " + FAV_TABLE_NAME + " ("
                    + FAV_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FAV_ITEM_ID + " INTEGER);";

    public static final String FAV_TABLE_DROP = "DROP TABLE IF EXISTS " + FAV_TABLE_NAME + ";";
    private SQLiteDatabase mDB = null;

    public FavoritesDAO(Context context, SQLiteDatabase db) {
        super(context);
        this.mDB = db;
    }

    public void add(Favorites f) {
        ContentValues value = new ContentValues();
        value.put(FavoritesDAO.FAV_ITEM_ID, f.getItem_id());
        mDB.insert(FavoritesDAO.FAV_TABLE_NAME, null, value);
    }

    public void delete(long id) {
        mDB.delete(FAV_TABLE_NAME, FAV_ITEM_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteFav(long id) {
        mDB.delete(FAV_TABLE_NAME, FAV_KEY + " = ?", new String[]{String.valueOf(id)});
    }

    public void edit(Favorites f) {
        ContentValues value = new ContentValues();
        value.put(FAV_ITEM_ID, f.getItem_id());
        mDB.update(FAV_TABLE_NAME, value, FAV_KEY + " = ?", new String[]{String.valueOf(f.getId())});
    }

    public Favorites select(long id) {
        Cursor c = mDB.rawQuery(
                "Select " + FAV_KEY + ", "
                        + FAV_ITEM_ID + " from "
                        + FAV_TABLE_NAME + " where "
                        + FAV_ITEM_ID + " = ?", new String[]{String.valueOf(id)});

        Favorites f = null;
        if (c != null)
        {
            while (c.moveToNext()) {
                f = new Favorites(c.getLong(0), c.getLong(1));
                break;
            }
            c.close();
        }

        if (f == null) {
            return null;
        }
        return f;
    }

    public ArrayList<Favorites> selectAll() {
        Cursor c = mDB.rawQuery(
                "Select " + FAV_KEY + ", "
                        + FAV_ITEM_ID + " from "
                        + FAV_TABLE_NAME, null);

        ArrayList<Favorites> favlist = null;
        if (c != null)
        {
            while (c.moveToNext()) {
                if (favlist == null) { favlist = new ArrayList<>(); }

                Favorites f = new Favorites(c.getLong(0), c.getLong(1));
                favlist.add(f);
            }
            c.close();
        }

        if (favlist == null) {
            return null;
        }

        return favlist;
    }
}
