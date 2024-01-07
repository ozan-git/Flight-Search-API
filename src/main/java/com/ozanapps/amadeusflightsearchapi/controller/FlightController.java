package com.ozanapps.amadeusflightsearchapi.controller;

import com.ozanapps.amadeusflightsearchapi.model.Flight;
import com.ozanapps.amadeusflightsearchapi.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight newFlight = flightService.createFlight(flight);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }

    @GetMapping("/")
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
        Flight updatedFlight = flightService.updateFlight(id, flightDetails);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam(required = false) String departureAirport,
                                                      @RequestParam(required = false) String arrivalAirport,
                                                      @RequestParam(required = false) LocalDateTime departureTime,
                                                      @RequestParam(required = false) LocalDateTime returnTime) {
        List<Flight> flights = flightService.searchFlights(departureAirport, arrivalAirport, departureTime, returnTime);
        return ResponseEntity.ok(flights);
    }
}
