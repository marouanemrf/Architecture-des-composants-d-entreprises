package com.medori42.sales.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller providing health check and information endpoints.
 * This controller exposes basic service information and status endpoints.
 *
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/")
public class HealthController {

    /**
     * Returns a welcome message from the service.
     *
     * @return welcome message string
     */
    @GetMapping
    public String getWelcomeMessage() {
        return "Hello from spring boot application :)";
    }

    /**
     * Retrieves user information endpoint.
     *
     * @return user information string
     */
    @GetMapping("/user")
    public String fetchUserList() {
        return "Users";
    }

    /**
     * Retrieves service presentation details.
     *
     * @return presentation information string
     */
    @GetMapping("/presentation")
    public String fetchServiceDetails() {
        return "presentation";
    }
}
