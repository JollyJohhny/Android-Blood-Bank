package com.example.androidbloodbank;

import android.location.Location;

public class LocationType {

    private String UserId;
    private Location location;

    public LocationType(){

    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocationType(String uid, Location l){
        UserId = uid;
        location = l;
    }
}
