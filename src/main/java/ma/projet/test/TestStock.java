package ma.projet.test;

import ma.projet.stock.beans.*;
import ma.projet.stock.service.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TestStock {
    public static void main(String[] args) {
        try {
            CategorieService categorieService = new CategorieService();
            ProduitService produitService = new ProduitService();
            CommandeService commandeService = new CommandeService();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            // Test affichage produits par catégorie
            System.out.println("=== Produits par catégorie ===");
            for (Categorie cat : categorieService.findAll()) {
                System.out.println("Catégorie: " + cat.getLibelle());
                for (Produit p : produitService.findByCategorie(cat.getId())) {
                    System.out.println("  - " + p.getReference() + " : " + p.getPrix() + " DH");
                }
            }
            
            // Test affichage produits commandés entre deux dates
            System.out.println("\n=== Produits commandés entre deux dates ===");
            Date dateDebut = sdf.parse("01/03/2013");
            Date dateFin = sdf.parse("31/03/2013");
            for (Object[] result : produitService.findProduitsCommandesEntreDates(dateDebut, dateFin)) {
                System.out.println("Référence: " + result[0] + ", Prix: " + result[1] + " DH, Quantité: " + result[2]);
            }
            
            // Test affichage produits d'une commande
            System.out.println("\n=== Produits de la commande ===");

            Commande commande = commandeService.findAll().get(0);
            System.out.println("Commande : " + commande.getId() + "     Date : " + sdf.format(commande.getDate()));
            System.out.println("Liste des produits :");
            System.out.println("Référence   Prix    Quantité");
            for (Object[] result : produitService.findProduitsByCommande(commande.getId())) {
                System.out.println(result[0] + "        " + result[1] + " DH  " + result[2]);
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
