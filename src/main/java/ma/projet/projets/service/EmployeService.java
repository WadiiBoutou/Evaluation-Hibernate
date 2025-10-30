package ma.projet.projets.service;


import ma.projet.dao.IDao;
import ma.projet.projets.beans.Employe;
import ma.projet.projets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeService implements IDao<Employe> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(Employe o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(Employe o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(Employe o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public Employe findById(int id) {
        Session session = sessionFactory.openSession();
        Employe employe = session.get(Employe.class, id);
        session.close();
        return employe;
    }
    
    @Override
    public List<Employe> findAll() {
        Session session = sessionFactory.openSession();
        List<Employe> employes = session.createQuery("from Employe").list();
        session.close();
        return employes;
    }
    
    public List<Object[]> findTachesRealiseesParEmploye(int employeId) {
        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
            "select t.nom, et.dateDebutReelle, et.dateFinReelle from Tache t " +
            "join t.employeTaches et " +
            "where et.employe.id = :employeId"
        );
        query.setParameter("employeId", employeId);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
    
    public List<Object[]> findProjetsGeresParEmploye(int employeId) {
        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
            "select p.nom, p.dateDebut, p.dateFin from Projet p " +
            "where p.chefProjet.id = :employeId"
        );
        query.setParameter("employeId", employeId);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
}
