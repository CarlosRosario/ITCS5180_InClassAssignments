package com.midterm.crosario.midterm;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by student on 3/21/16.
 */
public class GetVenuesAsyncTask extends AsyncTask<String, Void, List<Venue>> {


    IGetVenueData activity;

    public GetVenuesAsyncTask(IGetVenueData activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.showProcessing();

    }

    @Override
    protected void onPostExecute(List<Venue> venues) {
        super.onPostExecute(venues);
        activity.setVenueData(venues);
        activity.finishProcessing();
    }

    @Override
    protected List<Venue> doInBackground(String... params) {

        try {

            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int statusCode = connection.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();

                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }

                String JSONResponse = sb.toString();
                Log.d("demo", JSONResponse);

                return VenueJSONParser.parseVenues(JSONResponse);

            }


        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }



        return null;

    }

    public interface IGetVenueData {
        public void setVenueData(List<Venue> venues);
        public void showProcessing();
        public void finishProcessing();
    }

}
