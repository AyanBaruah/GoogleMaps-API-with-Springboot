package com.Myproject.GoogleMapApi.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteResponse {

    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    
}
