package ma.projet.stock.service;


import ma.projet.dao.IDao;
import ma.projet.stock.beans.Commande;
import ma.projet.stock.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CommandeService implements IDao<Commande> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(Commande o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(Commande o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(Commande o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public Commande findById(int id) {
        Session session = sessionFactory.openSession();
        Commande commande = session.get(Commande.class, id);
        session.close();
        return commande;
    }
    
    @Override
    public List<Commande> findAll() {
        Session session = sessionFactory.openSession();
        List<Commande> commandes = session.createQuery("from Commande").list();
        session.close();
        return commandes;
    }
}
