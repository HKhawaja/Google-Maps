package com.example.google_maps;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

//import com.example.android.common.activities.SampleActivityBase;
//import com.example.android.common.logger.Log;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity implements PlaceSelectionListener {

    private TextView mPlaceDetailsText;
    private TextView mPlaceAttribution;
    Place selectedPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Retrieve the PlaceAutocompleteFragment.
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.
        autocompleteFragment.setOnPlaceSelectedListener(this);

        // Retrieve the TextViews that will display details about the selected place.
        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);
   }

    public void submitLocation(View v) {
        //can retrieve selectedPlaces as the place our user selected
        System.out.println ("selectedPlace " + selectedPlace);

        //get coordinates
        LatLng coordinates = selectedPlace.getLatLng();

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("coordinates", coordinates);
        startActivity(intent);
    }


    //callback when a location has been selected
    @Override
    public void onPlaceSelected(Place place) {
        //System.out.println (place);
        selectedPlace = place;

        mPlaceDetailsText.setText(place.getName());
        System.out.println(place.getLatLng());

        //sends intent straight to MapsActivity once location has been selected
//        Intent sendDestinationToMap = new Intent(this,MapsActivity.class);
//        startActivity(sendDestinationToMap);

    }

    @Override
    public void onError(Status status) {
        Log.e("Error!", "onError: Status = " + status.toString());

        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }

}
