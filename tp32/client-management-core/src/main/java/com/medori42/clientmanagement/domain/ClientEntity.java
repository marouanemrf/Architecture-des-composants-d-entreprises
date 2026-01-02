package com.medori42.clientmanagement.domain;

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
 * EntitÃ© JPA reprÃ©sentant un client dans le systÃ¨me.
 * Cette classe dÃ©finit le modÃ¨le de donnÃ©es pour le stockage
 * des informations relatives aux clients.
 *
 * <p>Les instances de cette classe sont persistÃ©es dans la table
 * {@code clients} de la base de donnÃ©es MySQL.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 */
@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    /**
     * Identifiant technique unique du client.
     * GÃ©nÃ©rÃ© automatiquement par la base de donnÃ©es.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientIdentifier;

    /**
     * Nom complet du client.
     * Comprend le prÃ©nom et le nom de famille.
     */
    @Column(name = "complete_name", nullable = false)
    private String completeName;

    /**
     * Ã‚ge du client en annÃ©es.
     * Valeur dÃ©cimale pour permettre une prÃ©cision accrue.
     */
    @Column(name = "client_age")
    private Float clientAge;
}
