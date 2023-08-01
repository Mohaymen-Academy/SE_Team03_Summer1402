package database;

import entity.Account;
import entity.Profile;
import entity.File;
import entity.ProfileType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AddUser {

    private SessionFactory factory;

    public AddUser() {
        Config();
    }

    private void Config()
    {
        Configuration config = new Configuration();
        config.configure();
        factory = config.buildSessionFactory();
    }

    public void addUser(String username, String password, String phoneNumber, String profileName, String bio, File imageFile)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            Account account = new Account(username, password, phoneNumber);
            session.persist(account);
            Profile profile = new Profile(profileName, bio, imageFile, ProfileType.Pv, account);
            session.persist(profile);
            account.setProfile(profile);
            session.persist(account);
            tx.commit();
        }
        catch (HibernateException ex)
        {
            if(tx != null)
                tx.rollback();
        }
        finally
        {
            session.close();
        }
    }

}
