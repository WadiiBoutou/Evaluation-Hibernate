package ma.projet.civil.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("civil/hibernate.cfg.xml");
            
            // Add entity classes programmatically
            configuration.addAnnotatedClass(ma.projet.civil.beans.Personne.class);
            configuration.addAnnotatedClass(ma.projet.civil.beans.Homme.class);
            configuration.addAnnotatedClass(ma.projet.civil.beans.Femme.class);
            configuration.addAnnotatedClass(ma.projet.civil.beans.Mariage.class);
            
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
