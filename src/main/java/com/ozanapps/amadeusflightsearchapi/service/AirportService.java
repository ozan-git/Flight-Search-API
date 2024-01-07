package com.ozanapps.amadeusflightsearchapi.service;

import com.ozanapps.amadeusflightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
    @Autowired
    public AirportService(AirportRepository airportRepository) {
    }
}
