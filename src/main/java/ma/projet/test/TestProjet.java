package ma.projet.test;

import ma.projet.projets.beans.*;
import ma.projet.projets.service.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TestProjet {
    public static void main(String[] args) {
        try {
            EmployeService employeService = new EmployeService();
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            

            Employe employe1 = employeService.findAll().get(0);
            Projet projet = projetService.findAll().get(0);
            
            // Test affichage tâches réalisées par un employé
            System.out.println("=== Tâches réalisées par employé ===");
            for (Object[] result : employeService.findTachesRealiseesParEmploye(employe1.getId())) {
                System.out.println("Tâche: " + result[0] + ", Date début réelle: " + result[1] + ", Date fin réelle: " + result[2]);
            }
            
            // Test affichage projets gérés par un employé
            System.out.println("\n=== Projets gérés par employé ===");
            for (Object[] result : employeService.findProjetsGeresParEmploye(employe1.getId())) {
                System.out.println("Projet: " + result[0] + ", Date début: " + result[1] + ", Date fin: " + result[2]);
            }
            
            // Test affichage tâches planifiées pour un projet
            System.out.println("\n=== Tâches planifiées pour projet ===");
            for (Object[] result : projetService.findTachesPlanifieesPourProjet(projet.getId())) {
                System.out.println("ID: " + result[0] + ", Nom: " + result[1] + ", Date début: " + result[2] + ", Date fin: " + result[3]);
            }
            
            // Test affichage tâches réalisées avec dates réelles
            System.out.println("\n=== Tâches réalisées avec dates réelles ===");
            System.out.println("Projet : " + projet.getId() + "      Nom : " + projet.getNom() + "     Date début : " + sdf.format(projet.getDateDebut()));
            System.out.println("Liste des tâches:");
            System.out.println("Num Nom            Date Début Réelle   Date Fin Réelle");
            for (Object[] result : projetService.findTachesRealiseesAvecDatesReelles(projet.getId())) {
                System.out.println(result[0] + "  " + result[1] + "        " + result[2] + "          " + result[3]);
            }
            
            // Test tâches avec prix > 1000 DH
            System.out.println("\n=== Tâches avec prix > 1000 DH ===");
            for (Tache t : tacheService.findTachesPrixSuperieurA1000()) {
                System.out.println("Tâche: " + t.getNom() + ", Prix: " + t.getPrix() + " DH");
            }
            
            // Test tâches réalisées entre deux dates
            System.out.println("\n=== Tâches réalisées entre deux dates ===");
            Date dateDebutTest = sdf.parse("01/02/2013");
            Date dateFinTest = sdf.parse("31/03/2013");
            for (Object[] result : tacheService.findTachesRealiseesEntreDates(dateDebutTest, dateFinTest)) {
                System.out.println("Tâche: " + result[0] + ", Date début réelle: " + result[1] + ", Date fin réelle: " + result[2]);
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
