package com.example.client.repositories;

import com.example.client.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour les clients
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
