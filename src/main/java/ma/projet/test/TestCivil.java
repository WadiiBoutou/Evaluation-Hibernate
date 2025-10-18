package ma.projet.test;

import ma.projet.civil.beans.*;
import ma.projet.civil.service.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TestCivil {
    public static void main(String[] args) {
        try {
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            

            Homme homme1 = hommeService.findAll().get(0);
            Femme femme1 = femmeService.findAll().get(0);
            
            // Test affichage liste des femmes
            System.out.println("=== Liste des femmes ===");
            for (Femme f : femmeService.findAll()) {
                System.out.println(f.getNom() + " " + f.getPrenom() + " - " + sdf.format(f.getDateNaissance()));
            }
            
            // Test femme la plus âgée
            System.out.println("\n=== Femme la plus âgée ===");
            Femme femmeLaPlusAgee = femmeService.findFemmeLaPlusAgee();
            if (femmeLaPlusAgee != null) {
                System.out.println(femmeLaPlusAgee.getNom() + " " + femmeLaPlusAgee.getPrenom() + " - " + sdf.format(femmeLaPlusAgee.getDateNaissance()));
            }
            
            // Test épouses d'un homme
            System.out.println("\n=== Épouses d'un homme ===");
            Date dateDebut = sdf.parse("01/01/1990");
            Date dateFin = sdf.parse("31/12/2000");
            for (Object[] result : hommeService.findEpousesEntreDates(homme1.getId(), dateDebut, dateFin)) {
                System.out.println("Femme: " + result[0] + " " + result[1] + ", Date début: " + result[2] + ", Date fin: " + result[3] + ", Nbr enfants: " + result[4]);
            }
            
            // Test nombre d'enfants d'une femme entre deux dates
            System.out.println("\n=== Nombre d'enfants d'une femme entre deux dates ===");
            Long nombreEnfants = femmeService.findNombreEnfantsEntreDates(femme1.getId(), dateDebut, dateFin);
            System.out.println("Femme : " + femme1.getNom() + " " + femme1.getPrenom() + "   Date entre : " + sdf.format(dateDebut) + " - " + sdf.format(dateFin) + "   Nombre d'Enfants : " + nombreEnfants);
            
            // Test femmes mariées deux fois ou plus
            System.out.println("\n=== Femmes mariées deux fois ou plus ===");
            for (Femme f : femmeService.findFemmesMarieesAuMoinsDeuxFois()) {
                System.out.println(f.getNom() + " " + f.getPrenom());
            }
            
            // Test hommes mariés à quatre femmes entre deux dates
            System.out.println("\n=== Hommes mariés à quatre femmes entre deux dates ===");
            for (Homme h : femmeService.findHommesMariesAQuatreFemmesEntreDates(dateDebut, dateFin)) {
                System.out.println(h.getNom() + " " + h.getPrenom());
            }
            
            // Test mariages d'un homme avec détails
            System.out.println("\n=== Mariages d'un homme avec détails ===");
            System.out.println("Nom : " + homme1.getNom() + " " + homme1.getPrenom());
            System.out.println("Mariages En Cours :");
            int count = 1;
            for (Object[] result : hommeService.findMariagesAvecDetails(homme1.getId())) {
                if (result[3] == null) { // dateFin est null
                    System.out.println(count + ". Femme : " + result[0] + " " + result[1] + "   Date Début : " + result[2] + "    Nbr Enfants : " + result[4]);
                    count++;
                }
            }
            System.out.println("\nMariages échoués :");
            count = 1;
            for (Object[] result : hommeService.findMariagesAvecDetails(homme1.getId())) {
                if (result[3] != null) { // dateFin n'est pas null
                    System.out.println(count + ". Femme : " + result[0] + " " + result[1] + "  Date Début : " + result[2] + "    Date Fin : " + result[3] + "    Nbr Enfants : " + result[4]);
                    count++;
                }
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
