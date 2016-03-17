package com.preetiharkanth.listview.inclass07.group26.myapplication;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by crosari1 on 3/14/2016.
 */
public class StoriesTable {

    static final String TABLENAME = "stories";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_STORY_TITLE = "title";
    static final String COLUMN_STORY_BYLINE = "byline";
    static final String COLUMN_STORY_ABSTRACT = "abstract";
    static final String COLUMN_CREATE_DATE = "create_date";
    static final String COLUMN_THUMB_URL = "thumb_url";
    static final String COLUMN_NORMAL_URL = "normal_url";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + " (");
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_STORY_TITLE + " text, ");
        sb.append(COLUMN_STORY_BYLINE + " text, ");
        sb.append(COLUMN_STORY_ABSTRACT + " text, ");
        sb.append(COLUMN_CREATE_DATE + " text, ");
        sb.append(COLUMN_THUMB_URL + " text, ");
        sb.append(COLUMN_NORMAL_URL + " text);");

        String createString = sb.toString();
        Log.d("test3", createString);
        try {
            db.execSQL(createString);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    static public void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion){
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
            StoriesTable.onCreate(db);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

}
