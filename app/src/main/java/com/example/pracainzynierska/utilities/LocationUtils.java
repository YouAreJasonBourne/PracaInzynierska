package com.example.pracainzynierska.utilities;

import android.location.Location;

import com.example.pracainzynierska.data.Place;

public class LocationUtils {

    private final double  earthRadius = 6371.0;


    public LocationUtils() {
    }

    public double distanceToPlaceCalculation(Place place, Location location) {

        double currentLocationLat = location.getLatitude();
        double currentLocationLng = location.getLongitude();

        double placeLocationLat = place.latitude;
        double placeLocationLng = place.longitude;

        double currentLocationLatToRadius = currentLocationLat * Math.PI / 180;
        double placeLocationLatToRadius = placeLocationLat * Math.PI / 180;
        double calculateLats = (currentLocationLat - placeLocationLat) * Math.PI / 180;
        double calculateLngs = (currentLocationLng - placeLocationLng) * Math.PI / 180;

        double a = Math.sin(calculateLats / 2) * Math.sin(calculateLats / 2) +
                Math.cos(currentLocationLatToRadius) * Math.cos(placeLocationLatToRadius) *
                        Math.sin(calculateLngs / 2) * Math.sin(calculateLngs / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distanceInKilometeres = earthRadius * c;
        distanceInKilometeres = Math.round(distanceInKilometeres * 10);

        return distanceInKilometeres;
    }
}