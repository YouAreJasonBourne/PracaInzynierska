package com.example.pracainzynierska.data;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PlaceRepository {
    private PlaceDao mplaceDao;
    private LiveData<List<Place>> mallPlace;
    private List<Double> mallLatitude;
    private List<Double> mallLongitude;
    private List<String> mallPlaceType;
    private List<String> mallPlaceName;


    public PlaceRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mplaceDao = db.placeDao();
        mallPlace = mplaceDao.getAllPlace();
        mallLatitude = mplaceDao.getAllLatitude();
        mallLongitude = mplaceDao.getAllLongitude();
        mallPlaceName = mplaceDao.getAllPlaceName();
        mallPlaceType = mplaceDao.getAllPlaceType();
    }

    public LiveData<List<Place>> getPlaceAll(){
        return mallPlace;
    }
    public  List<Double> getMallLatitude() {return mallLatitude;}
    public List<Double> getMallLongitude() {return mallLongitude;}
    public List<String> getMallPlaceName() {return mallPlaceName;}
    public List<String> getMallPlaceType() {return mallPlaceType;}

        public void insert(Place place){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mplaceDao.insert(place);
        });
    }
}
