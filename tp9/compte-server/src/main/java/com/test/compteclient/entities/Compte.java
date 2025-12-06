package com.test.compteclient.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double solde;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    private String type;

    public Compte() {}

    public Compte(double solde, Date dateCreation, String type) {
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.type = type;
    }

    public Long getId() { return id; }
    public double getSolde() { return solde; }
    public Date getDateCreation() { return dateCreation; }
    public String getType() { return type; }

    public void setId(Long id) { this.id = id; }
    public void setSolde(double solde) { this.solde = solde; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    public void setType(String type) { this.type = type; }
}
