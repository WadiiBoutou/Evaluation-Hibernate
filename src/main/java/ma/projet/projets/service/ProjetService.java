package ma.projet.projets.service;

import ma.projet.dao.IDao;
import ma.projet.projets.beans.Projet;
import ma.projet.projets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProjetService implements IDao<Projet> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(Projet o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(Projet o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(Projet o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public Projet findById(int id) {
        Session session = sessionFactory.openSession();
        Projet projet = session.get(Projet.class, id);
        session.close();
        return projet;
    }
    
    @Override
    public List<Projet> findAll() {
        Session session = sessionFactory.openSession();
        List<Projet> projets = session.createQuery("from Projet").list();
        session.close();
        return projets;
    }
    
    public List<Object[]> findTachesPlanifieesPourProjet(int projetId) {
        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
            "select t.id, t.nom, t.dateDebut, t.dateFin from Tache t " +
            "where t.projet.id = :projetId"
        );
        query.setParameter("projetId", projetId);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
    
    public List<Object[]> findTachesRealiseesAvecDatesReelles(int projetId) {
        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
            "select t.id, t.nom, et.dateDebutReelle, et.dateFinReelle from Tache t " +
            "join t.employeTaches et " +
            "where t.projet.id = :projetId"
        );
        query.setParameter("projetId", projetId);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
}
