package com.Myproject.GoogleMapApi.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
	
	@JsonProperty("latLng")
	private LatLng latlan;

	public Location(LatLng latlan) {
		this.latlan = latlan;
	}

	public LatLng getLatlan() {
		return latlan;
	}

	public void setLatlan(LatLng latlan) {
		this.latlan = latlan;
	}
	
}
