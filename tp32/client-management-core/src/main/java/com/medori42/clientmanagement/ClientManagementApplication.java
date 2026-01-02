package com.medori42.clientmanagement;

import com.medori42.clientmanagement.domain.ClientEntity;
import com.medori42.clientmanagement.persistence.ClientDataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Application principale du microservice de gestion des clients.
 * Cette application Spring Boot fournit une API REST pour la gestion
 * des informations clients dans un environnement de microservices distribuÃ©.
 *
 * <p>Ce service s'enregistre auprÃ¨s du serveur Eureka pour permettre
 * la dÃ©couverte de services et le routage dynamique via l'API Gateway.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 * @see ClientEntity
 * @see ClientDataRepository
 */
@SpringBootApplication
public class ClientManagementApplication {

    /**
     * CrÃ©e et configure le bean d'initialisation de donnÃ©es de dÃ©monstration.
     * Ce bean est exÃ©cutÃ© au dÃ©marrage de l'application pour peupler
     * la base de donnÃ©es avec des enregistrements de test.
     *
     * @param clientDataRepository le repository d'accÃ¨s aux donnÃ©es clients
     * @return un CommandLineRunner qui initialise les donnÃ©es de dÃ©monstration
     */
    @Bean
    CommandLineRunner loadInitialData(ClientDataRepository clientDataRepository) {
        return commandLineArgs -> {
            clientDataRepository.save(new ClientEntity(1L, "Amine SAFI", 23.0f));
            clientDataRepository.save(new ClientEntity(2L, "Amal ALAOUI", 22.0f));
            clientDataRepository.save(new ClientEntity(3L, "Samir RAMI", 22.0f));
        };
    }

    /**
     * Point d'entrÃ©e principal de l'application Client Management.
     * Lance le contexte Spring Boot et dÃ©marre le serveur embarquÃ©.
     *
     * @param applicationArgs les arguments de ligne de commande
     */
    public static void main(String[] applicationArgs) {
        SpringApplication.run(ClientManagementApplication.class, applicationArgs);
    }
}
