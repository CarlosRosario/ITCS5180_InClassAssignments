package com.preetiharkanth.listview.inclass07.group26.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by crosari1 on 3/14/2016.
 */
public class StoryDAO {
    private SQLiteDatabase db;

    public StoryDAO(SQLiteDatabase db){
        this.db = db;
    }

    public long save(StoryDBObject story){
        ContentValues values = new ContentValues();
        values.put(StoriesTable.COLUMN_STORY_TITLE, story.getStoryTitle());
        values.put(StoriesTable.COLUMN_STORY_BYLINE, story.getStoryByline());
        values.put(StoriesTable.COLUMN_STORY_ABSTRACT, story.getStoryAbstract());
        values.put(StoriesTable.COLUMN_CREATE_DATE, story.getCreateDate());
        values.put(StoriesTable.COLUMN_THUMB_URL, story.getThumbUrl());
        values.put(StoriesTable.COLUMN_NORMAL_URL, story.getNormalUrl());
        return db.insert(StoriesTable.TABLENAME, null, values);
    }

    public boolean update(StoryDBObject story){
        ContentValues values = new ContentValues();
        values.put(StoriesTable.COLUMN_STORY_TITLE, story.getStoryTitle());
        values.put(StoriesTable.COLUMN_STORY_BYLINE, story.getStoryByline());
        values.put(StoriesTable.COLUMN_STORY_ABSTRACT, story.getStoryAbstract());
        values.put(StoriesTable.COLUMN_CREATE_DATE, story.getCreateDate());
        values.put(StoriesTable.COLUMN_THUMB_URL, story.getThumbUrl());
        values.put(StoriesTable.COLUMN_NORMAL_URL, story.getNormalUrl());
        return db.update(StoriesTable.TABLENAME, values, StoriesTable.COLUMN_ID + "=?", new String[]{story.get_id() + ""}) > 0;
    }

    public boolean delete(StoryDBObject story){
        return db.delete(StoriesTable.TABLENAME, StoriesTable.COLUMN_ID + "=?", new String[]{story.get_id() + ""}) > 0;
    }

    public StoryDBObject getStoryDBObjectByTitle(String title){

        StoryDBObject storyDBObject = null;
        Cursor c = db.query(true, StoriesTable.TABLENAME, new String[]{StoriesTable.COLUMN_ID, StoriesTable.COLUMN_STORY_TITLE, StoriesTable.COLUMN_STORY_BYLINE, StoriesTable.COLUMN_STORY_ABSTRACT, StoriesTable.COLUMN_CREATE_DATE, StoriesTable.COLUMN_CREATE_DATE, StoriesTable.COLUMN_THUMB_URL, StoriesTable.COLUMN_NORMAL_URL}, StoriesTable.COLUMN_STORY_TITLE + "=?", new String[]{title + ""}, null, null, null, null, null);


        if(c != null && c.moveToFirst()){
            storyDBObject = buildNoteFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }
        }
        return storyDBObject;
    }

    public List<StoryDBObject> getAllStories(){
        List<StoryDBObject> stories = null;
        Cursor c = db.query(true, StoriesTable.TABLENAME, new String[]{StoriesTable.COLUMN_ID, StoriesTable.COLUMN_STORY_TITLE, StoriesTable.COLUMN_STORY_BYLINE, StoriesTable.COLUMN_STORY_ABSTRACT, StoriesTable.COLUMN_CREATE_DATE, StoriesTable.COLUMN_CREATE_DATE, StoriesTable.COLUMN_THUMB_URL, StoriesTable.COLUMN_NORMAL_URL}, StoriesTable.COLUMN_ID + "=?", null, null, null, null, null);


        if(c != null && c.moveToFirst()){
            do {
                StoryDBObject storyDBObject = buildNoteFromCursor(c);
                if(storyDBObject != null){
                    stories.add(storyDBObject);
                }
            } while(c.moveToNext());

            if(!c.isClosed()){
                c.close();
            }
        }
        return stories;
    }

    private StoryDBObject buildNoteFromCursor(Cursor c){
        StoryDBObject storyDBObject = null;
        if(c != null){
            storyDBObject = new StoryDBObject();
            storyDBObject.set_id(c.getLong(0));
            storyDBObject.setStoryTitle(c.getString(1));
            storyDBObject.setStoryByline(c.getString(2));
            storyDBObject.setStoryAbstract(c.getString(3));
            storyDBObject.setCreateDate(c.getString(4));
            storyDBObject.setThumbUrl(c.getString(5));
            storyDBObject.setNormalUrl(c.getString(6));
        }
        return storyDBObject;
    }
}
