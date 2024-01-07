package com.ozanapps.amadeusflightsearchapi.controller;

import com.ozanapps.amadeusflightsearchapi.model.Flight;
import com.ozanapps.amadeusflightsearchapi.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @BeforeEach
    public void setup() {
        List<Flight> mockFlights = new ArrayList<>();
        Flight mockFlight = new Flight();
        mockFlight.setFlightID(1L);
        mockFlight.setDepartureAirport("JFK");
        mockFlight.setArrivalAirport("LHR");
        mockFlight.setDepartureTime(LocalDateTime.of(2024, 1, 10, 14, 30));
        mockFlight.setArrivalTime(LocalDateTime.of(2024, 1, 11, 2, 30));
        mockFlights.add(mockFlight);

        Mockito.when(flightService.searchFlights(any(), any(), any(), any())).thenReturn(mockFlights);
    }

    @Test
    public void whenSearchFlights_thenReturnsFlights() throws Exception {
        mockMvc.perform(get("/api/flights/search")
                        .param("departureAirport", "JFK")
                        .param("arrivalAirport", "LHR")
                        .param("departureTime", "2024-01-10T14:30:00")
                        .param("returnTime", "2024-01-11T02:30:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"flightID\":1,\"departureAirport\":\"JFK\",\"arrivalAirport\":\"LHR\",\"departureTime\":\"2024-01-10T14:30:00\",\"arrivalTime\":\"2024-01-11T02:30:00\",\"price\":null}]"));
    }
}
