package com.medori42.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Main application class for the Sales Service.
 * This Spring Boot application provides REST endpoints for sales operations.
 *
 * @version 1.0
 * @since 2025
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SalesServiceApplication {

    /**
     * Main entry point for the Sales Service application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SalesServiceApplication.class, args);
    }
}
