package com.preetiharkanth.listview.inclass07.group26.myapplication;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity implements GetImageAsyncTask.IGetImage{

    TextView story_title;
    TextView story_byline;
    ImageView story_image;
    TextView story_abstract;
    Story story;
    ImageView bookmarkIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        story_title = (TextView) findViewById(R.id.textViewStoryTitle);
        story_byline = (TextView) findViewById(R.id.textViewStoryByline);
        story_image = (ImageView) findViewById(R.id.imageViewStoryImage);
        story_abstract = (TextView) findViewById(R.id.textViewStoryAbstract);
        bookmarkIcon = (ImageView) findViewById(R.id.bookmarkIcon);

        if(getIntent() != null && getIntent().getExtras() != null){
            story =(Story) getIntent().getExtras().getSerializable("STORY");
            if(!(story.getNormalUrl().equals("null normalUrl"))){
                new GetImageAsyncTask(DetailsActivity.this).execute(story.getNormalUrl());
            }

            story_title.setText(story.getTitle());
            story_byline.setText(story.getByline());
            story_abstract.setText(story.getAbstract_text());

            DatabaseDataManager dm = new DatabaseDataManager(DetailsActivity.this);
            if(dm.getStoryByTitle(story.getTitle()) != null){
                bookmarkIcon.setImageResource(R.drawable.bookmark_filled);
            }
            else {
                bookmarkIcon.setImageResource(R.drawable.bookmark_empty);
            }
        }


        //new GetImageAsyncTask(DetailsActivity.this).execute("https://www.planwallpaper.com/static/images/Winter-Tiger-Wild-Cat-Images.jpg");

    }

    @Override
    public void setImage(Bitmap b) {
        if (b != null) {
            story_image.setImageBitmap(b);
        } else {
            story_image.setImageResource(R.drawable.photo_not_found);
        }
    }

    @Override
    public void showProcessing() {
        Log.d("details activity, ", "image processing...");
    }

    @Override
    public void finishProcessing() {
        Log.d("details activity, ", "image finished processing");
    }
}
