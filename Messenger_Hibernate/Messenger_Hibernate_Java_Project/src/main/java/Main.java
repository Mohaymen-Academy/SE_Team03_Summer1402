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
//        database.changeBio(1, "Hello Ali!");
//        database.addUser("Hossein", "1234", "09038426111",
//                "Hossein Ghermezcheshme", "hello !", null);
//        System.out.println(database.getLoginCheck("Ali", "1234"));
//        database.deleteAccount("Ali");
//        database.sendMessage(4, 2, "Hello where are you?");
//        database.editMessage(1, "asfdasgadgdasdsadasd");
//        database.deleteMessage(1);
        hibernateUtil.shutdown();
    }

}
