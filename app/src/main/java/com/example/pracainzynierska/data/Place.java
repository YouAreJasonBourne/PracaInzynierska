package com.example.pracainzynierska.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Place")
public class Place {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "place_name",defaultValue = "temp")
    public String placeName ;
    @ColumnInfo(name = "description",defaultValue = "description")
    public String description ;
    @ColumnInfo(name = "image_url")
    public String imageUrl ;
    @ColumnInfo(name = "Latitude")
    public double latitude;
    @ColumnInfo(name = "longitude")
    public double longitude;
    @ColumnInfo(name = "place_type")
    public String placeType;

    public Place(){}
    public Place(String placeName,String description,String imageUrl,String placeType,double longitude,double latitude) {
        this.placeName = placeName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.placeType = placeType;
        this.latitude = latitude;
        this.longitude = longitude;}
}
