import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database database = new Database("..//Local_Information//DataBase_Local_Information.txt");
        //database.addUser("aaa", "aaa", "09038426825", "1234");
        //System.out.println(database.login("Hossin", "Hossein09105158355"));
        //database.deleteAccount("Hossein");
        //database.changeBio("Ali", "Hello Ali.");
        //database.sendMessage("Ali", 1, "Hello Hossein ON FIRE.");
        //database.editMessage(201, "Im ali form Iran.");
        //database.deleteMessage(201);
        //database.addUser("aaa", "aaa", "01234567891", "1234");
        //database.setSeen(1, "aaa", 5);
        System.out.println(database.isSeen(70));
    }

}
