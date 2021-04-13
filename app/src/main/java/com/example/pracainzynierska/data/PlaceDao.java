package com.example.pracainzynierska.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlaceDao{

    @Query("SELECT * FROM Place")
    LiveData<List<Place>> getAllPlace();
    @Query("SELECT latitude FROM PLACE")
    List<Double> getAllLatitude();
    @Query("SELECT longitude FROM PLACE")
    List<Double> getAllLongitude();
    @Query("SELECT place_type FROM PLACE")
    List<String> getAllPlaceType();
    @Query("SELECT place_name FROM PLACE")
    List<String> getAllPlaceName();
    @Query("DELETE FROM PLACE")
    void deleteAllPlace();
    @Query("SELECT * FROM PLACE WHERE PLACE_TYPE ='Muzeum'")
    LiveData<List<Place>> getAllMuseum();
    @Query("SELECT * FROM PLACE WHERE PLACE_TYPE ='Obiekt Sakralny'")
    LiveData<List<Place>> getAllMChurch();
    @Query("SELECT * FROM PLACE WHERE PLACE_TYPE ='Pałac'")
    LiveData<List<Place>> getAllPalace();
    @Query("SELECT * FROM PLACE WHERE PLACE_TYPE ='Obiekt Historyczny'")
    LiveData<List<Place>> getAllHistoryPlace();
    @Query("SELECT * FROM PLACE WHERE PLACE_TYPE ='Jezioro'")
    LiveData<List<Place>> getAllLake();
    @Query("SELECT * FROM PLACE WHERE PLACE_TYPE ='Park'")
    LiveData<List<Place>> getAllPark();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Place place);

    @Delete(entity = Place.class)
    void deleteOnePlace(Place place);

    @Update(entity = Place.class)
    void updateOnePlace(Place place);

}
