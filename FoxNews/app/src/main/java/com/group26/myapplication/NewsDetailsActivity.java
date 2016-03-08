package com.group26.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by meredithbrowne on 2/22/16.
 */
public class NewsDetailsActivity extends AppCompatActivity implements GetThumbnailAsyncTask.IGetThumbnail {
    ImageView thumbnail;
    News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        if(getIntent().getExtras() != null){
            news = (News)getIntent().getExtras().getSerializable("NEWS");
        }

        thumbnail = (ImageView) findViewById(R.id.details_thumbnail);
        TextView title = (TextView) findViewById(R.id.details_title);
        TextView pubdate = (TextView) findViewById(R.id.details_pubdate);
        TextView description = (TextView) findViewById(R.id.details_desc_body);

        if(!news.getNewsImageUrl().isEmpty()){
            new GetThumbnailAsyncTask(NewsDetailsActivity.this).execute(news.getNewsImageUrl());
        }

        title.setText(news.getNewsTitle());
        pubdate.setText(news.getPublicationDate());
        description.setText(news.getNewsDescription());

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsDetailsActivity.this, NewsWebViewActivity.class);
                intent.putExtra("URL", news.getNewsItemUrl());
                startActivity(intent);

            }
        });
    }

    @Override
    public void setThumbnail(Bitmap bitmap) {
        if (bitmap != null) {
            thumbnail.setImageBitmap(bitmap);
            Log.d("thumbnail", "setting thumbnail");
        }
    }

    @Override
    public void showProcessing() {
        //Log.d("thumbnail", "thumbnail processing");
    }

    @Override
    public void finishProcessing() {
        //Log.d("thumbnail", "thumbnail finished processing");
    }
}

