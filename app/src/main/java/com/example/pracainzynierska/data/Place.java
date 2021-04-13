package com.example.pracainzynierska.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Place")
public class Place  implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id ;

    @ColumnInfo(name = "place_name")
    public String placeName ;

    @ColumnInfo(name = "description")
    public String description ;

    @ColumnInfo(name = "image_url")
    public String imageUrl ;

    @ColumnInfo(name = "Latitude")
    public double latitude;

    @ColumnInfo(name = "longitude")
    public double longitude;

    @ColumnInfo(name = "place_type")
    public String placeType;

    public Place(String placeName,String description,String imageUrl,String placeType,double longitude,double latitude) {

        this.placeName = placeName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.placeType = placeType;
        this.latitude = latitude;
        this.longitude = longitude;}
    @Ignore
    public Place(int id, String placeName,String description,String imageUrl,String placeType,double longitude,double latitude) {
        this.id = id;
        this.placeName = placeName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.placeType = placeType;
        this.latitude = latitude;
        this.longitude = longitude;}

    protected Place(Parcel in) {

        id = in.readInt();
        placeName = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        placeType = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(placeName);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(placeType);
    }
}
