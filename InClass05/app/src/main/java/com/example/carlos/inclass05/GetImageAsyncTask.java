package com.example.carlos.inclass05;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Carlos on 2/15/2016.
 */
public class GetImageAsyncTask extends AsyncTask<String, Void, Bitmap> {


    IGetImage activity;

    public GetImageAsyncTask(IGetImage activity){
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(Bitmap b) {
        super.onPostExecute(b);
        if(b != null){
            activity.setImage(b);
        }
        activity.finishProcessing();

    }

    @Override
    protected Bitmap doInBackground(String... params) {


        try {

            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            Bitmap image = BitmapFactory.decodeStream(connection.getInputStream()); // use this code to return an image
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.showProcessing();
    }

    public interface IGetImage{
        void setImage(Bitmap b);
        void showProcessing();
        void finishProcessing();
    }
}
