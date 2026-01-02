package com.medori42.fleetmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Application principale du microservice de gestion de flotte automobile.
 * Cette application Spring Boot gÃ¨re les informations des vÃ©hicules et
 * leurs associations avec les clients via communication inter-services.
 *
 * <p>Le service communique avec le service Client Management pour rÃ©cupÃ©rer
 * les informations des propriÃ©taires de vÃ©hicules.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 */
@SpringBootApplication
public class FleetManagementApplication {

    /**
     * DÃ©lai de connexion en millisecondes pour les appels HTTP.
     */
    private static final int HTTP_CONNECTION_TIMEOUT = 5000;

    /**
     * DÃ©lai de lecture en millisecondes pour les appels HTTP.
     */
    private static final int HTTP_READ_TIMEOUT = 5000;

    /**
     * Point d'entrÃ©e principal de l'application Fleet Management.
     * Initialise le contexte Spring Boot et dÃ©marre le serveur.
     *
     * @param commandLineArgs les arguments de la ligne de commande
     */
    public static void main(String[] commandLineArgs) {
        SpringApplication.run(FleetManagementApplication.class, commandLineArgs);
    }

    /**
     * Configure et fournit un bean RestTemplate pour la communication HTTP.
     * DÃ©finit les dÃ©lais de connexion et de lecture pour une communication
     * fiable entre les microservices.
     *
     * @return une instance RestTemplate configurÃ©e avec les timeouts appropriÃ©s
     */
    @Bean
    public RestTemplate httpRestTemplate() {
        RestTemplate restTemplateInstance = new RestTemplate();
        SimpleClientHttpRequestFactory httpRequestConfiguration = new SimpleClientHttpRequestFactory();
        httpRequestConfiguration.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
        httpRequestConfiguration.setReadTimeout(HTTP_READ_TIMEOUT);
        restTemplateInstance.setRequestFactory(httpRequestConfiguration);
        return restTemplateInstance;
    }
}
