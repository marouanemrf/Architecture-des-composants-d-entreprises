package com.medori42.fleetmanagement.dto;

import com.medori42.fleetmanagement.domain.OwnerClientData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objet de transfert de donnÃ©es pour les rÃ©ponses de l'API vÃ©hicule.
 * Combine les informations du vÃ©hicule avec les donnÃ©es du propriÃ©taire
 * pour fournir une vue complÃ¨te.
 *
 * <p>UtilisÃ© comme modÃ¨le de rÃ©ponse pour les endpoints REST
 * qui nÃ©cessitent des informations enrichies.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutomobileResponseDto {

    /**
     * Identifiant unique du vÃ©hicule.
     */
    private Long automobileIdentifier;

    /**
     * Marque du constructeur.
     */
    private String manufacturerName;

    /**
     * DÃ©signation du modÃ¨le.
     */
    private String modelName;

    /**
     * NumÃ©ro de plaque d'immatriculation.
     */
    private String registrationPlate;

    /**
     * Informations du propriÃ©taire associÃ©.
     */
    private OwnerClientData ownerInformation;
}
