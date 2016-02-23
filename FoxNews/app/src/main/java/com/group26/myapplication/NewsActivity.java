package com.group26.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements GetNewsAsyncTask.IGetNews{

    String url = "";
    List<News> newsList = new ArrayList<News>();
    List<String> storyTitles = new ArrayList<String>();
    LinearLayout linearLayout;
    TextView textView;
    ProgressDialog progressDialog;

    @Override
    public void showProcessing() {
        progressDialog = new ProgressDialog(NewsActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading News..");
        progressDialog.show();
    }

    @Override
    public void finishProcessing() {
        progressDialog.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if(getIntent().getExtras() != null){
            url = getIntent().getExtras().getString("URL");
            new GetNewsAsyncTask(this).execute(url);



            linearLayout = (LinearLayout)findViewById(R.id.newTitleLinearLayout);




        }


    }

    @Override
    public void setNews(List<News> list) {
        Log.d("demo", "populating news list");
        this.newsList = list;

        for(final News n: newsList){
            Log.d("title", "grabbed: " + n.getNewsTitle());
            textView = new TextView(NewsActivity.this);


            textView.setText(n.getNewsTitle()+ "\n");
            textView.setGravity(Gravity.LEFT);
            textView.setClickable(true);
            //textView.setTag(n.getNewsTitle());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newsDetailsIntent = new Intent(NewsActivity.this, NewsDetailsActivity.class);
                    newsDetailsIntent.putExtra("NEWS", n);
                    startActivity(newsDetailsIntent);
                }
            });
            textView.setTypeface(null, Typeface.BOLD);
            textView.setTextColor(Color.BLACK);

            textView.setTextSize(16);
            linearLayout.addView(textView);

        }
    }
}
