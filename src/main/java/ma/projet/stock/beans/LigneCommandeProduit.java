package ma.projet.stock.beans;

import javax.persistence.*;

@Entity
@Table(name = "ligne_commande_produit")
public class LigneCommandeProduit {
    @EmbeddedId
    private LigneCommandeProduitId id = new LigneCommandeProduitId();
    
    @Column(name = "quantite")
    private int quantite;
    
    @MapsId("produitId")
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;
    
    @MapsId("commandeId")
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;
    
    public LigneCommandeProduit() {}
    
    public LigneCommandeProduit(int quantite) {
        this.quantite = quantite;
    }
    
    public LigneCommandeProduitId getId() { return id; }
    public void setId(LigneCommandeProduitId id) { this.id = id; }
    
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    
    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
    
    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }
}
