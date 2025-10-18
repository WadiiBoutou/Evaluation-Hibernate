package ma.projet.stock.service;


import ma.projet.dao.IDao;
import ma.projet.stock.beans.Categorie;
import ma.projet.stock.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class CategorieService implements IDao<Categorie> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(Categorie o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(Categorie o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(Categorie o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public Categorie findById(int id) {
        Session session = sessionFactory.openSession();
        Categorie categorie = session.get(Categorie.class, id);
        session.close();
        return categorie;
    }
    
    @Override
    public List<Categorie> findAll() {
        Session session = sessionFactory.openSession();
        List<Categorie> categories = session.createQuery("from Categorie").list();
        session.close();
        return categories;
    }
}
