package com.preetiharkanth.listview.inclass07.group26.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TopStoriesActivity extends AppCompatActivity implements  GetTopStoryDataAsyncTask.IGetTopStoryData{

    List<Story> stories = new ArrayList<Story>();
    List<Story> bookMarkedStories = new ArrayList<Story>();
    String category;
    String baseUrl = "http://api.nytimes.com/svc/topstories/v1/";
    ProgressDialog progressDialog;
    ListView listView;
    boolean showingBookmarksOnly = false;
    DatabaseDataManager dm;
    StoryAdaptor storyAdaptor;

    @Override
    protected void onDestroy() {
        dm.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);

        dm = new DatabaseDataManager(TopStoriesActivity.this);

        if(getIntent() != null && getIntent().getExtras() != null){
            category = getIntent().getExtras().getString("CATEGORY");

            String url = baseUrl + category + ".json?api-key=4ec26f049ce3e63cf7f39bec8bc4a193:5:74582549";
            new GetTopStoryDataAsyncTask(TopStoriesActivity.this).execute(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.book_mark_action_bar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //showPopup(findViewById(R.id.testlayout));
        return false;
    }

    public void showBookmarks(MenuItem item) {
        showingBookmarksOnly = true;

        for(Story s: stories){
            if(s.isBookMarked()){
                bookMarkedStories.add(s);
            }
        }
        storyAdaptor = new StoryAdaptor(this,R.layout.row_item_layout,bookMarkedStories);
        listView  = (ListView) findViewById(R.id.topStoryListView);
        listView.setAdapter(storyAdaptor);
        storyAdaptor.setNotifyOnChange(true);
    }

    public void clearBookmarks(MenuItem item) {
        showingBookmarksOnly = false;

        for(Story s: stories){
            if(dm.getStoryByTitle(s.getTitle()) != null){
                dm.deleteStory(dm.getStoryByTitle(s.getTitle()));
                s.setIsBookMarked(false);
            }
        }
        // clear bookmarked stories list so that clicking showBookMarks shows up empty.
        bookMarkedStories.clear();

        storyAdaptor = new StoryAdaptor(this, R.layout.row_item_layout, stories);

        listView  = (ListView) findViewById(R.id.topStoryListView);
        listView.setAdapter(storyAdaptor);

        storyAdaptor.setNotifyOnChange(true);
        //Toast.makeText(this, "Clear All Bookmarks", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setStoryData(final List<Story> stories) {
        this.stories = stories;

        storyAdaptor = new StoryAdaptor(this,R.layout.row_item_layout,stories);
        listView  = (ListView) findViewById(R.id.topStoryListView);
        listView.setAdapter(storyAdaptor);
        storyAdaptor.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopStoriesActivity.this, DetailsActivity.class);
                intent.putExtra("STORY", stories.get(position));
                startActivity(intent);
                // Log.d("demo", "position is " + position + ", Value is " + stories.get(position).toString());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Story story = stories.get(position);
                ImageView bookmarkimage = (ImageView)view.findViewById(R.id.bookMarkView);


                if(showingBookmarksOnly){
                    Story bookmarkedStory = bookMarkedStories.get(position);
                    if(dm.getStoryByTitle(bookmarkedStory.getTitle()) != null){
                        dm.deleteStory(dm.getStoryByTitle(bookmarkedStory.getTitle()));
                        bookmarkimage.setImageResource(R.drawable.bookmark_empty);
                        storyAdaptor.remove(bookmarkedStory);
                    }
                }else {
                    if(dm.getStoryByTitle(story.getTitle()) != null){
                        // this means the story did exist in the database
                        dm.deleteStory(dm.getStoryByTitle(story.getTitle()));
                        //bookmarkimage.setBackgroundResource(R.drawable.bookmark_empty);
                        bookmarkimage.setImageResource(R.drawable.bookmark_empty);
                        story.setIsBookMarked(false);
                        Toast.makeText(TopStoriesActivity.this, "Successfully deleted story", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // this means the story did NOT exist in the database

                        StoryDBObject storyDBObject = new StoryDBObject();
                        storyDBObject.setCreateDate(story.getCreated_date());
                        storyDBObject.setNormalUrl(story.getNormalUrl());
                        storyDBObject.setStoryAbstract(story.getAbstract_text());
                        storyDBObject.setStoryByline(story.getByline());
                        storyDBObject.setStoryTitle(story.getTitle());
                        storyDBObject.setThumbUrl(story.getThumbUrl());
                        //bookmarkimage.setBackgroundResource(R.drawable.bookmark_filled);
                        bookmarkimage.setImageResource(R.drawable.bookmark_filled);

                        dm.saveStory(storyDBObject);
                        story.setIsBookMarked(true);
                        Toast.makeText(TopStoriesActivity.this, "Successfully added story", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });

    }

    @Override
    public void startProcessing() {
        progressDialog = new ProgressDialog(TopStoriesActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading Stories..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void finishProcessing() {
        progressDialog.dismiss();
    }
}
