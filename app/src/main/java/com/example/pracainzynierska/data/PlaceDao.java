package com.example.pracainzynierska.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Place place);
    @Delete
    void delete(Place place);
}
