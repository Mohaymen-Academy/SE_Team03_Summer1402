import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                conn.prepareStatement("INSERT INTO account(fk_username, password_hash)" +
                        "VALUES (?, ?)");
        accountInsertStmt.setString(1, username);
        accountInsertStmt.setString(2, password);
        accountInsertStmt.execute();

        conn.close();
    }

    public void addUser(String username, String displayName, String phoneNumber, String password) throws SQLException {
        addUser(username, displayName, phoneNumber, password, "");
    }

    public ArrayList<String> getAllMessages(String username) throws SQLException {
        ArrayList<String> result = new ArrayList<>();

        Connection conn = dataSource.getConnection();
        PreparedStatement stmt =
                conn.prepareStatement("SELECT text_message " +
                        "FROM chat_message CM " +
                        "INNER JOIN profile PF ON CM.fk_sender = PF.profile_id " +
                        "INNER JOIN account AC ON PF.fk_account_id = AC.username " +
                        "WHERE AC.username = ?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        conn.close();

        while (rs.next()) {
            result.add(rs.getString("text_message"));
        }

        return result;
    }

    public int getMessageCount(String username) throws SQLException {
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt =
                conn.prepareStatement("SELECT COUNT(*) " +
                        "FROM chat_message CM " +
                        "INNER JOIN profile PF ON CM.fk_sender = PF.profile_id " +
                        "INNER JOIN account AC ON PF.fk_account_id = AC.username " +
                        "WHERE AC.username = ?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        conn.close();
        rs.next();
        return rs.getInt("count");
    }

    public int getConnectionCount(String username) throws SQLException {
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt =
                conn.prepareStatement("SELECT COUNT(*) " +
                        "FROM profile_connection PC " +
                        "INNER JOIN profile PF ON PC.fk_profile_1 = PF.profile_id " +
                        "INNER JOIN account AC ON PF.fk_account_id = AC.username " +
                        "AND AC.username = ?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        conn.close();
        rs.next();
        return rs.getInt("count");
    }

    public float getAverageMessageSent(String username) throws SQLException {
        return (float)getMessageCount(username) / getConnectionCount(username);
    }

    public void setSeen(int profileId1, int profileId2, int messageId) throws SQLException {
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt =
                conn.prepareStatement("UPDATE profile_connection " +
                        "SET fk_last_message_seen = ? " +
                        "WHERE fk_profile_1 = ? " +
                        "AND fk_profile_2 = ?");
        stmt.setInt(1, messageId);
        stmt.setInt(2, profileId1);
        stmt.setInt(3, profileId2);
        stmt.execute();
        conn.close();
    }

    public Boolean isSeen(int messageId) throws SQLException {
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt =
                conn.prepareStatement("SELECT is_seen(?)");
        stmt.setInt(1, messageId);
        ResultSet rs = stmt.executeQuery();
        conn.close();
        if(!rs.next())
            return false;
        return rs.getBoolean("is_seen");
    }

    public boolean getLoginCheck(String username, String password) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement accountSelectStmt =
                conn.prepareStatement("SELECT     * FROM Account " +
                        "WHERE      fk_username = ? " +
                        "AND        password_hash = ?");

        accountSelectStmt.setString(1, username);
        accountSelectStmt.setString(2, password);

        ResultSet rs = accountSelectStmt.executeQuery();

        conn.close();

        return rs.next();
    }

    public void deleteAccount(String username) throws  SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement profileDeleteStmt =
                conn.prepareStatement("DELETE     FROM Profile " +
                        "WHERE      username = ?;");
        profileDeleteStmt.setString(1, username);
        profileDeleteStmt.execute();

        conn.close();
    }

    public void changeBio(String username, String newBio) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement profileUpdate =
                conn.prepareStatement("UPDATE profile " +
                        "SET    bio = ? " +
                        "WHERE  username = ?;");
        profileUpdate.setString(1, newBio);
        profileUpdate.setString(2, username);
        profileUpdate.execute();

        conn.close();
    }

    public void sendMessage(String username, Integer chatId, String messageText) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement insertChatMessage =
                conn.prepareStatement("INSERT INTO chat_message(have_text, text_message, fk_chat_id, fk_username) " +
                        "VALUES ('true', ?, ?, ?);");
        insertChatMessage.setString(1, messageText);
        insertChatMessage.setInt(2, chatId);
        insertChatMessage.setString(3, username);
        insertChatMessage.execute();

        conn.close();
    }

    public void editMessage(int messageId, String messageText) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement updateChatMessage =
                conn.prepareStatement("UPDATE   chat_message " +
                        "SET    text_message = ? " +
                        "WHERE  message_id = ?;");
        updateChatMessage.setString(1, messageText);
        updateChatMessage.setInt(2, messageId);
        updateChatMessage.execute();

        conn.close();
    }

    public void deleteMessage(int messageId) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement deleteChatMessage =
                conn.prepareStatement("DELETE   FROM chat_message " +
                        "WHERE  message_id = ?");
        deleteChatMessage.setInt(1, messageId);
        deleteChatMessage.execute();

        conn.close();
    }

}
