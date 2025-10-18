package ma.projet.civil.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "femme")
@NamedQuery(name = "Femme.findByMariagesAuMoinsDeuxFois", query = "select f from Femme f where size(f.mariages) >= 2")
@NamedNativeQuery(
    name = "Femme.findNombreEnfantsEntreDates",
    query = "SELECT COUNT(*) FROM mariage m WHERE m.femme_id = :femmeId AND m.date_debut BETWEEN :dateDebut AND :dateFin"
)
public class Femme extends Personne {
    @OneToMany(mappedBy = "femme", cascade = CascadeType.ALL)
    private List<Mariage> mariages;
    
    public Femme() {}
    
    public Femme(String nom, String prenom, String telephone, String adresse, java.util.Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
    
    public List<Mariage> getMariages() { return mariages; }
    public void setMariages(List<Mariage> mariages) { this.mariages = mariages; }
}