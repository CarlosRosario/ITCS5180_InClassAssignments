package com.preetiharkanth.listview.inclass07.group26.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

/**
 * Created by crosari1 on 3/14/2016.
 */
public class DatabaseDataManager {
    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private StoryDAO storyDAO;

    public DatabaseDataManager(Context mContext){
        Log.d("test3", "databasedatamanager constructor");
        this.mContext = mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        storyDAO = new StoryDAO(db);
        Log.d("test3", "data manager constructor end");
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public long saveStory(StoryDBObject story){
        return this.storyDAO.save(story);
    }

    public boolean updateStory(StoryDBObject story){
        return this.storyDAO.update(story);
    }

    public boolean deleteStory(StoryDBObject story){
        return this.storyDAO.delete(story);
    }

    public StoryDBObject getStoryByTitle(String title){
        return this.storyDAO.getStoryDBObjectByTitle(title);
    }

    public List<StoryDBObject> getAllStories(){
        return this.storyDAO.getAllStories();
    }
}
