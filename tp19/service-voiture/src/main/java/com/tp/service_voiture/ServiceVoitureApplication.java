package com.tp.service_voiture;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.tp.service_voiture.VoitureRepository;
import com.tp.service_voiture.ClientService;
import com.tp.service_voiture.Client;
import com.tp.service_voiture.Voiture;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.tp.service_voiture")
@EnableJpaRepositories(basePackages = "com.tp.service_voiture")
public class ServiceVoitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceVoitureApplication.class, args);
	}

	@Bean
CommandLineRunner initialiserBaseH2(VoitureRepository voitureRepository, ClientService clientService){
    return args -> {
        try {
            Client c1 = clientService.clientById(2L);
            Client c2 = clientService.clientById(1L);
            System.out.println("**************************");
            System.out.println("Id est :" + c2.getId());
            System.out.println("Nom est :" + c2.getNom());
            System.out.println("**************************");
            System.out.println("**************************");
            System.out.println("Id est :" + c1.getId());
            System.out.println("Nom est :" + c1.getNom());
            System.out.println("Age est :" + c1.getAge());
            System.out.println("**************************");
            Voiture v1 = new Voiture();
            v1.setMarque("Toyota");
            v1.setMatricule("A 25 333");
            v1.setModel("Corolla");
            v1.setId_client(1L);
            v1.setClient(c2);
            voitureRepository.save(v1);
            
            Voiture v2 = new Voiture();
            v2.setMarque("Renault");
            v2.setMatricule("B 6 3456");
            v2.setModel("Megane");
            v2.setId_client(1L);
            v2.setClient(c2);
            voitureRepository.save(v2);
            
            Voiture v3 = new Voiture();
            v3.setMarque("Peugeot");
            v3.setMatricule("A 55 4444");
            v3.setModel("301");
            v3.setId_client(2L);
            v3.setClient(c1);
            voitureRepository.save(v3);
            System.out.println("Données de test chargées avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation: SERVICE-CLIENT n'est pas disponible. " + e.getMessage());
            System.out.println("Création de données de test sans client...");
            // Créer des voitures sans client pour permettre le test
            Voiture v1 = new Voiture();
            v1.setMarque("Toyota");
            v1.setMatricule("A 25 333");
            v1.setModel("Corolla");
            v1.setId_client(1L);
            voitureRepository.save(v1);
            
            Voiture v2 = new Voiture();
            v2.setMarque("Renault");
            v2.setMatricule("B 6 3456");
            v2.setModel("Megane");
            v2.setId_client(1L);
            voitureRepository.save(v2);
            
            Voiture v3 = new Voiture();
            v3.setMarque("Peugeot");
            v3.setMatricule("A 55 4444");
            v3.setModel("301");
            v3.setId_client(2L);
            voitureRepository.save(v3);
            System.out.println("Données de test créées (sans client) !");
        }
    };
}

	

}
