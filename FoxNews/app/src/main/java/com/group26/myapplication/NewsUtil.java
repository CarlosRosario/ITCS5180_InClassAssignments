package com.group26.myapplication;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by crosario on 2/22/2016.
 */
public class NewsUtil {


    static public class NewsPullParser {

        private static boolean isItemTag = false;

        static ArrayList<News> parseNews(InputStream in) throws IOException, XmlPullParserException {


            // For debugging purposes
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//            StringBuilder sb = new StringBuilder();
//            String line = bufferedReader.readLine();
//
//            while(line != null){
//                sb.append(line);
//                line = bufferedReader.readLine();
//            }
//
//            Log.d("demo", sb.toString());

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            News news = null;
            ArrayList<News> newsList = new ArrayList<News>();

            int event = parser.getEventType();

            while(event != XmlPullParser.END_DOCUMENT){

                switch(event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("item")){
                            isItemTag = true;
                            news = new News();
                        } else if(parser.getName().equals("link")){
                            if(isItemTag){
                                String text = parser.nextText();
                                Log.d("demo", "link:" + text);
                                news.setNewsItemUrl(text);
                            }
                        } else if(parser.getName().equals("media:thumbnail")){
                            if(isItemTag){
                               // Log.d("demo", parser.nextText());
                                String text = parser.getAttributeValue(null,"url");
                                Log.d("demo", "url" + text);
                                news.setNewsImageUrl(text);
                            }
                        } else if(parser.getName().equals("title")){
                            if(isItemTag){
                                String text = parser.nextText();
                                Log.d("demo", "title" +  text);
                                news.setNewsTitle(text);
                            }
                        } else if(parser.getName().equals("description")){
                            if(isItemTag){
                                String text = parser.nextText();
                                Log.d("demo", "description" + text);
                                news.setNewsDescription(text);
                            }
                        } else if(parser.getName().equals("pubDate")){
                            if(isItemTag){
                                String text = parser.nextText();
                                Log.d("demo", "publication date" + text);
                                news.setPublicationDate(text);
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            isItemTag = false;
                            newsList.add(news);
                            news = null;

                        }
                        break;
                }
                event = parser.next();
            }
            return newsList;
        }
    }
}
