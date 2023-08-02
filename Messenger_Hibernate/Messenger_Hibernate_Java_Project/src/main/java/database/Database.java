package database;

import database.entity.Account;
import database.entity.Profile;
import database.entity.File;
import database.entity.ProfileType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Database {

    private HibernateUtil hibernateUtil;

    public Database(HibernateUtil hibernateUtil){
        this.hibernateUtil = hibernateUtil;
    }

    public void addUser(String username, String password, String phoneNumber, String profileName, String bio, File imageFile)
    {
        Session session = hibernateUtil.getSessionFactory().openSession();
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
