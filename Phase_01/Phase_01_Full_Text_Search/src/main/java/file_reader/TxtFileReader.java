package file_reader;

import word_manipulation.Tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TxtFileReader extends FileReader {

    public TxtFileReader() {
        extension = "txt";
    }

    @Override
    public Document getDocument(File file, Tokenizer tokenizer) throws FileNotFoundException {
        String name = file.getName();
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while(sc.hasNextLine()){
            sb.append(sc.nextLine().strip()).append(tokenizer.separator());
        }
        String documentName = name.substring(0, name.length() - getExtension(file).length());
        return new Document(documentName, sb.toString());
    }
}