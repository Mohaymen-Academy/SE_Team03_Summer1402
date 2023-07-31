import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Database database = new Database("..//Local_Information//DataBase_Local_Information.txt");
        //database.addUser("aaa", "aaa", "01234567891", "1234");
        database.setSeen(1, "aaa", 5);
    }

}