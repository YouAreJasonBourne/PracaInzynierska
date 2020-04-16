package com.example.pracainzynierska;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pracainzynierska.adapters.PlaceAdapter;

import com.example.pracainzynierska.data.Place;
import com.example.pracainzynierska.viewmodels.PlaceViewModel;

import java.util.List;

public class PlaceActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private PlaceViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
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
    }

}
