package com.Myproject.GoogleMapApi.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestGoogleDist {
	
	@JsonProperty("origin")
	private Place origin;
	
	@JsonProperty("destination")
	private Place dest;
	
	@JsonProperty("travelMode")
	private String travel;
	
	
	public RequestGoogleDist() {
	}
	public RequestGoogleDist(Place origin, Place dest, String travel) {
		this.origin = origin;
		this.dest = dest;
		this.travel = travel;
	}
	public Place getOrigin() {
		return origin;
	}
	public void setOrigin(Place origin) {
		this.origin = origin;
	}
	public Place getDest() {
		return dest;
	}
	public void setDest(Place dest) {
		this.dest = dest;
	}
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	
	

}
