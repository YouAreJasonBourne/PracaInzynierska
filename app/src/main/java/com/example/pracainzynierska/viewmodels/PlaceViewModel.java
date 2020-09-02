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
    private List<Double> mAllLatitude;
    private List<Double> mAllLongitude;
    private List<String> mAllPlaceType;
    private List<String> mAllPlaceName;

    public PlaceViewModel(Application application) {
           super(application);
           mRepository = new PlaceRepository(application);
           mAllPlace = mRepository.getPlaceAll();
            mAllLatitude = mRepository.getMallLatitude();
            mAllLongitude = mRepository.getMallLongitude();
            mAllPlaceName = mRepository.getMallPlaceName();
            mAllPlaceType = mRepository.getMallPlaceType();
       }


       public LiveData<List<Place>> getPlaceAll() {return mAllPlace;}
       public List<Double> getmAllLatitude() {return mAllLatitude;}
       public List<Double> getmAllLongitude() {return mAllLongitude;}
       public List<String> getmAllPlaceName() {return mAllPlaceName;}
       public List<String> getmAllPlaceType() {return mAllPlaceType;}

    public void insert(Place place) {
        mRepository.insert(place);}
}
