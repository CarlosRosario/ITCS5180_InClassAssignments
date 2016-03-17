package com.preetiharkanth.listview.inclass07.group26.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by crosari1 on 3/14/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "mystories.db";
    static final int DB_VERSION = 1;

    public DatabaseOpenHelper(Context context){

        super(context, DB_NAME, null, DB_VERSION);
        Log.d("test3", "DatabaseOpenHelper Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("test3", "onCreate called");
        StoriesTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("test3", "onUpgrade called");
        StoriesTable.onUpdate(db, oldVersion, newVersion);
    }
}
