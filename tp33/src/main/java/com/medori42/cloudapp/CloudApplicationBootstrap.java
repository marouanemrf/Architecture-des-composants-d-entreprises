package com.medori42.cloudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de dÃ©marrage de l'application Spring Boot Cloud.
 *
 * <p>
 * Cette application dÃ©montre le dÃ©ploiement d'une application Spring Boot
 * sur Kubernetes avec intÃ©gration ConfigMap, configuration de sÃ©curitÃ© et
 * points d'accÃ¨s API RESTful.
 * </p>
 *
 * <p>
 * L'application utilise Spring Boot 3.2.0 avec Java 17 et fournit une
 * architecture cloud-native prÃªte pour la production.
 * </p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 */
@SpringBootApplication
public class CloudApplicationBootstrap {

    /**
     * Constructeur par dÃ©faut de la classe de dÃ©marrage.
     *
     * <p>
     * Ce constructeur est utilisÃ© par Spring Boot pour initialiser
     * l'application. Il ne nÃ©cessite aucun paramÃ¨tre.
     * </p>
     */
    public CloudApplicationBootstrap() {
    }

    /**
     * Point d'entrÃ©e principal de l'application Spring Boot.
     *
     * <p>
     * Cette mÃ©thode initialise et dÃ©marre le contexte d'application Spring,
     * en bootstrappant tous les composants et services configurÃ©s.
     * </p>
     *
     * <p>
     * Le processus de dÃ©marrage inclut:
     * <ul>
     *   <li>Chargement de la configuration depuis application.properties</li>
     *   <li>Initialisation des beans Spring</li>
     *   <li>DÃ©marrage du serveur embarquÃ©</li>
     *   <li>Configuration de la sÃ©curitÃ©</li>
     * </ul>
     * </p>
     *
     * @param applicationArguments arguments de ligne de commande passÃ©s Ã  l'application
     */
    public static void main(final String[] applicationArguments) {
        initializeApplication(applicationArguments);
    }

    /**
     * MÃ©thode d'initialisation de l'application Spring Boot.
     *
     * <p>
     * Cette mÃ©thode encapsule la logique de dÃ©marrage de l'application
     * et peut Ãªtre Ã©tendue pour ajouter des configurations supplÃ©mentaires.
     * </p>
     *
     * @param runtimeArguments les arguments de dÃ©marrage de l'application
     */
    private static void initializeApplication(final String[] runtimeArguments) {
        SpringApplication.run(CloudApplicationBootstrap.class, runtimeArguments);
    }
}
