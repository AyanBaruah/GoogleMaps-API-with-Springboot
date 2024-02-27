package com.Myproject.GoogleMapApi.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {
	
	@JsonProperty("location")
	private Location location;
	
	public Place(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}
