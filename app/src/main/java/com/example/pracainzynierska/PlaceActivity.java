package com.example.pracainzynierska;

import android.content.Intent;
import android.os.Bundle;

import com.example.pracainzynierska.adapters.PlaceAdapter;
import com.example.pracainzynierska.data.Place;
import com.example.pracainzynierska.viewmodels.PlaceViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlaceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        getSupportActionBar().setTitle("Muzea");
        recyclerView = findViewById(R.id.recyclerView);
        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        PlaceAdapter placeAdapter = new PlaceAdapter(this);
        viewModel.getPlaceAll().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setWords(places);
            }
        });

        recyclerView.setAdapter(placeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent= getIntent();
        String placeName = intent.getStringExtra(AddPlaceActivity.EXTRA_PLACE_NAME);
        String placeDescription = intent.getStringExtra(AddPlaceActivity.EXTRA_PLACE_DESCRIPTION);
        String placeImg = intent.getStringExtra(AddPlaceActivity.EXTRA_PLACE_IMAGE);
        String placeType = intent.getStringExtra(AddPlaceActivity.EXTRA_PLACE_TYPE);
        Double longitude = intent.getDoubleExtra(AddPlaceActivity.EXTRA_PLACE_LONGTITUDE,0.0);
        Double latitude = intent.getDoubleExtra(AddPlaceActivity.EXTRA_PLACE_LATITUDE,0.0);

        Place place = new Place(placeName,placeDescription,placeImg,placeType,longitude, latitude);

        viewModel.insert(place);
    }


}
