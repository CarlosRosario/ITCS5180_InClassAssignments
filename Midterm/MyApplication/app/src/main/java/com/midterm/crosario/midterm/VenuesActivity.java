package com.midterm.crosario.midterm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VenuesActivity extends AppCompatActivity implements GetVenuesAsyncTask.IGetVenueData{

    List<Venue> venuesList = new ArrayList<Venue>();
    List<Venue> markedVenuesList = new ArrayList<Venue>();
    ListView listView;
    VenueAdapter venueAdapter;
    private boolean isShowingMarked = false;

    ProgressDialog progressDialog;

    private String clientId = "OJAOBG4ZMJRPFTIM5EQG1Z122OOHTYCFGFE0XF5I4A2WP5PX";
    private String clientSecret = "Q0GWHQEXIPQPAX1RLSKAXTCMW123K40SU5DTMY4GJAYDSJLJ";
    private String location;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public void setVenueData(List<Venue> venues) {

        this.venuesList = venues;


        // Use custom adapter here
        venueAdapter = new VenueAdapter(VenuesActivity.this, R.layout.venues_row_item_layout, venuesList);
        listView = (ListView)findViewById(R.id.venueListView);
        listView.setAdapter(venueAdapter);
        venueAdapter.setNotifyOnChange(true);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Venue selectedVenue = venuesList.get(position);
                ImageView checkedImageView = (ImageView) view.findViewById(R.id.checkImageView);

                if(isShowingMarked){
                    editor.remove(selectedVenue.getVenueID());
                    venueAdapter.remove(selectedVenue);
                }
                else {
                    // If the venue ID is not in the shared preferences, save it to shared preferences and update the UI
                    // by marking the check mark to a green checkmark

                    String venueID = pref.getString(selectedVenue.getVenueID(), null);
                    if (venueID == null || venueID.isEmpty()) {
                        editor.putString("VenueID", selectedVenue.getVenueID());
                        editor.commit();
                        selectedVenue.setIsChecked(true);
                        Picasso.with(VenuesActivity.this).load(R.drawable.visited).into(checkedImageView);
                        Toast.makeText(VenuesActivity.this, "Successfully added Venue", Toast.LENGTH_SHORT).show();
                    }

                    // If the Venue ID is already in SharedPreferences, delete the Venue item from the Shared Preferences
                    // And update the UI y showing the black check mark
                    else {
                        editor.remove(selectedVenue.getVenueID());
                        editor.commit();
                        selectedVenue.setIsChecked(false);
                        Picasso.with(VenuesActivity.this).load(R.drawable.unvisited).into(checkedImageView);
                        Toast.makeText(VenuesActivity.this, "Successfully deleted Venue", Toast.LENGTH_SHORT).show();
                    }
                }



                return true;
            }
        });



    }

    @Override
    public void showProcessing() {

        progressDialog = new ProgressDialog(VenuesActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading Venues..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

    }

    @Override
    public void finishProcessing() {

        progressDialog.dismiss();

    }

    String todaysDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues);



        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor = pref.edit();

        // does this even work?
        editor.clear();
        editor.commit();
        // does this even work?

        // Generate todays date in YYYYMMDD format
        Date date = new Date();
        todaysDate = new SimpleDateFormat("yyyyMMdd").format(date);
        //Log.d("demo", "todays date in YYYYMMDD = " + todaysDate);

        if(getIntent() != null && getIntent().getExtras() != null){
            location = getIntent().getExtras().getString(Constants.SELECTEDLOCATION).replace(" ", "");
        }


        String url = Constants.FOURSQUAREENDPOINT + "client_id=" + clientId + "&" + "client_secret=" + clientSecret + "&" +"v=" + todaysDate + "&" +"near=" +  location;
        Log.d("url", url);
        new GetVenuesAsyncTask(VenuesActivity.this).execute(url);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custom_action_bar, menu);
        return true;

    }

    public void showVenue(MenuItem item){

        isShowingMarked = true;

        String venueId;
        for(Venue v: venuesList){
            if(v.isChecked()){
                markedVenuesList.add(v);
            }
        }

        venueAdapter = new VenueAdapter(this, R.layout.venues_row_item_layout, markedVenuesList);
        listView = (ListView)findViewById(R.id.venueListView);
        listView.setAdapter(venueAdapter);
        venueAdapter.setNotifyOnChange(true);






    }

    public void clearVenue(MenuItem item){

        isShowingMarked = false;

        for(Venue v: venuesList){
            editor.remove(v.getVenueID());
            v.setIsChecked(false);
        }

        markedVenuesList.clear();

        venueAdapter = new VenueAdapter(this, R.layout.venues_row_item_layout, venuesList);

        listView = (ListView)findViewById(R.id.venueListView);
        listView.setAdapter(venueAdapter);
        venueAdapter.setNotifyOnChange(true);


    }
}
