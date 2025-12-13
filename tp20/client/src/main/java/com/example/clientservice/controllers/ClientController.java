package com.example.clientservice.controllers;

import com.example.clientservice.entities.Client;
import com.example.clientservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Client client = service.findById(id);
            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client) {
        Client savedClient = service.addClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }
}