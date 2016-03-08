package com.group26.myapplication;


import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crosario on 2/22/2016.
 */
public class GetNewsAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {

    IGetNews activity;

    public GetNewsAsyncTask(IGetNews activity){
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(ArrayList<News> newses) {

        // For debugging purposes
//        if(newses != null){
//            for(News n: newses){
//                Log.d("demo", n.getNewsDescription());
//                Log.d("demo", n.getNewsImageUrl());
//                Log.d("demo", n.getNewsItemUrl());
//                Log.d("demo", n.getNewsTitle());
//                Log.d("demo", n.getPublicationDate());
//            }
//        }

        super.onPostExecute(newses);
        activity.setNews(newses);
        activity.finishProcessing();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.showProcessing();
    }

    @Override
    protected ArrayList<News> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int statusCode = con.getResponseCode();

            if(statusCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = con.getInputStream();
                Log.d("demo", "returning input stream");
                return NewsUtil.NewsPullParser.parseNews(inputStream);
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (XmlPullParserException e){
            e.printStackTrace();
        }

        return null;
    }

    public interface IGetNews{
        void setNews(List<News> list);
        void showProcessing();
        void finishProcessing();

    }
}
