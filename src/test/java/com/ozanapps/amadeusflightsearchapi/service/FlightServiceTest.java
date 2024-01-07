package com.ozanapps.amadeusflightsearchapi.service;

import com.ozanapps.amadeusflightsearchapi.model.Flight;
import com.ozanapps.amadeusflightsearchapi.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FlightServiceTest {

    @MockBean
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @Test
    public void whenNoFlightsExist_findAllReturnsEmptyList() {
        Mockito.when(flightRepository.findAll()).thenReturn(Collections.emptyList());
        List<Flight> flights = flightService.getAllFlights();
        assertTrue(flights.isEmpty(), "Expected an empty list of flights");
    }

    @Test
    public void whenCreateFlight_thenFlightShouldBeCreated() {
        Flight flight = new Flight();
        flight.setDepartureAirport("IST");
        flight.setArrivalAirport("ESB");
        flight.setDepartureTime(LocalDateTime.parse("2021-05-01T10:00:00"));
        flight.setArrivalTime(LocalDateTime.parse("2021-05-01T11:00:00"));
        flight.setPrice(BigDecimal.valueOf(100.0));
        Mockito.when(flightRepository.save(flight)).thenReturn(flight);
        Flight newFlight = flightService.createFlight(flight);

        assertEquals("IST", newFlight.getDepartureAirport(), "Expected departure airport to be IST");
        assertEquals("ESB", newFlight.getArrivalAirport(), "Expected arrival airport to be ESB");
        assertEquals(flight.getDepartureTime().truncatedTo(ChronoUnit.MINUTES), newFlight.getDepartureTime().truncatedTo(ChronoUnit.MINUTES), "Expected departure time to be 2021-05-01T10:00");
        assertEquals(flight.getArrivalTime().truncatedTo(ChronoUnit.MINUTES), newFlight.getArrivalTime().truncatedTo(ChronoUnit.MINUTES), "Expected arrival time to be 2021-05-01T11:00");
        assertEquals(100.0, newFlight.getPrice().doubleValue(), "Expected price to be 100.0");
    }

    @Test
    public void whenSearchFlights_thenFlightsShouldBeFound() {
        Flight flight = new Flight();
        flight.setDepartureAirport("IST");
        flight.setArrivalAirport("ESB");
        flight.setDepartureTime(LocalDateTime.parse("2021-05-01T10:00:00"));
        flight.setArrivalTime(LocalDateTime.parse("2021-05-01T11:00:00"));
        flight.setPrice(BigDecimal.valueOf(100.0));
        Mockito.when(flightRepository.searchFlights("IST", "ESB", LocalDateTime.parse("2021-05-01T10:00:00"), LocalDateTime.parse("2021-05-01T11:00:00"))).thenReturn(Collections.singletonList(flight));
        List<Flight> flights = flightService.searchFlights("IST", "ESB", LocalDateTime.parse("2021-05-01T10:00:00"), LocalDateTime.parse("2021-05-01T11:00:00"));

        assertEquals(1, flights.size(), "Expected 1 flight");
        assertEquals("IST", flights.get(0).getDepartureAirport(), "Expected departure airport to be IST");
        assertEquals("ESB", flights.get(0).getArrivalAirport(), "Expected arrival airport to be ESB");
        assertEquals(flight.getDepartureTime().truncatedTo(ChronoUnit.MINUTES), flights.get(0).getDepartureTime().truncatedTo(ChronoUnit.MINUTES), "Expected departure time to be 2021-05-01T10:00");
        assertEquals(flight.getArrivalTime().truncatedTo(ChronoUnit.MINUTES), flights.get(0).getArrivalTime().truncatedTo(ChronoUnit.MINUTES), "Expected arrival time to be 2021-05-01T11:00");
        assertEquals(100.0, flights.get(0).getPrice().doubleValue(), "Expected price to be 100.0");
    }

    @Test
    public void whenSearchFlightsById_thenFlightsShouldBeFound() {
        List<Flight> flights = flightService.searchFlightsById(1L);
        assertEquals(0, flights.size(), "Expected 1 flight");
    }
}
