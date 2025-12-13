package ma.projet.grpc.config;

import ma.projet.grpc.entities.Compte;
import ma.projet.grpc.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final CompteRepository compteRepository;
    
    public DataInitializer(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (compteRepository.count() == 0) {
            // Create sample accounts
            Compte compte1 = new Compte();
            compte1.setSolde(5000.0f);
            compte1.setDateCreation("2024-01-15");
            compte1.setType("COURANT");
            compteRepository.save(compte1);
            
            Compte compte2 = new Compte();
            compte2.setSolde(10000.0f);
            compte2.setDateCreation("2024-02-20");
            compte2.setType("EPARGNE");
            compteRepository.save(compte2);
            
            Compte compte3 = new Compte();
            compte3.setSolde(2500.0f);
            compte3.setDateCreation("2024-03-10");
            compte3.setType("COURANT");
            compteRepository.save(compte3);
            
            Compte compte4 = new Compte();
            compte4.setSolde(15000.0f);
            compte4.setDateCreation("2024-04-05");
            compte4.setType("EPARGNE");
            compteRepository.save(compte4);
            
            Compte compte5 = new Compte();
            compte5.setSolde(7500.0f);
            compte5.setDateCreation("2024-05-12");
            compte5.setType("COURANT");
            compteRepository.save(compte5);
            
            System.out.println("Sample data initialized: " + compteRepository.count() + " accounts created");
        }
    }
}

