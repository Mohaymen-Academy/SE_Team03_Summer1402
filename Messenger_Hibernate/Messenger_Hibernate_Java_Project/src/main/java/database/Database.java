package database;

import database.entity.Account;
import database.entity.Profile;
import database.entity.File;
import database.entity.ProfileType;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Database {

    private final HibernateUtil hibernateUtil;

    public Database(HibernateUtil hibernateUtil){
        this.hibernateUtil = hibernateUtil;
    }

    @SneakyThrows(NoSuchAlgorithmException.class)
    public void addUser(String username, String password, String phoneNumber, String profileName, String bio, File imageFile)
    {
        EntityManager em = hibernateUtil.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedPasswordHash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        Account account = new Account(username, encodedPasswordHash, phoneNumber);
        em.persist(account);

        Profile profile = new Profile(profileName, bio, imageFile, ProfileType.Pv, account);
        em.persist(profile);

        account.setProfile(profile);
        em.persist(account);

        em.getTransaction().commit();
    }

    @SneakyThrows(NoSuchAlgorithmException.class)
    public Boolean getLoginCheck(String username, String password){
        EntityManager em = hibernateUtil.getEntityManagerFactory().createEntityManager();

        Account account = em.find(Account.class, username);
        em.detach(account);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedPasswordHash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));

        return Arrays.equals(encodedPasswordHash, account.getPasswordHash());
    }

    public void deleteAccount(String username){
        EntityManager em = hibernateUtil.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        Account account = em.find(Account.class, username);
        em.remove(account);

        em.getTransaction().commit();
    }

}
