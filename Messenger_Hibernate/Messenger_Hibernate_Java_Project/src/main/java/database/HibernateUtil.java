package database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static StandardServiceRegistry serviceRegistry;

    public HibernateUtil(String databaseUrl, String username, String password) {
        try {
            Configuration config = new Configuration();
            config.configure();
            StandardServiceRegistryBuilder standardServiceRegistryBuilder =
                    config.getStandardServiceRegistryBuilder();

            // database local information
            standardServiceRegistryBuilder.applySetting(
                    "hibernate.connection.url", databaseUrl);
            standardServiceRegistryBuilder.applySetting(
                    "hibernate.connection.username", username);
            standardServiceRegistryBuilder.applySetting(
                    "hibernate.connection.password", password);

            serviceRegistry = standardServiceRegistryBuilder.build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {

            if (serviceRegistry != null)
                StandardServiceRegistryBuilder.destroy(serviceRegistry);

            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
        if (serviceRegistry != null)
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

}
