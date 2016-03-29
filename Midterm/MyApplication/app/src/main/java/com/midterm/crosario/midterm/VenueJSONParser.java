package com.midterm.crosario.midterm;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 3/21/16.
 */
public class VenueJSONParser {


    static List<Venue> parseVenues(String in) throws JSONException {

        List<Venue> venueList = new ArrayList<Venue>();

        JSONObject root = new JSONObject(in);
        JSONObject response = root.getJSONObject("response");
        JSONArray venueJSONArray = response.getJSONArray("venues");

        for(int i = 0; i < venueJSONArray.length(); i++){
            JSONObject venueJSONObject = venueJSONArray.getJSONObject(i);
            Venue venue = Venue.createVenue(venueJSONObject);
            venueList.add(venue);
        }


        // For debugging purposes
        for(Venue v: venueList){
            Log.d("venue", "Venue Id = " + v.getVenueID());
            Log.d("venue", "Venue Name = " + v.getVenueName());
            Log.d("venue", "Venue Category Name = " + v.getCategoryName());
            Log.d("venue", "Venue Category Icon = " + v.getCategoryIcon());
            Log.d("venue", "Venue Check In Count = " + v.getCheckInCount());
        }


        return venueList;
    }

}
