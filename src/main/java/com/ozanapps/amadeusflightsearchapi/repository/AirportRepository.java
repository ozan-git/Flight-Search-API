package com.ozanapps.amadeusflightsearchapi.repository;

import com.ozanapps.amadeusflightsearchapi.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findByCity(String city);
}
