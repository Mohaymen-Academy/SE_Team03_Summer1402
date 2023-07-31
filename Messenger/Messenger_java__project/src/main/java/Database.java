import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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


    public void addUser(String username, String displayName, String phoneNumber, String password, String bio) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement profileInsertStmt =
                conn.prepareStatement("INSERT INTO Profile(username, display_name, phone_number, bio)" +
                        "VALUES (?, ?, ?, ?)");
        profileInsertStmt.setString(1, username);
        profileInsertStmt.setString(2, displayName);
        profileInsertStmt.setString(3, phoneNumber);
        profileInsertStmt.setString(4, bio);
        profileInsertStmt.execute();

        PreparedStatement accountInsertStmt =
                conn.prepareStatement("INSERT INTO Account(fk_username, password_hash)" +
                        "VALUES (?, ?)");
        accountInsertStmt.setString(1, username);
        accountInsertStmt.setString(2, password);
        accountInsertStmt.execute();

        conn.close();
    }

    public void addUser(String username, String displayName, String phoneNumber, String password) throws SQLException {
        addUser(username, displayName, phoneNumber, password, "");
    }

}
