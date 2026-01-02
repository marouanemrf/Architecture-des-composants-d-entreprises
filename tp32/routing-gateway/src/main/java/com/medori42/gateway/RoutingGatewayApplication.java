package com.medori42.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

/**
 * Application principale de la passerelle API.
 * Cette passerelle Spring Cloud Gateway route les requÃªtes entrantes
 * vers les microservices appropriÃ©s en utilisant la dÃ©couverte de services.
 *
 * <p>IntÃ¨gre avec le serveur Eureka pour le routage dynamique
 * sans configuration manuelle des routes.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 */
@SpringBootApplication
public class RoutingGatewayApplication {

    /**
     * Point d'entrÃ©e principal de l'application Gateway.
     * DÃ©marre le contexte Spring Boot et le serveur de routage.
     *
     * @param launchArguments les arguments de lancement de l'application
     */
    public static void main(String[] launchArguments) {
        SpringApplication.run(RoutingGatewayApplication.class, launchArguments);
    }

    /**
     * Configure la dÃ©couverte dynamique des routes via Eureka.
     * Permet la crÃ©ation automatique des routes pour les services
     * enregistrÃ©s dans le registre de services.
     *
     * @param reactiveClient le client rÃ©actif de dÃ©couverte de services
     * @param locatorProperties les propriÃ©tÃ©s de configuration du locator
     * @return le locator configurÃ© pour le routage dynamique
     */
    @Bean
    DiscoveryClientRouteDefinitionLocator configureRouteDiscovery(
            ReactiveDiscoveryClient reactiveClient,
            DiscoveryLocatorProperties locatorProperties) {
        return new DiscoveryClientRouteDefinitionLocator(reactiveClient, locatorProperties);
    }
}
