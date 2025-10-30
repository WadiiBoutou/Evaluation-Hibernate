package ma.projet.projets.service;


import ma.projet.dao.IDao;
import ma.projet.projets.beans.EmployeTache;
import ma.projet.projets.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeTacheService implements IDao<EmployeTache> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(EmployeTache o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(EmployeTache o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(EmployeTache o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public EmployeTache findById(int id) {
        Session session = sessionFactory.openSession();
        EmployeTache employeTache = session.get(EmployeTache.class, id);
        session.close();
        return employeTache;
    }
    
    @Override
    public List<EmployeTache> findAll() {
        Session session = sessionFactory.openSession();
        List<EmployeTache> employeTaches = session.createQuery("from EmployeTache").list();
        session.close();
        return employeTaches;
    }
}
