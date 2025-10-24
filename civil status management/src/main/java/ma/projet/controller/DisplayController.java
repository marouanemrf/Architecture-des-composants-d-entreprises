package ma.projet.controller;

import ma.projet.beans.Mariage;
import ma.projet.beans.Femme;
import ma.projet.services.MariageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DisplayController {

    private final MariageService mariageService;

    public DisplayController(MariageService mariageService) {
        this.mariageService = mariageService;
    }

    @GetMapping("/afficherMariages")
    public String afficherMariages(@RequestParam int hommeId) {
        // Récupérer tous les mariages d'un homme donné
        List<Mariage> mariages = mariageService.findMarriagesByMan(hommeId);

        // Séparer les mariages en cours et échoués
        List<Mariage> mariagesEnCours = mariages.stream()
                .filter(mariage -> mariage.getDateFin() == null || mariage.getDateFin().after(new Date()))
                .collect(Collectors.toList());

        List<Mariage> mariagesEchoues = mariages.stream()
                .filter(mariage -> mariage.getDateFin() != null && mariage.getDateFin().before(new Date()))
                .collect(Collectors.toList());

        // Construire la réponse avec les détails
        StringBuilder response = new StringBuilder();
        response.append("Nom: ").append();

        response.append("Mariages En Cours:\n");
        for (Mariage mariage : mariagesEnCours) {
            Femme femme = mariage.getFemme(); // Obtenir la femme du mariage
            response.append("1. Femme : ").append(femme.getNom()).append(" ").append(femme.getPrenom())
                    .append("   Date Début : ").append(mariage.getDateDebut())
                    .append("   Nbr Enfants : ").append(mariage.getNbrEnfant()).append("\n");
        }

        response.append("\nMariages Échoués:\n");
        for (Mariage mariage : mariagesEchoues) {
            Femme femme = mariage.getFemme();
            response.append("1. Femme : ").append(femme.getNom()).append(" ").append(femme.getPrenom())
                    .append("   Date Début : ").append(mariage.getDateDebut())
                    .append("   Date Fin : ").append(mariage.getDateFin())
                    .append("   Nbr Enfants : ").append(mariage.getNbrEnfant()).append("\n");
        }

        return response.toString();
    }
}

