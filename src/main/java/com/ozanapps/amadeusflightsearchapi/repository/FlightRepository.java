package com.ozanapps.amadeusflightsearchapi.repository;

import com.ozanapps.amadeusflightsearchapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.departureAirport = :departureAirport AND f.arrivalAirport = :arrivalAirport AND f.departureTime BETWEEN :departureTime AND :returnTime")
    List<Flight> searchFlights(@Param("departureAirport") String departureAirport,
                               @Param("arrivalAirport") String arrivalAirport,
                               @Param("departureTime") LocalDateTime departureTime,
                               @Param("returnTime") LocalDateTime returnTime);

    @Query("SELECT f FROM Flight f WHERE f.flightID = :id")
    List<Flight> searchFlightsById(@Param("id") Long id);

    @Query("SELECT f FROM Flight f WHERE f.departureAirport = :departureAirport AND f.arrivalAirport = :arrivalAirport AND f.departureTime = :departureTime")
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureTime(String departureAirport, String arrivalAirport, LocalDateTime departureTime);
}
