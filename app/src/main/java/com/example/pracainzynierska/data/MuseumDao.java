package com.example.pracainzynierska.data;

import java.util.List;

import androidx.room.Query;

public interface MuseumDao {
    @Query("SELECT * FROM museums")
    List<Museum> getAll();
}
