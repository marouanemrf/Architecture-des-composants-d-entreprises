package com.test.tp7.web;

import com.test.tp7.Entity.Compte;
import com.test.tp7.Repository.CompteRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


import java.util.List;

@Path("/banque")
public class CompteRestJaxRSAPI {

    private final CompteRepository compteRepository;

    public CompteRestJaxRSAPI(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @GET
    @Path("/comptes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }

    @GET
    @Path("/comptes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Compte getCompte(@PathParam("id") Long id) {
        return compteRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
    }
}
