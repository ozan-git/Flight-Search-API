package com.ozanapps.amadeusflightsearchapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().components(new io.swagger.v3.oas.models.Components())
                .info(new Info().title("Amadeus Flight Search API")
                        .description("This is a simple API for searching flights using Amadeus API")
                        .version("1.0.0"));
    }
}