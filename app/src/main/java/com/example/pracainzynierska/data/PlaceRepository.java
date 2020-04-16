package com.example.pracainzynierska.data;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PlaceRepository {
    private PlaceDao mplaceDao;
    private LiveData<List<Place>> mallPlace;


    public PlaceRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mplaceDao = db.placeDao();
        mallPlace = mplaceDao.getAllPlace();
    }

    public LiveData<List<Place>> getPlaceAll(){
        return mallPlace;
    }

        public void insert(Place place){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            mplaceDao.insert(place);
        });
    }
}
