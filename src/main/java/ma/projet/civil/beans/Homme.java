package ma.projet.civil.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "homme")
@NamedQuery(name = "Homme.findByMariagesEntreDates", query = "select h from Homme h join h.mariages m where h.id = :hommeId and m.dateDebut between :dateDebut and :dateFin")
@NamedQuery(name = "Homme.findByMariagesAQuatreFemmes", query = "select h from Homme h where size(h.mariages) >= 4")
public class Homme extends Personne {
    @OneToMany(mappedBy = "homme", cascade = CascadeType.ALL)
    private List<Mariage> mariages;
    
    public Homme() {}
    
    public Homme(String nom, String prenom, String telephone, String adresse, java.util.Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
    }
    
    public List<Mariage> getMariages() { return mariages; }
    public void setMariages(List<Mariage> mariages) { this.mariages = mariages; }
}
