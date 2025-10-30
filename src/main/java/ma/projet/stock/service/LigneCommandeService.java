package ma.projet.stock.service;


import ma.projet.dao.IDao;
import ma.projet.stock.beans.LigneCommandeProduit;
import ma.projet.stock.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LigneCommandeService implements IDao<LigneCommandeProduit> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(LigneCommandeProduit o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(LigneCommandeProduit o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(LigneCommandeProduit o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public LigneCommandeProduit findById(int id) {
        Session session = sessionFactory.openSession();
        LigneCommandeProduit ligneCommande = session.get(LigneCommandeProduit.class, id);
        session.close();
        return ligneCommande;
    }
    
    @Override
    public List<LigneCommandeProduit> findAll() {
        Session session = sessionFactory.openSession();
        List<LigneCommandeProduit> ligneCommandes = session.createQuery("from LigneCommandeProduit").list();
        session.close();
        return ligneCommandes;
    }
}
