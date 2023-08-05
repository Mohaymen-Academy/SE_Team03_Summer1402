package database;

import database.entity.*;
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

        Account account = em.find(Account.class, username);
        if(account != null) {
            em.getTransaction().begin();
            em.remove(account);
            em.getTransaction().commit();
        }
        else
            System.out.println("This account doesn't exist!");
    }

    public void changeBio(int profileId, String newBio) {
        EntityManager em = hibernateUtil.getEntityManagerFactory().createEntityManager();

        Profile profile = em.find(Profile.class, profileId);
        if(profile != null) {
            em.getTransaction().begin();
            profile.setBio(newBio);
            em.persist(profile);
            em.getTransaction().commit();
        }
        else
            System.out.println("This profile doesn't exist!");
    }

    public void sendMessage(int senderId, int receiverId, String textMessage) {
        EntityManager em = hibernateUtil.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        Profile senderProfile = em.find(Profile.class, senderId);
        Profile receiverProfile = em.find(Profile.class, receiverId);

        if(senderProfile == null || receiverProfile == null)
            System.out.println("Invalid profile id!");

        else {
            Message message = Message.builder()
                    .text(textMessage)
                    .sender(senderProfile)
                    .receiver(receiverProfile)
                    .build();

            em.persist(message);
            em.getTransaction().commit();
        }
    }

    public void editMessage(int messageId, String text) {
        EntityManager em = hibernateUtil.getEntityManagerFactory().createEntityManager();

        Message message = em.find(Message.class, messageId);
        if(message != null) {
            em.getTransaction().begin();
            message.setText(text);
            em.persist(message);
            em.getTransaction().commit();
        }
        else
            System.out.println("This message doesn't exist!");
    }

    public void deleteMessage(int messageId) {
        EntityManager em = hibernateUtil.getEntityManagerFactory().createEntityManager();

        Message message = em.find(Message.class, messageId);
        if(message != null) {
            em.getTransaction().begin();
            em.remove(message);
            em.getTransaction().commit();
        }
        else
            System.out.println("This message id doesn't exist!");
    }

}
