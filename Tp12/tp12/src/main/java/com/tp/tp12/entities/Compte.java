package com.tp.tp12.entities;

import ch.qos.logback.core.net.server.Client;
import com.tp.tp12.TypeCompte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double solde;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    private TypeCompte type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Compte(Long id, double solde, Date dateCreation, TypeCompte typeCompte) {
        this.id = id;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.type = type;
    }
}
