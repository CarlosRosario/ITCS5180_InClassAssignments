package com.group26.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by meredithbrowne on 2/22/16.
 */
public class NewsWebViewActivity extends AppCompatActivity {
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web_view);

        if(getIntent().getExtras() != null){
            url = getIntent().getExtras().getString("URL");
        }

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        // load from a URL
        if (url != null && !url.isEmpty()) {
            webView.loadUrl(url);
        }


        // load from an HTML string
        //String summary = "<html><body></body></html>";
        //webView.loadData(summary, "text/html", null);
    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}

