package com.tp.tp12.entities;

import com.tp.tp12.TypeCompte;

@Projection(name = "mobile", types = Compte.class)
public interface CompteProjection2 {
    double getSolde();
    TypeCompte getType();
}
