package com.midterm.crosario.midterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String selectedLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // client id: OJAOBG4ZMJRPFTIM5EQG1Z122OOHTYCFGFE0XF5I4A2WP5PX
        // client secret: Q0GWHQEXIPQPAX1RLSKAXTCMW123K40SU5DTMY4GJAYDSJLJ

        //https://api.foursquare.com/v2/venues/search?client_id=OJAOBG4ZMJRPFTIM5EQG1Z122OOHTYCFGFE0XF5I4A2WP5PX&client_secret=Q0GWHQEXIPQPAX1RLSKAXTCMW123K40SU5DTMY4GJAYDSJLJ&v=20160320&near=Charlotte,NC

        //https://api.foursquare.com/v2/venues/search?client_id=OJAOBG4ZMJRPFTIM5EQG1Z122OOHTYCFGFE0XF5I4A2WP5PX&client_secret=Q0GWHQEXIPQPAX1RLSKAXTCMW123K40SU5DTMY4GJAYDSJLJ&v=20160320&near=Charlotte,NC
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.main_activity_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocation = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button submitButton = (Button)findViewById(R.id.main_activity_submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedLocation.isEmpty()){
                    Intent venuesActivityIntent = new Intent(MainActivity.this, VenuesActivity.class);
                    venuesActivityIntent.putExtra(Constants.SELECTEDLOCATION, selectedLocation);
                    startActivity(venuesActivityIntent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please select a location", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
