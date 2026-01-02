package com.medori42.fleetmanagement.persistence;

import com.medori42.fleetmanagement.domain.AutomobileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repository pour l'accÃ¨s aux donnÃ©es des vÃ©hicules.
 * Fournit les opÃ©rations CRUD standard et peut Ãªtre Ã©tendue
 * avec des requÃªtes personnalisÃ©es.
 *
 * <p>Utilise Spring Data JPA pour la gÃ©nÃ©ration automatique
 * des implÃ©mentations.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 * @see AutomobileEntity
 * @see JpaRepository
 */
@Repository
public interface AutomobileDataRepository extends JpaRepository<AutomobileEntity, Long> {

}
