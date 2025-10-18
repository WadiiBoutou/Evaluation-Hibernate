package ma.projet.civil.service;


import ma.projet.civil.beans.Mariage;
import ma.projet.civil.util.HibernateUtil;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

public class MariageService implements IDao<Mariage> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(Mariage o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(Mariage o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(Mariage o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public Mariage findById(int id) {
        Session session = sessionFactory.openSession();
        Mariage mariage = session.get(Mariage.class, id);
        session.close();
        return mariage;
    }
    
    @Override
    public List<Mariage> findAll() {
        Session session = sessionFactory.openSession();
        List<Mariage> mariages = session.createQuery("from Mariage").list();
        session.close();
        return mariages;
    }
}
