package com.medori42.fleetmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objet de transfert de donnÃ©es reprÃ©sentant un propriÃ©taire client.
 * UtilisÃ© pour recevoir les donnÃ©es du service Client Management
 * via l'API REST.
 *
 * <p>Cette classe n'est pas une entitÃ© JPA car les donnÃ©es client
 * sont gÃ©rÃ©es par un microservice sÃ©parÃ©.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerClientData {

    /**
     * Identifiant unique du propriÃ©taire client.
     */
    private Long ownerIdentifier;

    /**
     * Nom complet du propriÃ©taire.
     */
    private String ownerFullName;

    /**
     * Ã‚ge du propriÃ©taire en annÃ©es.
     */
    private Integer ownerAge;
}
