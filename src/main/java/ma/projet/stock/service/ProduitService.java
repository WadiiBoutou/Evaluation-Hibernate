package ma.projet.stock.service;


import ma.projet.dao.IDao;
import ma.projet.stock.beans.Produit;
import ma.projet.stock.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProduitService implements IDao<Produit> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(Produit o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(Produit o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(Produit o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public Produit findById(int id) {
        Session session = sessionFactory.openSession();
        Produit produit = session.get(Produit.class, id);
        session.close();
        return produit;
    }
    
    @Override
    public List<Produit> findAll() {
        Session session = sessionFactory.openSession();
        List<Produit> produits = session.createQuery("from Produit").list();
        session.close();
        return produits;
    }
    
    public List<Produit> findByCategorie(int categorieId) {
        Session session = sessionFactory.openSession();
        Query<Produit> query = session.createQuery("from Produit p where p.categorie.id = :categorieId");
        query.setParameter("categorieId", categorieId);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }
    
    public List<Object[]> findProduitsCommandesEntreDates(Date dateDebut, Date dateFin) {
        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
            "select p.reference, p.prix, lcp.quantite from Produit p " +
            "join p.ligneCommandeProduits lcp " +
            "join lcp.commande c " +
            "where c.date between :dateDebut and :dateFin"
        );
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
    
    public List<Object[]> findProduitsByCommande(int commandeId) {
        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
            "select p.reference, p.prix, lcp.quantite from Produit p " +
            "join p.ligneCommandeProduits lcp " +
            "where lcp.commande.id = :commandeId"
        );
        query.setParameter("commandeId", commandeId);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
    
    public List<Produit> findProduitsPrixSuperieurA100() {
        Session session = sessionFactory.openSession();
        Query<Produit> query = session.createNamedQuery("Produit.findByPrixSuperieurA100");
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }
}
