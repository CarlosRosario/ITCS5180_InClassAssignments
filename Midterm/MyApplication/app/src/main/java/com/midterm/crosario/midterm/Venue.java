package com.midterm.crosario.midterm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by student on 3/21/16.
 */
public class Venue {

    private String venueID;
    private String venueName;
    private String categoryName;
    private String categoryIcon;
    private String checkInCount;
    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public static Venue createVenue(JSONObject json) throws JSONException {

        Venue venue = new Venue();

        try {
            venue.setVenueID(json.getString("id"));
            venue.setVenueName(json.getString("name"));

            JSONArray categories = json.getJSONArray("categories");
            JSONObject category = categories.getJSONObject(0);
            venue.setCategoryName(category.getString("name"));

            JSONObject icon = category.getJSONObject("icon");
            venue.setCategoryIcon(icon.getString("prefix") + "bg_64" + icon.getString("suffix"));

            JSONObject stats = json.getJSONObject("stats");
            venue.setCheckInCount(stats.getString("checkinsCount"));
        } catch (JSONException e){
            e.printStackTrace();
        }

        return venue;
    }


    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon.replace(" ", "");
    }

    public String getCheckInCount() {
        return checkInCount;
    }

    public void setCheckInCount(String checkInCount) {
        this.checkInCount = checkInCount;
    }
}
