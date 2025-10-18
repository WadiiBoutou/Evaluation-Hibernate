package ma.projet.civil.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mariage")
public class Mariage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    
    @Column(name = "nbr_enfant")
    private int nbrEnfant;
    
    @ManyToOne
    @JoinColumn(name = "homme_id")
    private Homme homme;
    
    @ManyToOne
    @JoinColumn(name = "femme_id")
    private Femme femme;
    
    public Mariage() {}
    
    public Mariage(Date dateDebut, Date dateFin, int nbrEnfant) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrEnfant = nbrEnfant;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    
    public int getNbrEnfant() { return nbrEnfant; }
    public void setNbrEnfant(int nbrEnfant) { this.nbrEnfant = nbrEnfant; }
    
    public Homme getHomme() { return homme; }
    public void setHomme(Homme homme) { this.homme = homme; }
    
    public Femme getFemme() { return femme; }
    public void setFemme(Femme femme) { this.femme = femme; }
}
