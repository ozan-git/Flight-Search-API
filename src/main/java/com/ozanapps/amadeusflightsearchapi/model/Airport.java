package com.ozanapps.amadeusflightsearchapi.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "airports")
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private Integer airportID;

    @Column(name = "city", length = 100)
    private String city;

    // Constructors
    public Airport() {
    }

    public Airport(String city) {
        this.city = city;
    }

    // Getters and setters
    public Integer getAirportID() {
        return airportID;
    }

    public void setAirportID(Integer airportID) {
        this.airportID = airportID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportID=" + airportID +
                ", city='" + city + '\'' +
                '}';
    }
}
