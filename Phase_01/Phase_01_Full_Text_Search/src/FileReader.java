import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class FileReader {
            public static ArrayList<Set<String>> ReadBooks(String path)
            {
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
                    Set<String> book = new HashSet<String>();
                    while(sc.hasNextLine()){
                        for(String word : sc.nextLine().strip().split(" ")) {
                            book.add(word.toLowerCase());
                        }
                    }
                    books.add(book);
                }
                System.out.println(books);
                return books;
            }

}