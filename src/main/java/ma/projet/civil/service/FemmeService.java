package ma.projet.civil.service;

import ma.projet.civil.beans.Femme;
import ma.projet.civil.beans.Homme;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ma.projet.civil.util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public boolean create(Femme o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean update(Femme o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public boolean delete(Femme o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    
    @Override
    public Femme findById(int id) {
        Session session = sessionFactory.openSession();
        Femme femme = session.get(Femme.class, id);
        session.close();
        return femme;
    }
    
    @Override
    public List<Femme> findAll() {
        Session session = sessionFactory.openSession();
        List<Femme> femmes = session.createQuery("from Femme").list();
        session.close();
        return femmes;
    }
    
    public Long findNombreEnfantsEntreDates(int femmeId, Date dateDebut, Date dateFin) {
        Session session = sessionFactory.openSession();
        Query<?> query = session.createNamedQuery("Femme.findNombreEnfantsEntreDates");
        query.setParameter("femmeId", femmeId);
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        Object result = query.uniqueResult();
        session.close();
        return result != null ? ((Number) result).longValue() : 0L;
    }
    
    public List<Femme> findFemmesMarieesAuMoinsDeuxFois() {
        Session session = sessionFactory.openSession();
        Query<Femme> query = session.createNamedQuery("Femme.findByMariagesAuMoinsDeuxFois");
        List<Femme> femmes = query.list();
        session.close();
        return femmes;
    }
    
    public List<Homme> findHommesMariesAQuatreFemmesEntreDates(Date dateDebut, Date dateFin) {
        Session session = sessionFactory.openSession();
        Query<Homme> query = session.createQuery(
            "select h from Homme h " +
            "where size(h.mariages) >= 4 " +
            "and exists (select m from Mariage m where m.homme = h and m.dateDebut between :dateDebut and :dateFin)"
        );
        query.setParameter("dateDebut", dateDebut);
        query.setParameter("dateFin", dateFin);
        List<Homme> hommes = query.list();
        session.close();
        return hommes;
    }
    
    public Femme findFemmeLaPlusAgee() {
        Session session = sessionFactory.openSession();
        Query<Femme> query = session.createQuery("from Femme f order by f.dateNaissance asc");
        query.setMaxResults(1);
        Femme femme = query.uniqueResult();
        session.close();
        return femme;
    }
}
