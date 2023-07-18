import java.io.*;
import java.util.*;

public class FileReader {
    public static List<String> lastReadNames;
    public static ArrayList<Set<String>> ReadBooks(String path)
    {
        lastReadNames = new ArrayList<String>();
        ArrayList<Set<String>> books = new ArrayList<Set<String>>();
        File folder = new File(path);
        File[] files = folder.listFiles();
        Scanner sc = null;
        StringBuffer sd = new StringBuffer();
        for(File file : files) {
            String name = file.getName();
            if(name.length() < 4 || !name.substring(name.length() - 4).equals(".txt")){
                continue;
            }
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            lastReadNames.add(name.substring(0, name.length() - 4));
            Set<String> book = new HashSet<String>();
            while(sc.hasNextLine()){
                for(String word : sc.nextLine().strip().split(" ")) {
                    for(String w : Normalize.Normalized(word)){
                        if(Stop_Words.words.contains(w))
                            continue;
                        book.add(w.toLowerCase());
                    }

                }
            }
            books.add(book);
        }
        return books;
    }
}