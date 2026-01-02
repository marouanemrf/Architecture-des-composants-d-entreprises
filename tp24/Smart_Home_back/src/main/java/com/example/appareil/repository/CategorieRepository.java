package com.example.appareil.repository;

import com.example.appareil.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour les catégories
 */
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
