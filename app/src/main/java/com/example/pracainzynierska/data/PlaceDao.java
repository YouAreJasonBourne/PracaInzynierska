package com.example.pracainzynierska.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
@Dao
public interface PlaceDao{
    @Query("SELECT * FROM Place")
    LiveData<List<Place>> getAllPlace();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Place place);
}
