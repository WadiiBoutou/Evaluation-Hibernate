package ma.projet.inserts;

import ma.projet.stock.beans.*;
import ma.projet.stock.service.*;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class InsertStock {
    public static void main(String[] args) {
        try {
            CategorieService categorieService = new CategorieService();
            ProduitService produitService = new ProduitService();
            CommandeService commandeService = new CommandeService();
            LigneCommandeService ligneCommandeService = new LigneCommandeService();
            

            Categorie categorie1 = new Categorie("CAT001", "Ordinateurs");
            Categorie categorie2 = new Categorie("CAT002", "Accessoires");
            categorieService.create(categorie1);
            categorieService.create(categorie2);
            

            Produit produit1 = new Produit("ES12", 120.0f);
            produit1.setCategorie(categorie1);
            produitService.create(produit1);
            
            Produit produit2 = new Produit("ZR85", 100.0f);
            produit2.setCategorie(categorie1);
            produitService.create(produit2);
            
            Produit produit3 = new Produit("EE85", 200.0f);
            produit3.setCategorie(categorie2);
            produitService.create(produit3);
            

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateCommande = sdf.parse("14/03/2013");
            Commande commande = new Commande(dateCommande);
            commandeService.create(commande);
            

            LigneCommandeProduit ligne1 = new LigneCommandeProduit(7);
            ligne1.setProduit(produit1);
            ligne1.setCommande(commande);
            ligneCommandeService.create(ligne1);
            
            LigneCommandeProduit ligne2 = new LigneCommandeProduit(14);
            ligne2.setProduit(produit2);
            ligne2.setCommande(commande);
            ligneCommandeService.create(ligne2);
            
            LigneCommandeProduit ligne3 = new LigneCommandeProduit(5);
            ligne3.setProduit(produit3);
            ligne3.setCommande(commande);
            ligneCommandeService.create(ligne3);
            
            System.out.println("Stock data inserted successfully!");
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
