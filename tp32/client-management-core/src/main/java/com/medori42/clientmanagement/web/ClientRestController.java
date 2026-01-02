package com.medori42.clientmanagement.web;

import com.medori42.clientmanagement.domain.ClientEntity;
import com.medori42.clientmanagement.application.ClientBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ContrÃ´leur REST exposant les endpoints de gestion des clients.
 * Fournit une API RESTful pour les opÃ©rations CRUD sur les clients.
 *
 * <p>Les endpoints sont accessibles via le prÃ©fixe {@code /api/client}.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 * @see ClientEntity
 * @see ClientBusinessService
 */
@RestController
@RequestMapping("api/client")
public class ClientRestController {

    /**
     * Service mÃ©tier pour les opÃ©rations clients.
     */
    private final ClientBusinessService clientBusinessService;

    /**
     * Constructeur avec injection du service mÃ©tier.
     *
     * @param clientBusinessService le service de gestion des clients
     */
    @Autowired
    public ClientRestController(ClientBusinessService clientBusinessService) {
        this.clientBusinessService = clientBusinessService;
    }

    /**
     * RÃ©cupÃ¨re la liste complÃ¨te des clients.
     *
     * @return une liste de toutes les entitÃ©s clients
     */
    @GetMapping
    public List<ClientEntity> getAllClients() {
        return clientBusinessService.retrieveAllClients();
    }

    /**
     * RÃ©cupÃ¨re un client spÃ©cifique par son identifiant.
     *
     * @param clientIdentifier l'identifiant unique du client
     * @return l'entitÃ© client correspondante
     * @throws Exception si le client n'existe pas
     */
    @GetMapping("/{id}")
    public ClientEntity getClientById(@PathVariable("id") Long clientIdentifier) throws Exception {
        return clientBusinessService.retrieveClientById(clientIdentifier);
    }

    /**
     * CrÃ©e un nouveau client dans le systÃ¨me.
     *
     * @param clientData les donnÃ©es du client Ã  crÃ©er
     * @return une rÃ©ponse HTTP avec le statut de crÃ©ation
     */
    @PostMapping
    public ResponseEntity<Void> createNewClient(@RequestBody ClientEntity clientData) {
        clientBusinessService.registerNewClient(clientData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
