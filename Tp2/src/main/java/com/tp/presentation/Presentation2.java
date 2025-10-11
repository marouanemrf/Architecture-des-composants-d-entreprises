package com.tp.presentation;

import com.tp.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.tp.dao", "com.tp.metier"})
public class Presentation2 {

    public static void main(String[] args) {
        // Initialisation du contexte Spring et scan des composants
        ApplicationContext context = new AnnotationConfigApplicationContext(Presentation2.class);

        // Récupération du bean IMetier
        IMetier metier = context.getBean(IMetier.class);

        // Utilisation du bean pour afficher le résultat du calcul
        System.out.println("Résultat = " + metier.calcul());
    }
}
