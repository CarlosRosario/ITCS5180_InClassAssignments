package com.meredithbrowne.inclass6;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        thumbnail = (ImageView) findViewById(R.id.details_thumbnail);
        TextView title = (TextView) findViewById(R.id.details_title);
        TextView pubdate = (TextView) findViewById(R.id.details_pubdate);
        TextView description = (TextView) findViewById(R.id.details_desc_body);
        new GetThumbnailAsyncTask(NewsDetailsActivity.this).execute("http://tools.foxnews.com/sites/tools.foxnews.com/files/images/fox-news-logo.png");
        title.setText("Story Title");
        pubdate.setText("Story Pub Date");
        description.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque ut convallis erat. Quisque ullamcorper nibh at est pharetra, ut placerat risus blandit. Suspendisse commodo mauris et erat dignissim fermentum. Vestibulum placerat bibendum justo ut dictum. Cras risus lacus, scelerisque ac ultricies ac, vulputate fermentum nulla. Praesent faucibus efficitur quam, eu aliquam turpis sagittis eu. Donec lacinia euismod rutrum.");

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsDetailsActivity.this, NewsWebViewActivity.class);
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
        Log.d("thumbnail", "thumbnail processing");
    }

    @Override
    public void finishProcessing() {
        Log.d("thumbnail", "thumbnail finished processing");
    }
}
