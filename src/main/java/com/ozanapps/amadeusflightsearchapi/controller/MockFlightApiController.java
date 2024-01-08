package com.ozanapps.amadeusflightsearchapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@RestController
@RequestMapping("/mock-api/flights")
public class MockFlightApiController {
    private static final Logger logger = LoggerFactory.getLogger(MockFlightApiController.class);

    @GetMapping("/")
    public List<Flight> getAllFlights() {
        return readFlightsFromJsonFile();
    }

    private List<Flight> readFlightsFromJsonFile() {
        try {
            String jsonFilePath = "src/main/resources/flights.json";

            InputStream inputStream = new FileInputStream(jsonFilePath);
            byte[] copyToByteArray = FileCopyUtils.copyToByteArray(inputStream);
            String json = new String(copyToByteArray, StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, new TypeReference<List<Flight>>() {});

        } catch (IOException e) {
            logger.error("Error reading flights from JSON file", e);
            return List.of();
        }
    }

}