package com.example.pracainzynierska.data;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PlaceRepository {
    private PlaceDao mplaceDao;

    private LiveData<List<Place>> mallPlace;
    private LiveData<List<Place>> mAllMuseum;
    private LiveData<List<Place>> mAllChurch;
    private LiveData<List<Place>> mAllPark;
    private LiveData<List<Place>> mAllPalace;
    private LiveData<List<Place>> mAllLake;
    private LiveData<List<Place>> mAllHistoryPlace;

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
        mAllMuseum = mplaceDao.getAllMuseum();
        mAllChurch = mplaceDao.getAllMChurch();
        mAllHistoryPlace = mplaceDao.getAllHistoryPlace();
        mAllLake = mplaceDao.getAllLake();
        mAllPalace = mplaceDao.getAllPalace();
        mAllPark = mplaceDao.getAllPark();

    }

    public LiveData<List<Place>> getPlaceAll(){
        return mallPlace;
    }
    public LiveData<List<Place>> getMuseumAll(){ return mAllMuseum;}
    public LiveData<List<Place>> getPalaceAll(){ return mAllPalace;}
    public LiveData<List<Place>> getParkAll(){ return mAllPark;}
    public LiveData<List<Place>> getLakeAll(){ return mAllLake;}
    public LiveData<List<Place>> getChurchAll(){ return mAllChurch;}
    public LiveData<List<Place>> getHistoryPlaceAll(){ return mAllHistoryPlace;}

    public  List<Double> getMallLatitude() {return mallLatitude;}
    public List<Double> getMallLongitude() {return mallLongitude;}
    public List<String> getMallPlaceName() {return mallPlaceName;}
    public List<String> getMallPlaceType() {return mallPlaceType;}

        public void insert(Place place){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mplaceDao.insert(place);
        });
    }

        public void delete(Place place){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mplaceDao.deleteOnePlace(place);
        });
    }

         public void update(Place place){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mplaceDao.updateOnePlace(place);
        });
    }
    public void deleteAllPlace(){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mplaceDao.deleteAllPlace();
        });
    }
}
