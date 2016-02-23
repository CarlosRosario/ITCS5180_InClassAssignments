package com.meredithbrowne.inclass6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by meredithbrowne on 2/22/16.
 */
public class GetThumbnailAsyncTask extends AsyncTask<String, Void, Bitmap> {
    IGetThumbnail activity;

    public GetThumbnailAsyncTask(IGetThumbnail activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.showProcessing();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        activity.finishProcessing();
        activity.setThumbnail(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface IGetThumbnail {
        void setThumbnail(Bitmap bitmap);
        void showProcessing();
        void finishProcessing();
    }

}
