package com.example.pracainzynierska;

import android.location.Location;
import android.os.Bundle;

import com.example.pracainzynierska.viewmodels.PlaceViewModel;
import com.example.pracainzynierska.viewmodels.PlaceViewModelFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

private List<String> placeType;
private List<String> placeName;

private Double[] latArray;
private Double[] lngArray;

private String[] placeNameArray;
private String[] placeTypeArray;

private Location currentlocation;
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
        placeName = placeViewModel.getmAllPlaceName();
        placeType = placeViewModel.getmAllPlaceType();
        latArray = lat.toArray(new Double[1000]);
        lngArray = lng.toArray(new Double[1000]);
        placeNameArray = placeName.toArray(new String[1000]);
        placeTypeArray = placeType.toArray(new String[1000]);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        for(int i = 0 ; i < lat.size() ; i++) {

            createMarker(latArray[i],lngArray[i],placeTypeArray[i],placeNameArray[i]);
        }
       map.setMyLocationEnabled(true);
    }

    protected Marker createMarker(double latitude, double longitude,String placeType, String placeName) {

        return map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(placeName)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                .snippet(placeType)
                .anchor(0.5f, 0.5f))
                ;

    }

}
