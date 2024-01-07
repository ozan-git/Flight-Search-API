package com.ozanapps.amadeusflightsearchapi.service;

import com.ozanapps.amadeusflightsearchapi.exception.ResourceNotFoundException;
import com.ozanapps.amadeusflightsearchapi.model.Airport;
import com.ozanapps.amadeusflightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // Create a new airport
    public Airport createAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    // Retrieve an airport by its ID
    public Airport getAirportById(Long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if (airport.isPresent()) {
            return airport.get();
        } else {
            throw new ResourceNotFoundException("Airport not found with id: " + id);
        }
    }

    // Retrieve all airports
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // Update an airport's details
    public Airport updateAirport(Long id, Airport airportDetails) {
        Airport airport = getAirportById(id); // Can throw a ResourceNotFoundException
        airport.setCity(airportDetails.getCity());
        return airportRepository.save(airport);
    }

    // Delete an airport
    public void deleteAirport(Long id) {
        Airport airport = getAirportById(id); // Can throw a ResourceNotFoundException
        airportRepository.delete(airport);
    }
}
