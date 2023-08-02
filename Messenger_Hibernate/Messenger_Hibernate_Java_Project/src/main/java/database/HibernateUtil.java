package database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {

    private static StandardServiceRegistry serviceRegistry;

    @Getter
    private SessionFactory sessionFactory;

    @Getter
    private EntityManagerFactory entityManagerFactory;

    public HibernateUtil(String databaseUrl, String username, String password) {
        aetUpSessionFactory(databaseUrl, username, password);
        setUpEntityManagerFactory(databaseUrl, username, password);
    }

    private void aetUpSessionFactory(String databaseUrl, String username, String password)
    {
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

    private void setUpEntityManagerFactory(String databaseUrl, String username, String password)
    {
        Properties props = new Properties();
        props.setProperty("javax.persistence.jdbc.url", databaseUrl);
        props.setProperty("javax.persistence.jdbc.user", username);
        props.setProperty("javax.persistence.jdbc.password", password);
        entityManagerFactory =
                Persistence.createEntityManagerFactory(
                        "dbPersistenceUnit", props);
    }

    public void shutdown() {
        sessionFactory.close();
        if (serviceRegistry != null)
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

}
