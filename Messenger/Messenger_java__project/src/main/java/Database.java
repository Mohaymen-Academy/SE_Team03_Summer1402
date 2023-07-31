import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Database {

    private final String databaseUrl;

    private final DataSource dataSource;

    public Database(String databaseInfoPath){
        databaseUrl = getDataBaseUrl(databaseInfoPath);
        dataSource = createDataSource();
    }

    private DataSource createDataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(databaseUrl);
        return dataSource;
    }

    private String getDataBaseUrl(String databaseInfoPath){
        ArrayList<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(databaseInfoPath))) {
            stream.forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "jdbc:postgresql://localhost:"
                + lines.get(0)
                + "/"
                + lines.get(1)
                + "?user="
                + lines.get(2)
                +"&password="
                + lines.get(3);
    }


    public Boolean addUser(String username, String phoneNumber, String bio) throws SQLException {
        Connection conn = dataSource.getConnection();
        PreparedStatement insertStmt =
                conn.prepareStatement("INSERT INTO App_User(username, phone_number, bio)" +
                        "VALUES (?, ?, ?)");
        insertStmt.setString(1, username);
        insertStmt.setString(2, phoneNumber);
        insertStmt.setString(3, bio);
        Boolean result = insertStmt.execute();
        conn.close();
        return result;
    }

    public Boolean addUser(String username, String phoneNumber) throws SQLException {
        return addUser(username, phoneNumber, "");
    }

}
