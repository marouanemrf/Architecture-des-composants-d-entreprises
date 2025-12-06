package com.test.compteclient.controllers;


import com.test.compteclient.entities.Compte;
import com.test.compteclient.repositories.CompteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CompteController {

    private final CompteRepository compteRepository;

    public CompteController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    // ✅ GET ALL
    @GetMapping("/comptes")
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // ✅ POST
    @PostMapping("/comptes")
    public Compte saveCompte(@RequestBody Compte compte) {
        return compteRepository.save(compte);
    }
}
