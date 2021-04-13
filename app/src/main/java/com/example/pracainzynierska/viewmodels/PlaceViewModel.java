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
    private LiveData <List<Place>> mAllMuseum;
    private LiveData <List<Place>> mAllPalace;
    private LiveData <List<Place>> mAllPark;
    private LiveData <List<Place>> mAllChurch;
    private LiveData <List<Place>> mAllHistoryPlace;
    private LiveData <List<Place>> mAllLake;


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
           mAllMuseum = mRepository.getMuseumAll();
           mAllChurch = mRepository.getChurchAll();
           mAllHistoryPlace = mRepository.getHistoryPlaceAll();
           mAllLake = mRepository.getLakeAll();
           mAllPalace = mRepository.getPalaceAll();
           mAllPark = mRepository.getParkAll();
}
       public LiveData<List<Place>> getPlaceAll() {return mAllPlace;}
       public LiveData<List<Place>> getAllMuseum(){ return mAllMuseum;}
       public LiveData<List<Place>> getAllPalace(){ return mAllPalace;}
       public LiveData<List<Place>> getAllPark(){ return mAllPark;}
       public LiveData<List<Place>> getAllLake(){ return mAllLake;}
       public LiveData<List<Place>> getAllChurch(){ return mAllChurch;}
       public LiveData<List<Place>> getAllHistoryPlace(){ return mAllHistoryPlace;}


       public List<Double> getmAllLatitude() {return mAllLatitude;}
       public List<Double> getmAllLongitude() {return mAllLongitude;}


       public List<String> getmAllPlaceName() {return mAllPlaceName;}
       public List<String> getmAllPlaceType() {return mAllPlaceType;}


    public void insert(Place place) {
        mRepository.insert(place);
    }

    public void delete(Place place) {
        mRepository.delete(place);
    }

    public void deleteAllPlace() {
        mRepository.deleteAllPlace();
    }

    public void update(Place place){
        mRepository.update(place);
    }
        
}
