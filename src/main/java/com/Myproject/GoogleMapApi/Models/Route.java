package com.Myproject.GoogleMapApi.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Route {

    @JsonProperty("distanceMeters")
    private int distanceMeters;

    public int getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(int distanceMeters) {
        this.distanceMeters = distanceMeters;
    }
    
}