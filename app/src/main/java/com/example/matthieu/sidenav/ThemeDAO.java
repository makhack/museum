package com.example.matthieu.sidenav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Amine on 14-Jan-16.
 */
public class ThemeDAO extends BaseDAO{
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

    private SQLiteDatabase mDB = null;

    public ThemeDAO(Context context, SQLiteDatabase db) {
        super(context);
        this.mDB = db;
    }

    public void add(Theme t) {
        ContentValues value = new ContentValues();
        value.put(ThemeDAO.THEME_NAME, t.getName());
        value.put(ThemeDAO.THEME_DESCRIPTION, t.getDescription());
        value.put(ThemeDAO.THEME_IMG, t.getImg());
        mDB.insert(ThemeDAO.THEME_TABLE_NAME, null, value);
    }

    public void delete(long id) {
        mDB.delete(THEME_TABLE_NAME, THEME_KEY + " = ?", new String[]{String.valueOf(id)});
    }

    public void edit(Theme t) {
        ContentValues value = new ContentValues();
        value.put(ThemeDAO.THEME_NAME, t.getName());
        value.put(ThemeDAO.THEME_DESCRIPTION, t.getDescription());
        value.put(ThemeDAO.THEME_IMG, t.getImg());
        mDB.update(THEME_TABLE_NAME, value, THEME_KEY + " = ?", new String[]{String.valueOf(t.getId())});
    }

    public Theme select(long id) {
        Cursor c = mDB.rawQuery(
                "Select " + THEME_KEY + ", "
                        + THEME_NAME + ", "
                        + THEME_IMG + ", "
                        + THEME_DESCRIPTION + " from "
                        + THEME_TABLE_NAME + " where "
                        + THEME_KEY + " = ?", new String[]{String.valueOf(id)});

        Theme t = null;
        if (c != null)
        {
            while (c.moveToNext()) {
                t = new Theme(c.getString(1), c.getLong(0), c.getInt(2), c.getString(3));
                break;
            }
            c.close();
        }

        if (t == null) {
            return null;
        }
        return t;
    }

    public Theme selectThemeByName(String name) {
        Cursor c = mDB.rawQuery(
                "Select " + THEME_KEY + ", "
                        + THEME_NAME + ", "
                        + THEME_IMG + ", "
                        + THEME_DESCRIPTION + " from "
                        + THEME_TABLE_NAME + " where "
                        + THEME_NAME + " = ?", new String[]{String.valueOf(name)});

        Theme t = null;
        if (c != null)
        {
            while (c.moveToNext()) {
                t = new Theme(c.getString(1), c.getLong(0), c.getInt(2), c.getString(3));
                break;
            }
            c.close();
        }

        if (t == null) {
            return null;
        }
        return t;
    }

    public ArrayList<Theme> selectAll() {
        Cursor c = mDB.rawQuery(
                "Select " + THEME_KEY + ", "
                        + THEME_NAME + ", "
                        + THEME_IMG + ", "
                        + THEME_DESCRIPTION + " from "
                        + THEME_TABLE_NAME, null);

        ArrayList<Theme> themelist = null;
        if (c != null)
        {
            while (c.moveToNext()) {
                if (themelist == null) { themelist = new ArrayList<>(); }

                Theme i = new Theme(c.getString(1), c.getLong(0), c.getInt(2), c.getString(3));
                themelist.add(i);
            }
            c.close();
        }

        if (themelist == null) {
            return null;
        }

        return themelist;
    }
}
