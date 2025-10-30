package ma.projet.projets.service;


import ma.projet.dao.IDao;
import ma.projet.projets.beans.Tache;
import ma.projet.projets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TacheService implements IDao<Tache> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(Tache o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(Tache o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(Tache o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public Tache findById(int id) {
        Session session = sessionFactory.openSession();
        Tache tache = session.get(Tache.class, id);
        session.close();
        return tache;
    }
    
    @Override
    public List<Tache> findAll() {
        Session session = sessionFactory.openSession();
        List<Tache> taches = session.createQuery("from Tache").list();
        session.close();
        return taches;
    }
    
    public List<Tache> findTachesPrixSuperieurA1000() {
        Session session = sessionFactory.openSession();
        Query<Tache> query = session.createNamedQuery("Tache.findByPrixSuperieurA1000");
        List<Tache> taches = query.list();
        session.close();
        return taches;
    }
    
    public List<Object[]> findTachesRealiseesEntreDates(Date dateDebut, Date dateFin) {
        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
            "select t.nom, et.dateDebutReelle, et.dateFinReelle from Tache t " +
            "join t.employeTaches et " +
            "where et.dateDebutReelle between :dateDebut and :dateFin"
        );
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
}
