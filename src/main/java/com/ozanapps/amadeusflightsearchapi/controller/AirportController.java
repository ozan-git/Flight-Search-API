package com.ozanapps.amadeusflightsearchapi.controller;

import com.ozanapps.amadeusflightsearchapi.model.Airport;
import com.ozanapps.amadeusflightsearchapi.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    // Create a new airport
    @PostMapping("/")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        Airport newAirport = airportService.createAirport(airport);
        return new ResponseEntity<>(newAirport, HttpStatus.CREATED);
    }

    // Retrieve an airport by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);
        return ResponseEntity.ok(airport);
    }

    // Retrieve all airports
    @GetMapping("/")
    public ResponseEntity<List<Airport>> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    // Update an airport's details
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airportDetails) {
        Airport updatedAirport = airportService.updateAirport(id, airportDetails);
        return ResponseEntity.ok(updatedAirport);
    }

    // Delete an airport
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
