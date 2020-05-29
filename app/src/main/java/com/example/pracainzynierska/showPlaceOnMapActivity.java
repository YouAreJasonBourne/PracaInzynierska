package com.example.pracainzynierska;

import android.os.Bundle;

import com.example.pracainzynierska.viewmodels.PlaceViewModel;
import com.example.pracainzynierska.viewmodels.PlaceViewModelFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class showPlaceOnMapActivity extends AppCompatActivity implements OnMapReadyCallback {
private GoogleMap map;
private PlaceViewModel placeViewModel;
private PlaceViewModelFactory placeViewModelFactory;
private List<Double> lat;
private List<Double> lng;
private Double[] latArray;
private Double[] lngArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_place_on_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.showMarkerPlace);
        mapFragment.getMapAsync(this);
        placeViewModelFactory = new PlaceViewModelFactory(getApplication());
        placeViewModel = new ViewModelProvider(this,placeViewModelFactory).get(PlaceViewModel.class);
        lat = placeViewModel.getmAllLatitude();
        lng = placeViewModel.getmAllLongitude();
        latArray = lat.toArray(new Double[1000]);
        lngArray = lng.toArray(new Double[1000]);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        for(int i = 0 ; i < lat.size() ; i++) {

            createMarker(latArray[i],lngArray[i]);
        }
        map.addMarker(new MarkerOptions()
                .position(new LatLng(10, 10))
                .title("Hello world"));
    }
    protected Marker createMarker(double latitude, double longitude) {

        return map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f));

    }
}
