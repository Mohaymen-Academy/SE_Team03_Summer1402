package file_reader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TxtFileReader extends FileReader {

    public TxtFileReader() {
        extension = "txt";
    }

    @Override
    public Document getDocument(File file, String separator) throws IOException {
        Stream<String> wordStream;
        wordStream = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8);
        String joinedString = wordStream.collect(Collectors.joining(separator));

        String name = file.getName();
        String documentName = name.substring(0, name.length() - getFileExtension(file).length() - 1);

        return new Document(documentName, joinedString);
    }

}