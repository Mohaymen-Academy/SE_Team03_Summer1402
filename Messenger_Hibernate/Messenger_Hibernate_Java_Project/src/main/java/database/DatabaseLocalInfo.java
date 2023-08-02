package database;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

@Getter
public class DatabaseLocalInfo {

    private final String url;
    private final String username;
    private final String password;

    public DatabaseLocalInfo(String databaseInfoPath){
        ArrayList<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(databaseInfoPath))) {
            stream.forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = "jdbc:postgresql://localhost:"
                + lines.get(0)
                + "/"
                + lines.get(1);
        username = lines.get(2);
        password = lines.get(3);
    }

}
