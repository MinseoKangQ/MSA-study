package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

public class FilterConfig {

    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // If a client requests http://localhost:8000/first-service/**, the request actually goes to http://localhost:8081/first-service/**
                .route(r -> r.path("/first-service/**")
                        .filters( f -> f.addRequestHeader("first-request", "first-request-header")
                                        .addResponseHeader("first-response", "first-response-header"))
                        .uri("http://localhost:8081"))
                // If a client requests http://localhost:8000/second-service/**, the request actually goes to http://localhost:8082/second-service/**
                .route(r -> r.path("/second-service/**")
                        .filters( f -> f.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response", "second-response-header"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
