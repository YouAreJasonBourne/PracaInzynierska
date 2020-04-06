package com.example.pracainzynierska.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "museums")
public class Museum {
    @PrimaryKey(autoGenerate = true)
    public int mid;
    @ColumnInfo(name = "museum_name")
    public String museumName;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "image_url")
    public String imageUrl;
}
