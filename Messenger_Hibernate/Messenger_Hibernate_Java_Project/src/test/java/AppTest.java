import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class AppTest {

    @Test
    public void testApp() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Account user = new Account("Ali", "1234", "09038426825");
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}