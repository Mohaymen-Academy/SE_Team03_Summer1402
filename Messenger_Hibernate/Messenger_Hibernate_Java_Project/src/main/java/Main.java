import database.Database;
import database.DatabaseLocalInfo;
import database.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        DatabaseLocalInfo databaseLocalInfo =
                new DatabaseLocalInfo("..//Local_Information//DataBase_Local_Information.txt");
        HibernateUtil hibernateUtil =
                new HibernateUtil(databaseLocalInfo.getUrl(),
                        databaseLocalInfo.getUsername(),
                        databaseLocalInfo.getPassword());
        Database database =
                new Database(hibernateUtil);
        database.addUser("Ali", "1234", "09038426825",
                "ali athary", "hello !", null);
        //hibernateUtil.shutdown();
    }

}
