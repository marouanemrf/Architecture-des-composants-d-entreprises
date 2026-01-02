package com.medori42.clientmanagement.application;

import com.medori42.clientmanagement.domain.ClientEntity;
import com.medori42.clientmanagement.persistence.ClientDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service mÃ©tier pour la gestion des opÃ©rations clients.
 * ImplÃ©mente la logique mÃ©tier et orchestre les appels
 * vers la couche de persistance.
 *
 * <p>Ce service est injectÃ© dans les contrÃ´leurs REST pour
 * traiter les requÃªtes relatives aux clients.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 * @see ClientEntity
 * @see ClientDataRepository
 */
@Service
public class ClientBusinessService {

    /**
     * Repository pour l'accÃ¨s aux donnÃ©es clients.
     */
    private final ClientDataRepository clientRepository;

    /**
     * Constructeur avec injection de dÃ©pendances.
     *
     * @param clientRepository le repository d'accÃ¨s aux donnÃ©es
     */
    @Autowired
    public ClientBusinessService(ClientDataRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * RÃ©cupÃ¨re l'ensemble des clients enregistrÃ©s.
     *
     * @return une liste contenant tous les clients
     */
    public List<ClientEntity> retrieveAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Recherche un client spÃ©cifique par son identifiant.
     *
     * @param clientId l'identifiant unique du client recherchÃ©
     * @return l'entitÃ© client correspondante
     * @throws Exception si l'identifiant ne correspond Ã  aucun client
     */
    public ClientEntity retrieveClientById(Long clientId) throws Exception {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new Exception("Identifiant client invalide: " + clientId));
    }

    /**
     * Enregistre un nouveau client dans la base de donnÃ©es.
     *
     * @param newClient l'entitÃ© client Ã  persister
     */
    public void registerNewClient(ClientEntity newClient) {
        clientRepository.save(newClient);
    }
}
