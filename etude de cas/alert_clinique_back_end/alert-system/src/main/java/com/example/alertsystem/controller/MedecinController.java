package com.example.alertsystem.controller;


import org.springframework.web.bind.annotation.*;
import com.example.alertsystem.service.MedecinService;
import com.example.alertsystem.entities.Medecin;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {
    private final MedecinService medecinService;

    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    @GetMapping
    public List<Medecin> getAllMedecins() { return medecinService.getAllMedecins(); }

    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id) {
        return medecinService.getMedecinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medecin createMedecin(@RequestBody Medecin medecin) { return medecinService.saveMedecin(medecin); }

    @PutMapping("/{id}")
    public ResponseEntity<Medecin> updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        return medecinService.updateMedecin(id, medecin)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteMedecin(@PathVariable Long id) { medecinService.deleteMedecin(id); }
}

