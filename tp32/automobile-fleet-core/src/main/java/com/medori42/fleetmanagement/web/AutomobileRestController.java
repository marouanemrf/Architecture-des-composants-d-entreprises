package com.medori42.fleetmanagement.web;

import com.medori42.fleetmanagement.dto.AutomobileResponseDto;
import com.medori42.fleetmanagement.application.FleetBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ContrÃ´leur REST exposant les endpoints de gestion de la flotte automobile.
 * Fournit une API RESTful pour la consultation des vÃ©hicules
 * avec leurs informations propriÃ©taires.
 *
 * <p>Les endpoints sont accessibles via le prÃ©fixe {@code /api/automobile}.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 * @see AutomobileResponseDto
 * @see FleetBusinessService
 */
@RestController
@RequestMapping("api/automobile")
public class AutomobileRestController {

    /**
     * Service mÃ©tier pour les opÃ©rations sur la flotte.
     */
    private final FleetBusinessService fleetBusinessService;

    /**
     * Constructeur avec injection du service mÃ©tier.
     *
     * @param fleetBusinessService le service de gestion de flotte
     */
    @Autowired
    public AutomobileRestController(FleetBusinessService fleetBusinessService) {
        this.fleetBusinessService = fleetBusinessService;
    }

    /**
     * RÃ©cupÃ¨re la liste complÃ¨te des vÃ©hicules avec propriÃ©taires.
     *
     * @return une liste de DTOs automobile avec informations enrichies
     */
    @GetMapping
    public List<AutomobileResponseDto> getAllAutomobiles() {
        return fleetBusinessService.retrieveAllAutomobiles();
    }

    /**
     * RÃ©cupÃ¨re un vÃ©hicule spÃ©cifique par son identifiant.
     *
     * @param automobileIdentifier l'identifiant unique du vÃ©hicule
     * @return le DTO automobile avec informations du propriÃ©taire
     * @throws Exception si le vÃ©hicule n'existe pas
     */
    @GetMapping("/{id}")
    public AutomobileResponseDto getAutomobileById(@PathVariable("id") Long automobileIdentifier) throws Exception {
        return fleetBusinessService.retrieveAutomobileById(automobileIdentifier);
    }
}
