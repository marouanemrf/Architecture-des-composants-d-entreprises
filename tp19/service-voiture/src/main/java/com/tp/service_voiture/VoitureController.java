package com.tp.service_voiture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<EntityModel<Voiture>> getAllVoitures() {
        return voitureRepository.findAll().stream()
                .map(voiture -> {
                    EntityModel<Voiture> resource = EntityModel.of(voiture);
                    try {
                        Client client = clientService.clientById(voiture.getId_client());
                        voiture.setClient(client);
                    } catch (Exception e) {
                        // Si le client n'est pas disponible, on continue sans
                    }
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VoitureController.class)
                            .getVoitureById(voiture.getId())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<Voiture> getVoitureById(@PathVariable Long id) {
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voiture non trouvée"));
        
        try {
            Client client = clientService.clientById(voiture.getId_client());
            voiture.setClient(client);
        } catch (Exception e) {
            // Si le client n'est pas disponible, on continue sans
        }
        
        EntityModel<Voiture> resource = EntityModel.of(voiture);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VoitureController.class)
                .getVoitureById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VoitureController.class)
                .getAllVoitures()).withRel("voitures"));
        
        return resource;
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Voiture>> getVoituresByClientId(@PathVariable Long id) {
        try {
            Client client = clientService.clientById(id);
            if (client == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            
            List<Voiture> voitures = voitureRepository.findAll().stream()
                    .filter(v -> v.getId_client() != null && v.getId_client().equals(id))
                    .map(v -> {
                        v.setClient(client);
                        return v;
                    })
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(voitures);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
