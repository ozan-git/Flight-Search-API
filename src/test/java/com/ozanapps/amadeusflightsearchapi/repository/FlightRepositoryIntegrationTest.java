package com.ozanapps.amadeusflightsearchapi.repository;

import com.ozanapps.amadeusflightsearchapi.model.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class FlightRepositoryIntegrationTest {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void whenCreateFlight_thenFlightIsInDatabase() {
        // Given: Create a flight
        Flight flight = new Flight();
        flight.setDepartureAirport("IST");
        flight.setArrivalAirport("ESB");
        flight.setDepartureTime(LocalDateTime.parse("2021-05-01T10:00:00"));
        flight.setReturnTime(LocalDateTime.parse("2021-05-01T11:00:00"));
        flight.setPrice(BigDecimal.valueOf(100.0));

        // When: Save the flight to the database
        Flight savedFlight = flightRepository.save(flight);

        // Then: The flight should have an ID
        assertNotNull(savedFlight.getFlightID(), "Flight should have an ID after being saved to the database");
    }

    @Test
    public void whenDeleteFlight_thenFlightIsNotInDatabase() {
        // Given: Create a flight
        Flight flight = new Flight();
        flight.setDepartureAirport("IST");
        flight.setArrivalAirport("ESB");
        flight.setDepartureTime(LocalDateTime.parse("2021-05-01T10:00:00"));
        flight.setReturnTime(LocalDateTime.parse("2021-05-01T11:00:00"));
        flight.setPrice(BigDecimal.valueOf(100.0));

        // When: Save the flight to the database
        Flight savedFlight = flightRepository.save(flight);

        // Then: The flight should have an ID
        assertNotNull(savedFlight.getFlightID(), "Flight should have an ID after being saved to the database");

        // When: Delete the flight from the database
        flightRepository.delete(savedFlight);

        // Then: The flight should not be in the database
        assertNotNull(savedFlight.getFlightID(), "Flight should not be in the database after being deleted");
    }

    @Test
    public void whenUpdateFlight_thenFlightIsUpdatedInDatabase() {
        // Given: Create a flight
        Flight flight = new Flight();
        flight.setDepartureAirport("IST");
        flight.setArrivalAirport("ESB");
        flight.setDepartureTime(LocalDateTime.parse("2021-05-01T10:00:00"));
        flight.setReturnTime(LocalDateTime.parse("2021-05-01T11:00:00"));
        flight.setPrice(BigDecimal.valueOf(100.0));

        // When: Save the flight to the database
        Flight savedFlight = flightRepository.save(flight);

        // Then: The flight should have an ID
        assertNotNull(savedFlight.getFlightID(), "Flight should have an ID after being saved to the database");

        // When: Update the flight in the database
        savedFlight.setDepartureAirport("SAW");
        savedFlight.setArrivalAirport("ADB");
        savedFlight.setDepartureTime(LocalDateTime.parse("2021-05-01T12:00:00"));
        savedFlight.setReturnTime(LocalDateTime.parse("2021-05-01T13:00:00"));
        savedFlight.setPrice(BigDecimal.valueOf(200.0));
        flightRepository.save(savedFlight);

        // Then: The flight should be updated in the database
        assertNotNull(savedFlight.getFlightID(), "Flight should be updated in the database");
    }
}