package ma.projet.projets.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("projets/hibernate.cfg.xml");
            
            // Add entity classes programmatically
            configuration.addAnnotatedClass(ma.projet.projets.beans.Employe.class);
            configuration.addAnnotatedClass(ma.projet.projets.beans.Projet.class);
            configuration.addAnnotatedClass(ma.projet.projets.beans.Tache.class);
            configuration.addAnnotatedClass(ma.projet.projets.beans.EmployeTache.class);
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
