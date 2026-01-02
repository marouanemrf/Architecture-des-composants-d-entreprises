package com.medori42.cloudapp.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ContrÃ´leur REST fournissant les points d'accÃ¨s de bienvenue pour l'application cloud.
 *
 * <p>
 * Ce contrÃ´leur dÃ©montre l'intÃ©gration des ConfigMaps Kubernetes avec
 * les applications Spring Boot en exposant un point d'accÃ¨s qui retourne
 * un message de bienvenue configurable.
 * </p>
 *
 * <p>
 * Les fonctionnalitÃ©s principales incluent:
 * <ul>
 *   <li>Point d'accÃ¨s RESTful pour les messages de bienvenue</li>
 *   <li>Configuration externalisÃ©e via variables d'environnement</li>
 *   <li>IntÃ©gration avec Kubernetes ConfigMap</li>
 * </ul>
 * </p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024
 * @see org.springframework.web.bind.annotation.RestController
 */
@RestController
public class WelcomeRestController {

    /**
     * ClÃ© utilisÃ©e pour le message dans la rÃ©ponse JSON.
     */
    private static final String RESPONSE_MESSAGE_KEY = "message";

    /**
     * ClÃ© utilisÃ©e pour le statut dans la rÃ©ponse JSON.
     */
    private static final String RESPONSE_STATUS_KEY = "status";

    /**
     * Valeur de statut indiquant une opÃ©ration rÃ©ussie.
     */
    private static final String STATUS_SUCCESS_VALUE = "OK";

    /**
     * Message de bienvenue configurÃ© via Kubernetes ConfigMap ou propriÃ©tÃ©s de l'application.
     *
     * <p>
     * Cette valeur est injectÃ©e depuis la variable d'environnement {@code APP_MESSAGE},
     * avec une valeur par dÃ©faut de repli si non configurÃ©e.
     * </p>
     */
    @Value("${APP_MESSAGE:Bienvenue depuis la valeur par defaut}")
    private String welcomeMessageContent;

    /**
     * Constructeur par dÃ©faut du contrÃ´leur de bienvenue.
     *
     * <p>
     * Initialise le contrÃ´leur REST pour gÃ©rer les requÃªtes de bienvenue.
     * </p>
     */
    public WelcomeRestController() {
    }

    /**
     * RÃ©cupÃ¨re un message de bienvenue avec le statut de l'application.
     *
     * <p>
     * Ce point d'accÃ¨s dÃ©montre comment les ConfigMaps Kubernetes peuvent Ãªtre
     * utilisÃ©s pour externaliser la configuration dans les applications Spring Boot
     * conteneurisÃ©es. Le message peut Ãªtre mis Ã  jour en modifiant le ConfigMap
     * sans reconstruire l'application.
     * </p>
     *
     * <p>
     * Exemple de rÃ©ponse:
     * <pre>
     * {
     *   "message": "Bienvenue depuis Kubernetes ConfigMap",
     *   "status": "OK"
     * }
     * </pre>
     * </p>
     *
     * @return une Map contenant le message de bienvenue et le statut de l'application
     */
    @GetMapping("/api/hello")
    public Map<String, String> retrieveWelcomeMessage() {
        return buildResponsePayload();
    }

    /**
     * Construit le payload de rÃ©ponse contenant le message et le statut.
     *
     * <p>
     * Cette mÃ©thode encapsule la logique de crÃ©ation de la rÃ©ponse JSON
     * pour assurer une structure cohÃ©rente.
     * </p>
     *
     * @return la Map de rÃ©ponse avec le message et le statut
     */
    private Map<String, String> buildResponsePayload() {
        Map<String, String> responsePayload = new HashMap<>();
        responsePayload.put(RESPONSE_MESSAGE_KEY, getWelcomeMessageContent());
        responsePayload.put(RESPONSE_STATUS_KEY, STATUS_SUCCESS_VALUE);
        return responsePayload;
    }

    /**
     * RÃ©cupÃ¨re le contenu du message de bienvenue configurÃ©.
     *
     * <p>
     * Cette mÃ©thode fournit un accÃ¨s encapsulÃ© au message de bienvenue
     * pour permettre des extensions futures.
     * </p>
     *
     * @return le message de bienvenue configurÃ©
     */
    private String getWelcomeMessageContent() {
        return this.welcomeMessageContent;
    }
}
