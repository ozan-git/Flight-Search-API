package com.ozanapps.amadeusflightsearchapi.service;

import com.ozanapps.amadeusflightsearchapi.model.Flight;
import com.ozanapps.amadeusflightsearchapi.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ScheduledFlightFetcherService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledFlightFetcherService.class);
    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;

    public ScheduledFlightFetcherService(FlightRepository flightRepository, RestTemplate restTemplate) {
        this.flightRepository = flightRepository;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 0 0 * * *") // Run every day at midnight
    public void fetchFlightsDaily() {
        try {
            ParameterizedTypeReference<List<Flight>> responseType = new ParameterizedTypeReference<>() {
            };
            String mockApiUrl = "http://localhost:8080/mock-api/flights"; // URL is correct
            ResponseEntity<List<Flight>> response = restTemplate.exchange(
                    mockApiUrl, HttpMethod.GET, null, responseType);

            List<Flight> flights = response.getBody();
            if (flights != null && !flights.isEmpty()) {
                flightRepository.saveAll(flights);
                logger.info("Flights fetched and saved successfully");
            }
        } catch (Exception e) {
            logger.error("Failed to fetch flights from mock API", e);
        }
    }
}
