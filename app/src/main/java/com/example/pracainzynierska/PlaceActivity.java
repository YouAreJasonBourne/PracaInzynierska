package com.example.pracainzynierska;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pracainzynierska.adapters.PlaceAdapter;
import com.example.pracainzynierska.data.Place;
import com.example.pracainzynierska.viewmodels.PlaceViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceActivity extends AppCompatActivity {

    final private String CHURCH = "Obiekt Sakralny";
    final private String MUSEUM = "Muzeum";
    final private String PALACE = "Pałac";
    final private String LAKE = "Jezioro";
    final private String HISTORY_PLACE = "Obiekt Historyczny";
    final private String PARK = "Park";
    final private String ALL_PLACE = "Wszystkie miejsca";

    private RecyclerView recyclerView;
    private PlaceViewModel viewModel;
    private Intent intent;
    private TextView textView;
    private Map<String,Runnable> placeTypeAndFunction;
    private PlaceAdapter placeAdapter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        intent = getIntent();
        recyclerView = findViewById(R.id.recyclerView);
        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        placeAdapter = new PlaceAdapter(this);
        textView = findViewById(R.id.empty_view);
        String place1 = intent.getStringExtra("place");
        String place = intent.getStringExtra("name");

        placeTypeAndFunction = new HashMap<String, Runnable>();
        placeTypeAndFunction.put(MUSEUM,this::museumList);
        placeTypeAndFunction.put(CHURCH,this::churchList);
        placeTypeAndFunction.put(PARK,this::parkList);
        placeTypeAndFunction.put(PALACE,this::palaceList);
        placeTypeAndFunction.put(HISTORY_PLACE,this::historyPlaceList);
        placeTypeAndFunction.put(LAKE,this::lakeList);
        placeTypeAndFunction.put(ALL_PLACE,this::allPlaceList);

        if(place != null && place1 == null ) {
            placeTypeAndFunction.get(place).run();
        }
        else {
            allPlaceList();
        }
        recyclerView.setAdapter(placeAdapter);
        if (placeAdapter.getItemCount() == 0){
            textView.setVisibility(View.VISIBLE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void museumList()
    {
        viewModel.getAllMuseum().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setWords(places);
            }
        });
    }
    public void churchList()
    {
        viewModel.getAllChurch().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setWords(places);
            }
        });
    }
    public void lakeList()
    {
        viewModel.getAllLake().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setWords(places);
            }
        });
    }
    public void palaceList()
    {
        viewModel.getAllPalace().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setWords(places);
            }
        });
    }
    public void parkList()
    {
        viewModel.getAllPark().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setWords(places);
            }
        });
    }
    public void historyPlaceList()
    {
        viewModel.getAllHistoryPlace().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setWords(places);
            }
        });
    }
    public void allPlaceList()
    {
        viewModel.getPlaceAll().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setWords(places);
            }
        });
    }
}
