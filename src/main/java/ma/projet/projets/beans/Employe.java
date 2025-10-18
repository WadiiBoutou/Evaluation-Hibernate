package ma.projet.projets.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employe")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "prenom")
    private String prenom;
    
    @Column(name = "telephone")
    private String telephone;
    
    @OneToMany(mappedBy = "chefProjet", cascade = CascadeType.ALL)
    private List<Projet> projetsGeres;
    
    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<EmployeTache> employeTaches;
    
    public Employe() {}
    
    public Employe(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    public List<Projet> getProjetsGeres() { return projetsGeres; }
    public void setProjetsGeres(List<Projet> projetsGeres) { this.projetsGeres = projetsGeres; }
    
    public List<EmployeTache> getEmployeTaches() { return employeTaches; }
    public void setEmployeTaches(List<EmployeTache> employeTaches) { this.employeTaches = employeTaches; }
}
