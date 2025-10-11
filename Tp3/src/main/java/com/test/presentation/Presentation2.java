package com.test.presentation;

import com.test.metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.test.dao","com.test.metier","com.test.config"})
public class Presentation2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

         ctx.getEnvironment().setActiveProfiles("dev");
         ctx.getEnvironment().setActiveProfiles("prod");
         ctx.getEnvironment().setActiveProfiles("file");
         ctx.getEnvironment().setActiveProfiles("api");

        ctx.register(Presentation2.class);
        ctx.refresh();

        IMetier metier = ctx.getBean(IMetier.class);
        System.out.println("Résultat = " + metier.calcul());
        ctx.close();
    }
}