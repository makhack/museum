package com.example.matthieu.sidenav;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Amine on 14-Jan-16.
 */
public class BaseDAO {
    // Ne pas oublier d'incrémenter cette variable
    // à chaque mise à jour de la base de données.
    protected final static int VERSION = 1;
    // Nom du fichier de base de données.
    protected final static String DBNAME = "museum.db";

    protected SQLiteDatabase mDB = null;
    protected DatabaseHandler mHandler = null;

    public BaseDAO(Context context) {
        this.mHandler = new DatabaseHandler(context, DBNAME, null, VERSION);
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base
        // car getWritableDatabase s'en charge.
        mDB = mHandler.getWritableDatabase();
        return mDB;
    }

    public void Close() {
        mDB.close();
    }

    public SQLiteDatabase getDb() {
        return mDB;
    }
}
