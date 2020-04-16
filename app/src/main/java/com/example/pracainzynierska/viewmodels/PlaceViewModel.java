package com.example.pracainzynierska.viewmodels;

import android.app.Application;

import com.example.pracainzynierska.data.Place;
import com.example.pracainzynierska.data.PlaceRepository;


import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PlaceViewModel extends AndroidViewModel {
    private PlaceRepository mRepository;
    private LiveData <List<Place>> mAllPlace;
    public PlaceViewModel(Application application) {
        super(application);
        mRepository = new PlaceRepository(application);
        mAllPlace = mRepository.getPlaceAll();
    }

   public LiveData<List<Place>> getPlaceAll() {return mAllPlace;}

    public void insert(Place place) {
        mRepository.insert(place);}
}
