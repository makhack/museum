package com.example.matthieu.sidenav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Amine on 14-Jan-16.
 */
public class ItemDAO extends BaseDAO {
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

    private SQLiteDatabase mDB = null;

    public ItemDAO(Context context, SQLiteDatabase db) {
        super(context);
        this.mDB = db;
    }

    public void add(Item i) {
        ContentValues value = new ContentValues();
        value.put(ItemDAO.ITEM_NAME, i.getName());
        value.put(ItemDAO.ITEM_DESCRIPTION, i.getDescription());
        value.put(ItemDAO.ITEM_IMG, i.getImage());
        value.put(ItemDAO.ITEM_LATITUDE, i.get_latitude());
        value.put(ItemDAO.ITEM_LONGITUDE, i.get_longitude());
        value.put(ItemDAO.ITEM_THEME_ID, i.get_theme_id());
        mDB.insert(ItemDAO.ITEM_TABLE_NAME, null, value);
    }

    public void delete(long id) {
        mDB.delete(ITEM_TABLE_NAME, ITEM_KEY + " = ?", new String[]{String.valueOf(id)});
    }

    public void edit(Item i) {
        ContentValues value = new ContentValues();
        value.put(ItemDAO.ITEM_NAME, i.getName());
        value.put(ItemDAO.ITEM_DESCRIPTION, i.getDescription());
        value.put(ItemDAO.ITEM_IMG, i.getImage());
        value.put(ItemDAO.ITEM_LATITUDE, i.get_latitude());
        value.put(ItemDAO.ITEM_LONGITUDE, i.get_longitude());
        value.put(ItemDAO.ITEM_THEME_ID, i.get_item_id());
        mDB.update(ITEM_TABLE_NAME, value, ITEM_KEY + " = ?", new String[]{String.valueOf(i.get_item_id())});
    }

    public Item select(long id) {
        Cursor c = mDB.rawQuery(
                "Select " + ITEM_KEY + ", "
                        + ITEM_NAME + ", "
                        + ITEM_THEME_ID + ", "
                        + ITEM_IMG + ", "
                        + ITEM_DESCRIPTION + ", "
                        + ITEM_LATITUDE + ", "
                        + ITEM_LONGITUDE + " from "
                        + ITEM_TABLE_NAME + " where "
                        + ITEM_KEY + " = ?", new String[]{String.valueOf(id)});

        Item i = null;
        if (c != null) {
            while (c.moveToNext()) {
                i = new Item(c.getInt(3), c.getString(4), c.getString(1), c.getDouble(5), c.getDouble(6), c.getLong(2), c.getLong(0));
                break;
            }
            c.close();
        }

        if (i == null) {
            return null;
        }
        return i;
    }

    public ArrayList<Item> selectAll() {
        Cursor c = mDB.rawQuery(
                "Select " + ITEM_KEY + ", "
                        + ITEM_NAME + ", "
                        + ITEM_THEME_ID + ", "
                        + ITEM_IMG + ", "
                        + ITEM_DESCRIPTION + ", "
                        + ITEM_LATITUDE + ", "
                        + ITEM_LONGITUDE + " from "
                        + ITEM_TABLE_NAME, null);

        ArrayList<Item> itemlist = null;
        if (c != null) {
            while (c.moveToNext()) {
                if (itemlist == null) {
                    itemlist = new ArrayList<>();
                }

                Item i = new Item(c.getInt(3), c.getString(4), c.getString(1), c.getDouble(5), c.getDouble(6), c.getLong(2), c.getLong(0));
                itemlist.add(i);
            }
            c.close();
        }

        if (itemlist == null) {
            return null;
        }

        return itemlist;
    }

    public ArrayList<Item> selectAllItemsByThemeId(long id) {
        Cursor c = mDB.rawQuery(
                "Select " + ITEM_KEY + ", "
                        + ITEM_NAME + ", "
                        + ITEM_THEME_ID + ", "
                        + ITEM_IMG + ", "
                        + ITEM_DESCRIPTION + ", "
                        + ITEM_LATITUDE + ", "
                        + ITEM_LONGITUDE + " from "
                        + ITEM_TABLE_NAME + " where "
                        + ITEM_THEME_ID + " = ?", new String[]{String.valueOf(id)});

        if (c.getCount() <= 0) {
            Log.v("ALERT", "ALERT");
        }

        ArrayList<Item> itemlist = null;
        if (c != null) {
            while (c.moveToNext()) {
                if (itemlist == null) {
                    itemlist = new ArrayList<>();
                }

                Item i = new Item(c.getInt(3), c.getString(4), c.getString(1), c.getDouble(5), c.getDouble(6), c.getLong(2), c.getLong(0));
                itemlist.add(i);
            }
            c.close();
        }

        if (itemlist == null) {
            return null;
        }

        return itemlist;
    }
}
