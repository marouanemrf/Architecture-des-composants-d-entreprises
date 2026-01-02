package com.example.appareil.repository;

import com.example.appareil.entity.Appareil;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour les appareils
 */
public interface AppareilRepository extends JpaRepository<Appareil, Long> {
}
