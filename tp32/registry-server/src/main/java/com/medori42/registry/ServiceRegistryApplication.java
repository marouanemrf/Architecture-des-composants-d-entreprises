package com.medori42.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Application principale du serveur de registre de services.
 * ImplÃ©mente un serveur Eureka pour l'enregistrement et la dÃ©couverte
 * des microservices dans l'architecture distribuÃ©e.
 *
 * <p>Ce serveur permet aux autres services de s'enregistrer dynamiquement
 * et de dÃ©couvrir les instances disponibles pour la communication.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 */
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

    /**
     * Point d'entrÃ©e principal du serveur de registre.
     * Initialise le contexte Spring Boot et dÃ©marre le serveur Eureka.
     *
     * @param startupArguments les arguments de dÃ©marrage de l'application
     */
    public static void main(String[] startupArguments) {
        SpringApplication.run(ServiceRegistryApplication.class, startupArguments);
    }
}
