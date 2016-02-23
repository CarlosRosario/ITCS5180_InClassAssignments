package com.group26.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String url = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this, NewsActivity.class);



        findViewById(R.id.mostPopular).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/most-popular";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });

        findViewById(R.id.entertainment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/entertainment";
                intent.putExtra("URL", url);
                startActivity(intent);
            }
        });

        findViewById(R.id.health).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/health";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });

        findViewById(R.id.lifestyle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/section/lifestyle";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });

        findViewById(R.id.opnion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/opinion";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });

        findViewById(R.id.politics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/politics";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });

        findViewById(R.id.science).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/science";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });

        findViewById(R.id.sports).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/sports";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });

        findViewById(R.id.tech).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/tech";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });

        findViewById(R.id.travel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/internal/travel/mixed";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });

        findViewById(R.id.unitesStates).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "http://feeds.foxnews.com/foxnews/national";
                intent.putExtra("URL", url);
                startActivity(intent);

            }
        });



    }
}
