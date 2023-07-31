import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database database = new Database("..//Local_Information//DataBase_Local_Information.txt");
        database.addUser("aaa", "aaa", "09038426825", "1234");
    }

}
