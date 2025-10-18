package ma.projet.civil.service;

import ma.projet.civil.beans.Homme;

import ma.projet.civil.util.HibernateUtil;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(Homme o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(Homme o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(Homme o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public Homme findById(int id) {
        Session session = sessionFactory.openSession();
        Homme homme = session.get(Homme.class, id);
        session.close();
        return homme;
    }
    
    @Override
    public List<Homme> findAll() {
        Session session = sessionFactory.openSession();
        List<Homme> hommes = session.createQuery("from Homme").list();
        session.close();
        return hommes;
    }
    
    public List<Object[]> findEpousesEntreDates(int hommeId, Date dateDebut, Date dateFin) {
        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
            "select f.nom, f.prenom, m.dateDebut, m.dateFin, m.nbrEnfant from Femme f " +
            "join f.mariages m " +
            "where m.homme.id = :hommeId and m.dateDebut between :dateDebut and :dateFin"
        );
        query.setParameter("hommeId", hommeId);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
    
    public List<Object[]> findMariagesAvecDetails(int hommeId) {
        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
            "select f.nom, f.prenom, m.dateDebut, m.dateFin, m.nbrEnfant from Femme f " +
            "join f.mariages m " +
            "where m.homme.id = :hommeId " +
            "order by m.dateDebut"
        );
        query.setParameter("hommeId", hommeId);
        List<Object[]> results = query.list();
        session.close();
        return results;
    }
}
