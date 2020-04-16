package com.example.pracainzynierska.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Place")
public class Place {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "place_name")
    public String placeName ;
    @ColumnInfo(name = "description")
    public String description ;
    @ColumnInfo(name = "image_url")
    public String imageUrl ;
    public Place(){}
    public Place(String placeName,String description,String imageUrl) {
        this.placeName = placeName;
        this.description = description;
        this.imageUrl = imageUrl;}
}
