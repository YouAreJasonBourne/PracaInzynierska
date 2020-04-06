package com.example.pracainzynierska.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Museum.class,},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MuseumDao museumDao();
}
