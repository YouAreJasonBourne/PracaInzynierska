package com.example.pracainzynierska;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.appcompat.app.AppCompatActivity;

public class MapsAddMarkerPlaceActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final String EXTRA_LAT = "com.example.android.pracainzynierska.LAT";
    public static final String EXTRA_LNG = "com.example.android.pracainzynierska.LNG";
    private GoogleMap map;
    private  LatLng placePoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_add_marker_place);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapAddMarker);
        mapFragment.getMapAsync(this);    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                map.clear();
                map.addMarker(new MarkerOptions().position(point));
                placePoint = point;
                }
        });
    }
    public void sendLocation(View view){
        Intent replyIntent = new Intent();
        replyIntent.putExtra("Latitude",placePoint.latitude);
        replyIntent.putExtra("Longitude",placePoint.longitude);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
