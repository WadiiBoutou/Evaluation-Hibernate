package ma.projet.stock.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("stock/hibernate.cfg.xml");
            
            // Add entity classes programmatically
            configuration.addAnnotatedClass(ma.projet.stock.beans.Categorie.class);
            configuration.addAnnotatedClass(ma.projet.stock.beans.Produit.class);
            configuration.addAnnotatedClass(ma.projet.stock.beans.Commande.class);
            configuration.addAnnotatedClass(ma.projet.stock.beans.LigneCommandeProduit.class);
            
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
