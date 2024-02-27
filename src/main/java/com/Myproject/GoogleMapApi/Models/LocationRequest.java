package com.Myproject.GoogleMapApi.Models;

import java.util.List;

public class LocationRequest {

    private List<String> origin;
    private List<String> destination;

    public List<String> getOrigin() {
        return origin;
    }

    public void setOrigin(List<String> origin) {
        this.origin = origin;
    }

    public List<String> getDestination() {
        return destination;
    }

    public void setDestination(List<String> destination) {
        this.destination = destination;
    }
}