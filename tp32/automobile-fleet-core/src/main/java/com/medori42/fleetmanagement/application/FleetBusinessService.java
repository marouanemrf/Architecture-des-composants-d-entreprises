package com.medori42.fleetmanagement.application;

import com.medori42.fleetmanagement.domain.AutomobileEntity;
import com.medori42.fleetmanagement.domain.OwnerClientData;
import com.medori42.fleetmanagement.dto.AutomobileResponseDto;
import com.medori42.fleetmanagement.persistence.AutomobileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service mÃ©tier pour la gestion des opÃ©rations sur les vÃ©hicules.
 * Orchestre les appels vers la couche de persistance et la communication
 * avec le service Client Management.
 *
 * <p>Ce service enrichit les donnÃ©es des vÃ©hicules avec les informations
 * des propriÃ©taires rÃ©cupÃ©rÃ©es via REST.</p>
 *
 * @author 
 * @version 2.0.0
 * @since 2024-12
 * @see AutomobileEntity
 * @see AutomobileDataRepository
 */
@Service
public class FleetBusinessService {

    /**
     * URL de base du service Client Management.
     */
    private static final String CLIENT_SERVICE_BASE_URL = "http://localhost:8888/CLIENT-MANAGEMENT-SERVICE";

    /**
     * Repository pour l'accÃ¨s aux donnÃ©es des vÃ©hicules.
     */
    private final AutomobileDataRepository automobileRepository;

    /**
     * Template REST pour la communication inter-services.
     */
    private final RestTemplate httpRestTemplate;

    /**
     * Constructeur avec injection de dÃ©pendances.
     *
     * @param automobileRepository le repository d'accÃ¨s aux donnÃ©es vÃ©hicules
     * @param httpRestTemplate le template REST pour les appels HTTP
     */
    @Autowired
    public FleetBusinessService(AutomobileDataRepository automobileRepository, RestTemplate httpRestTemplate) {
        this.automobileRepository = automobileRepository;
        this.httpRestTemplate = httpRestTemplate;
    }

    /**
     * RÃ©cupÃ¨re tous les vÃ©hicules avec leurs informations propriÃ©taires.
     * Effectue un appel au service Client Management pour enrichir les donnÃ©es.
     *
     * @return une liste de DTOs contenant vÃ©hicules et propriÃ©taires
     */
    public List<AutomobileResponseDto> retrieveAllAutomobiles() {
        List<AutomobileEntity> automobileList = automobileRepository.findAll();
        ResponseEntity<OwnerClientData[]> clientServiceResponse = httpRestTemplate.getForEntity(
                CLIENT_SERVICE_BASE_URL + "/api/client",
                OwnerClientData[].class);
        OwnerClientData[] ownerDataArray = clientServiceResponse.getBody();
        return automobileList.stream()
                .map(automobile -> convertToResponseDto(automobile, ownerDataArray))
                .toList();
    }

    /**
     * RÃ©cupÃ¨re un vÃ©hicule spÃ©cifique par son identifiant avec le propriÃ©taire.
     *
     * @param automobileId l'identifiant unique du vÃ©hicule
     * @return le DTO contenant les informations complÃ¨tes
     * @throws Exception si le vÃ©hicule n'existe pas
     */
    public AutomobileResponseDto retrieveAutomobileById(Long automobileId) throws Exception {
        AutomobileEntity automobile = automobileRepository.findById(automobileId)
                .orElseThrow(() -> new Exception("Identifiant vÃ©hicule invalide: " + automobileId));

        OwnerClientData ownerData = httpRestTemplate.getForObject(
                CLIENT_SERVICE_BASE_URL + "/api/client/" + automobile.getOwnerClientId(),
                OwnerClientData.class);

        return buildAutomobileResponse(automobile, ownerData);
    }

    /**
     * Convertit une entitÃ© vÃ©hicule en DTO de rÃ©ponse avec recherche du propriÃ©taire.
     *
     * @param automobile l'entitÃ© vÃ©hicule Ã  convertir
     * @param ownerDataArray le tableau des donnÃ©es propriÃ©taires
     * @return le DTO de rÃ©ponse construit
     */
    private AutomobileResponseDto convertToResponseDto(AutomobileEntity automobile, OwnerClientData[] ownerDataArray) {
        OwnerClientData matchingOwner = Arrays.stream(ownerDataArray)
                .filter(owner -> owner.getOwnerIdentifier().equals(automobile.getOwnerClientId()))
                .findFirst()
                .orElse(null);

        return buildAutomobileResponse(automobile, matchingOwner);
    }

    /**
     * Construit un objet de rÃ©ponse Ã  partir des donnÃ©es vÃ©hicule et propriÃ©taire.
     *
     * @param automobile l'entitÃ© vÃ©hicule source
     * @param ownerData les donnÃ©es du propriÃ©taire
     * @return le DTO de rÃ©ponse assemblÃ©
     */
    private AutomobileResponseDto buildAutomobileResponse(AutomobileEntity automobile, OwnerClientData ownerData) {
        return AutomobileResponseDto.builder()
                .automobileIdentifier(automobile.getAutomobileId())
                .manufacturerName(automobile.getManufacturerBrand())
                .ownerInformation(ownerData)
                .registrationPlate(automobile.getLicensePlateNumber())
                .modelName(automobile.getModelDesignation())
                .build();
    }
}
