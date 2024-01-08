package com.ozanapps.amadeusflightsearchapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ozanapps.amadeusflightsearchapi.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/mock-api/flights")
public class MockFlightApiController {
    private static final Logger logger = LoggerFactory.getLogger(MockFlightApiController.class);

    private final ObjectMapper objectMapper;

    public MockFlightApiController() {
        // Create and configure ObjectMapper here
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Registering the JavaTimeModule
    }

    @GetMapping  // Updated this line
    public List<Flight> getAllFlights() {
        return readFlightsFromJsonFile();
    }

    private List<Flight> readFlightsFromJsonFile() {
        try {
            String jsonFilePath = Objects.requireNonNull(getClass().getClassLoader().getResource("flights.json")).getFile();
            InputStream inputStream = new FileInputStream(jsonFilePath);
            byte[] copyToByteArray = FileCopyUtils.copyToByteArray(inputStream);
            String json = new String(copyToByteArray, StandardCharsets.UTF_8);

            TypeReference<List<Flight>> typeReference = new TypeReference<>() {
            };
            return objectMapper.readValue(json, typeReference); // Using the configured objectMapper
        } catch (IOException e) {
            logger.error("Error reading flights from JSON file", e);
            return List.of();
        }
    }
}