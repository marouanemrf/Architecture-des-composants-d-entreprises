package com.medori42.clientmanagement.persistence;

import com.medori42.clientmanagement.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repository pour l'accÃ¨s aux donnÃ©es des clients.
 * HÃ©rite des mÃ©thodes CRUD standard de JpaRepository et peut Ãªtre
 * Ã©tendue avec des requÃªtes personnalisÃ©es.
 *
 * <p>Cette interface utilise Spring Data JPA pour gÃ©nÃ©rer
 * automatiquement les implÃ©mentations des mÃ©thodes d'accÃ¨s aux donnÃ©es.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 * @see ClientEntity
 * @see JpaRepository
 */
@Repository
public interface ClientDataRepository extends JpaRepository<ClientEntity, Long> {

}
