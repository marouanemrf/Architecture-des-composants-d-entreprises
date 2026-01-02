package com.medori42.fleetmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EntitÃ© JPA reprÃ©sentant un vÃ©hicule dans le systÃ¨me de gestion de flotte.
 * Cette classe modÃ©lise les informations essentielles d'un vÃ©hicule
 * et sa relation avec un propriÃ©taire client.
 *
 * <p>Chaque vÃ©hicule est associÃ© Ã  un client via l'identifiant
 * {@code ownerClientId} qui rÃ©fÃ©rence le service Client Management.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 */
@Entity
@Table(name = "automobiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutomobileEntity {

    /**
     * Identifiant technique unique du vÃ©hicule.
     * GÃ©nÃ©rÃ© automatiquement via stratÃ©gie d'identitÃ©.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automobile_id")
    private Long automobileId;

    /**
     * Marque du constructeur automobile.
     * Exemples: Toyota, BMW, Mercedes-Benz.
     */
    @Column(name = "manufacturer_brand", nullable = false)
    private String manufacturerBrand;

    /**
     * DÃ©signation du modÃ¨le du vÃ©hicule.
     * Nom commercial du modÃ¨le spÃ©cifique.
     */
    @Column(name = "model_designation")
    private String modelDesignation;

    /**
     * NumÃ©ro d'immatriculation officiel du vÃ©hicule.
     * Plaque d'immatriculation ou numÃ©ro d'enregistrement.
     */
    @Column(name = "license_plate", unique = true)
    private String licensePlateNumber;

    /**
     * Identifiant du client propriÃ©taire du vÃ©hicule.
     * RÃ©fÃ©rence vers le service Client Management.
     */
    @Column(name = "owner_client_id")
    private Long ownerClientId;
}
