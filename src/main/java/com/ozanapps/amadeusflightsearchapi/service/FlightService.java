package com.ozanapps.amadeusflightsearchapi.service;

import com.ozanapps.amadeusflightsearchapi.exception.ResourceNotFoundException;
import com.ozanapps.amadeusflightsearchapi.model.Flight;
import com.ozanapps.amadeusflightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id).orElseThrow();
        flight.setDepartureAirport(flightDetails.getDepartureAirport());
        flight.setArrivalAirport(flightDetails.getArrivalAirport());
        flight.setDepartureTime(flightDetails.getDepartureTime());
        flight.setArrivalTime(flightDetails.getArrivalTime());
        flight.setPrice(flightDetails.getPrice());
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    // search flights by id
    public List<Flight> searchFlightsById(Long id) {
        return flightRepository.searchFlightsById(id);
    }

    public List<Flight> searchFlights(String departureAirport, String arrivalAirport, LocalDateTime departureTime, LocalDateTime returnTime) {
        if (returnTime == null) {
            return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(departureAirport, arrivalAirport, departureTime);
        } else {
            List<Flight> departures = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(departureAirport, arrivalAirport, departureTime);
            List<Flight> returns = flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureTime(arrivalAirport, departureAirport, returnTime);
            departures.addAll(returns);
            return departures;
        }
    }

}
