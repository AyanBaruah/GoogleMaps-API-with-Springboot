package com.Myproject.GoogleMapApi.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`order`")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int distance;
    private String status;

    // Constructors, getters, and setters

    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(Long id, int distance, String status) {
        this.id = id;
        this.distance = distance;
        this.status = status;
    }

    // Getters and setters for id, distance, and status

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
