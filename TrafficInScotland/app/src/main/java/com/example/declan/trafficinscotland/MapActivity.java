package com.example.declan.trafficinscotland;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 Declan Gibb - S1345890.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{
    public String GeoRssText = "";
    public Double GeoLat = 0.0, GeoLng = 0.0;
    public String Marker_Title = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);


        Intent intent = getIntent();
        GeoRssText = intent.getStringExtra(CurrentIncidentsActivity.Geo_Position);
        Marker_Title = intent.getStringExtra(CurrentIncidentsActivity.Marker_Name);
        ParseGeoRSS(GeoRssText);
    }

    void ParseGeoRSS(String georss)
    {
        int pos = georss.indexOf(" ");
        String latstr = georss.substring(0, pos-1);
        String lngstr = georss.substring(pos+1);
        GeoLat = Double.parseDouble(latstr);
        GeoLng = Double.parseDouble(lngstr);
    }
    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng location = new LatLng(GeoLat,GeoLng);
        googleMap.addMarker(new MarkerOptions().position(location)
                .title(Marker_Title));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,14));
        googleMap.getUiSettings().setZoomControlsEnabled(true);

    }


}
