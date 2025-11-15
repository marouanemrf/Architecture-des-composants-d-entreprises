package com.tp.tp12;

import com.tp.tp12.Repo.CompteRepository;
import com.tp.tp12.entities.Compte;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.Date;

public class MCApplication {
    public static void main(String[] args) {
        SpringApplication.run(MCApplication.class, args);
    }

    CommandLineRunner start(CompteRepository compteRepository){
        return args -> {
            compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE));

            compteRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });
        };
    }
}
