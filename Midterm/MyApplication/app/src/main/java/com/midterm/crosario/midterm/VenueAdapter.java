package com.midterm.crosario.midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by student on 3/21/16.
 */
public class VenueAdapter extends ArrayAdapter<Venue> {

    List<Venue> mData;
    Context mContext;
    int mResource;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        Venue venue = mData.get(position);

        // Set category icon
        ImageView iconImageView = (ImageView)convertView.findViewById(R.id.iconImageView);
        if(venue.getCategoryIcon() != null && !venue.getCategoryIcon().isEmpty()){
            Picasso.with(mContext).load(venue.getCategoryIcon()).into(iconImageView);
        }

        // Set venue name
        TextView venueNameTextView = (TextView)convertView.findViewById(R.id.venueNameTextView);
        venueNameTextView.setText(venue.getVenueName());

        // Set category name
        TextView categoryNameTextView = (TextView)convertView.findViewById(R.id.categoryNameTextView);
        categoryNameTextView.setText(venue.getCategoryName());

        // Set badge icon
        ImageView badgeImageView = (ImageView)convertView.findViewById(R.id.badgeImageView);

        int badgeCount = 0;
        if(venue.getCheckInCount() != null && venue.getCheckInCount() != "null"){
            badgeCount = Integer.parseInt(venue.getCheckInCount());
        }


        if(badgeCount >= 0 && badgeCount <= 100){
            Picasso.with(mContext).load(R.drawable.bronze).into(badgeImageView);
        }
        else if(badgeCount >= 101 && badgeCount <= 500){
            Picasso.with(mContext).load(R.drawable.silver).into(badgeImageView);
        }
        else if (badgeCount > 500){
            Picasso.with(mContext).load(R.drawable.gold).into(badgeImageView);
        }

        // Set checked icon to unchecked (default configuration)
        ImageView checkedImageView = (ImageView)convertView.findViewById(R.id.checkImageView);
        Picasso.with(mContext).load(R.drawable.unvisited).into(checkedImageView);

        if(venue.isChecked()){
            Picasso.with(mContext).load(R.drawable.visited).into(checkedImageView);
        }

        return convertView;

    }

    public VenueAdapter(Context context, int resource, List<Venue> objects){
        super(context, resource, objects);
        this.mData = objects;
        this.mContext = context;
        this.mResource = resource;
    }

}
