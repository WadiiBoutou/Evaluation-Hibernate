package ma.projet.stock.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "produit")
@NamedQuery(name = "Produit.findByPrixSuperieurA100", query = "from Produit p where p.prix > 100")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "reference")
    private String reference;
    
    @Column(name = "prix")
    private float prix;
    
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<LigneCommandeProduit> ligneCommandeProduits;
    
    public Produit() {}
    
    public Produit(String reference, float prix) {
        this.reference = reference;
        this.prix = prix;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    
    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }
    
    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
    
    public List<LigneCommandeProduit> getLigneCommandeProduits() { return ligneCommandeProduits; }
    public void setLigneCommandeProduits(List<LigneCommandeProduit> ligneCommandeProduits) { this.ligneCommandeProduits = ligneCommandeProduits; }
}
