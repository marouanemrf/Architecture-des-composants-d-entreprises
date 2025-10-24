package ma.projet;

import ma.projet.beans.Homme;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.services.HommeService;
import ma.projet.services.FemmeService;
import ma.projet.services.MariageService;
import ma.projet.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class TestApp {

    public static void main(String[] args) {
        HommeService hommeService = new HommeService();
        FemmeService femmeService = new FemmeService();
        MariageService mariageService = new MariageService();

        createMenAndWomen(hommeService, femmeService);

        displayWomen(femmeService);

        displayOldestWoman(femmeService);

        displayWivesOfMan(hommeService, mariageService, 1);

        displayChildrenCountOfWoman(femmeService, 1, new Date(2022, 01, 01), new Date(2023, 01, 01));

        displayWomenMarriedMoreThanOnce(femmeService);

        displayMenMarriedToFourWomen(mariageService, new Date(2020, 01, 01), new Date(2025, 01, 01));

        displayMarriagesOfMan(mariageService, 1);
    }

    private static void createMenAndWomen(HommeService hommeService, FemmeService femmeService) {
        for (int i = 1; i <= 5; i++) {
            Homme homme = new Homme();
            homme.setNom("Homme" + i);
            homme.setPrenom("PrenomHomme" + i);
            homme.setTelephone("123456789" + i);
            homme.setAdresse("Adresse Homme " + i);
            homme.setDateNaissance(new Date(1980 + i, 5, 15));  // 1980 + i pour diversifier les âges
            hommeService.save(homme);
        }

        for (int i = 1; i <= 10; i++) {
            Femme femme = new Femme();
            femme.setNom("Femme" + i);
            femme.setPrenom("PrenomFemme" + i);
            femme.setTelephone("987654321" + i);
            femme.setAdresse("Adresse Femme " + i);
            femme.setDateNaissance(new Date(1990 + i, 3, 20));  // 1990 + i pour diversifier les âges
            femmeService.save(femme);
        }
    }

    private static void displayWomen(FemmeService femmeService) {
        List<Femme> women = femmeService.findAll();
        System.out.println("Liste des femmes :");
        for (Femme femme : women) {
            System.out.println(femme.getNom() + " " + femme.getPrenom());
        }
    }

    private static void displayOldestWoman(FemmeService femmeService) {
        Femme oldestWoman = femmeService.findAll().stream()
                .min((w1, w2) -> w1.getDateNaissance().compareTo(w2.getDateNaissance()))
                .orElse(null);

        if (oldestWoman != null) {
            System.out.println("La femme la plus âgée : " + oldestWoman.getNom() + " " + oldestWoman.getPrenom());
        }
    }

    private static void displayWivesOfMan(HommeService hommeService, MariageService mariageService, int hommeId) {
        System.out.println("Épouses de l'homme avec ID " + hommeId + " :");
        List<Object[]> wives = mariageService.findMarriagesByMan(hommeId);
        for (Object[] marriage : wives) {
            Femme wife = (Femme) marriage[0];
            System.out.println(wife.getNom() + " " + wife.getPrenom());
        }
    }

    private static void displayChildrenCountOfWoman(FemmeService femmeService, int femmeId, Date startDate, Date endDate) {
        long childrenCount = femmeService.getChildrenCountBetweenDates(femmeId, startDate, endDate);
        System.out.println("Nombre d'enfants de la femme ID " + femmeId + " entre les dates " + startDate + " et " + endDate + ": " + childrenCount);
    }

    private static void displayWomenMarriedMoreThanOnce(FemmeService femmeService) {
        System.out.println("Femmes mariées deux fois ou plus :");
        List<Femme> women = femmeService.findWomenMarriedAtLeastTwice();
        for (Femme wife : women) {
            System.out.println(wife.getNom() + " " + wife.getPrenom());
        }
    }

    private static void displayMenMarriedToFourWomen(MariageService mariageService, Date startDate, Date endDate) {
        long menCount = mariageService.countMenMarriedToFourWomenBetweenDates(startDate, endDate);
        System.out.println("Nombre d'hommes mariés à 4 femmes entre les dates " + startDate + " et " + endDate + ": " + menCount);
    }

    private static void displayMarriagesOfMan(MariageService mariageService, int hommeId) {
        System.out.println("Mariages de l'homme avec ID " + hommeId + " :");
        List<Object[]> marriages = mariageService.findMarriagesByMan(hommeId);
        for (Object[] marriage : marriages) {
            Femme wife = (Femme) marriage[0];
            Date start = (Date) marriage[1];
            Date end = (Date) marriage[2];
            int children = (int) marriage[3];
            System.out.println("Femme: " + wife.getNom() + " " + wife.getPrenom() +
                    ", Date de début: " + start +
                    ", Date de fin: " + end +
                    ", Nombre d'enfants: " + children);
        }
    }
}
