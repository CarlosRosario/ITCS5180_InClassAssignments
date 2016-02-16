package com.example.carlos.inclass05;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements GetImageAsyncTask.IGetImage {

    Map<String, List<String>> downloadedContents = new HashMap<String, List<String>>();
    ImageView mainImageView, previousPhotoImageView, nextPhotoImageView;
    ProgressDialog progressDialog;
    int listindex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImageView = (ImageView)findViewById(R.id.mainImageView);
        previousPhotoImageView = (ImageView)findViewById(R.id.previousPhotoImageView);
        nextPhotoImageView = (ImageView)findViewById(R.id.nextPhotoImageView);

        previousPhotoImageView.setEnabled(false);
        nextPhotoImageView.setEnabled(false);

        if(isConnectedOnline()){
            new DownloadDictionaryContents().execute("http://dev.theappsdr.com/apis/spring_2016/inclass5/URLs.txt");
        }
        else {
            Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
        }


        final EditText keywordSearchEditText = (EditText)findViewById(R.id.keywordEditText);

        // Set up click listener for go button
        findViewById(R.id.goButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = keywordSearchEditText.getText().toString();

                if (!keyword.equals("UNCC") && !keyword.equals("Android") && !keyword.equals("winter") && !keyword.equals("aurora") && !keyword.equals("wonders")) {
                    Toast.makeText(MainActivity.this, "No Images Found for keyword: " + keyword, Toast.LENGTH_LONG).show();
                } else {
                    if (isConnectedOnline()) {
                        // Perform logic
                        List<String> urlsForThisKeyword = downloadedContents.get(keyword);

//                        for(String s: urlsForThisKeyword){
//                            Log.d("demo", s);
//                        }

                        listindex = 0;
                        new GetImageAsyncTask(MainActivity.this).execute(urlsForThisKeyword.get(listindex));

                        if (urlsForThisKeyword.size() > 1) {
                            previousPhotoImageView.setClickable(true);
                            previousPhotoImageView.setEnabled(true);
                            nextPhotoImageView.setClickable(true);
                            nextPhotoImageView.setEnabled(true);
                        }


                    } else {
                        Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Previous photo button
        previousPhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("demo", "previous clicked");
                String keyword = keywordSearchEditText.getText().toString();
                List<String> urls = downloadedContents.get(keyword);

//                for(String s: urls){
//                    Log.d("demo", s);
//                }

                if(urls != null && urls.size() > listindex - 1 && listindex != 0){
                    listindex--;
                    new GetImageAsyncTask(MainActivity.this).execute(urls.get(listindex));
                }
                else if(urls != null && listindex <= 0){
                    listindex = urls.size() - 1;
                    new GetImageAsyncTask(MainActivity.this).execute(urls.get(listindex));
                }

            }
        });

        // Next photo button
        nextPhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("demo", "next clicked");
                String keyword = keywordSearchEditText.getText().toString();
                List<String> urls = downloadedContents.get(keyword);

                if(urls != null && urls.size() > listindex + 1 && listindex != urls.size()-1){
                    listindex++;
                    new GetImageAsyncTask(MainActivity.this).execute(urls.get(listindex));
                }
                else if(urls != null && listindex >= urls.size()-1){
                    listindex = 0;
                    new GetImageAsyncTask(MainActivity.this).execute(urls.get(listindex));
                }
            }
        });
    }

    // Method to check if device is connected to network
    private boolean isConnectedOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // We can use networkInfo.getType() to figure out what kind of network the device is connected to (wifi, ethernet, bluetooth, etc)
        if(networkInfo != null && networkInfo.isConnected()){
            return  true;
        }
        return false;
    }

    @Override
    public void showProcessing() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading Photo..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void finishProcessing() {
        progressDialog.dismiss();
    }

    @Override
    public void setImage(Bitmap b) {
        mainImageView.setImageBitmap(b);
    }

    private class DownloadDictionaryContents extends AsyncTask<String, Void, Void>{

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Loading Dictionary..");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(String... params) {

            BufferedReader bufferedReader = null;
            try{
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line = "";
                while( (line = bufferedReader.readLine()) != null ){
                    sb.append(line);
                }

                //Log.d("test", sb.toString());
                String data = sb.toString();
                String[] dataArray = data.split(";");
                String previousKey = "";
                List<String> urls = new ArrayList<String>();
                for(String s: dataArray){
                    // Populate map data structure
                    //Log.d("test", s);
                    String[] dataComponents = s.split(",");

                    if (previousKey.equals("")) {
                        previousKey=dataComponents[0];
                    }
                    if(previousKey.equals(dataComponents[0])){
                        urls.add(dataComponents[1]);
                    }

                    if(!previousKey.equals(dataComponents[0])){
                        downloadedContents.put(previousKey, urls);
                        urls = new ArrayList<String>();
                        urls.add(dataComponents[1]);
                        previousKey = dataComponents[0];
                    }

                    if(previousKey.equals("wonders")){
                        downloadedContents.put(dataComponents[0], urls);
                    }
                }

                // Check that map is populated correctly
//                for(String key: downloadedContents.keySet()){
//                    List<String> tempList = downloadedContents.get(key);
//                    for(String tempurl: tempList){
//                        Log.d("demo", key + " " + tempurl);
//                    }
//                }
            } catch(MalformedURLException e){
                e.printStackTrace();
            } catch(IOException io){
                io.printStackTrace();
            }
            finally {
                if(bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }
}
