package ma.ws.jaxrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class JaxrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaxrsApplication.class, args);
	}

    @Bean
    CommandLineRunner start(CompteRepository compteRepository){
        return args -> {
            compteRepository.save(new Compte(null, Math.random()*9000, new java.util.Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, Math.random()*9000, new java.util.Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, Math.random()*9000, new java.util.Date(), TypeCompte.EPARGNE));
            compteRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });
        };
    }

}
