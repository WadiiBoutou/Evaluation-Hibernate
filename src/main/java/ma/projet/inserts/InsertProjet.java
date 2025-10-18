package ma.projet.inserts;

import ma.projet.projets.beans.*;
import ma.projet.projets.service.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class InsertProjet {
    public static void main(String[] args) {
        try {
            EmployeService employeService = new EmployeService();
            ProjetService projetService = new ProjetService();
            TacheService tacheService = new TacheService();
            EmployeTacheService employeTacheService = new EmployeTacheService();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            

            Employe employe1 = new Employe("ALAMI", "Ahmed", "0612345678");
            Employe employe2 = new Employe("BENALI", "Fatima", "0612345679");
            employeService.create(employe1);
            employeService.create(employe2);
            

            Date dateDebut = sdf.parse("14/01/2013");
            Date dateFin = sdf.parse("14/12/2013");
            Projet projet = new Projet("Gestion de stock", dateDebut, dateFin);
            projet.setChefProjet(employe1);
            projetService.create(projet);
            

            Date dateDebutTache1 = sdf.parse("10/02/2013");
            Date dateFinTache1 = sdf.parse("20/02/2013");
            Tache tache1 = new Tache("Analyse", dateDebutTache1, dateFinTache1, 1500.0);
            tache1.setProjet(projet);
            tacheService.create(tache1);
            
            Date dateDebutTache2 = sdf.parse("10/03/2013");
            Date dateFinTache2 = sdf.parse("15/03/2013");
            Tache tache2 = new Tache("Conception", dateDebutTache2, dateFinTache2, 2000.0);
            tache2.setProjet(projet);
            tacheService.create(tache2);
            
            Date dateDebutTache3 = sdf.parse("10/04/2013");
            Date dateFinTache3 = sdf.parse("25/04/2013");
            Tache tache3 = new Tache("Développement", dateDebutTache3, dateFinTache3, 3000.0);
            tache3.setProjet(projet);
            tacheService.create(tache3);
            

            Date dateDebutReelle1 = sdf.parse("10/02/2013");
            Date dateFinReelle1 = sdf.parse("20/02/2013");
            EmployeTache et1 = new EmployeTache(dateDebutReelle1, dateFinReelle1);
            et1.setEmploye(employe1);
            et1.setTache(tache1);
            employeTacheService.create(et1);
            
            Date dateDebutReelle2 = sdf.parse("10/03/2013");
            Date dateFinReelle2 = sdf.parse("15/03/2013");
            EmployeTache et2 = new EmployeTache(dateDebutReelle2, dateFinReelle2);
            et2.setEmploye(employe2);
            et2.setTache(tache2);
            employeTacheService.create(et2);
            
            Date dateDebutReelle3 = sdf.parse("10/04/2013");
            Date dateFinReelle3 = sdf.parse("25/04/2013");
            EmployeTache et3 = new EmployeTache(dateDebutReelle3, dateFinReelle3);
            et3.setEmploye(employe1);
            et3.setTache(tache3);
            employeTacheService.create(et3);
            
            System.out.println("Projet data inserted successfully!");
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
