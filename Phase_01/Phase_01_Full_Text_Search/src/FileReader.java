import java.io.*;
import java.util.*;

public class FileReader {
    public ArrayList<Set<String>> documents;
    public String[] documentsName;
    private List<File> files;

    public FileReader(String folderPath, Normalization normalization, String extention) throws FileNotFoundException {
        files = new ArrayList<File>();
        GetFiles(folderPath, extention);
        documents = new ArrayList<Set<String>>();
        documentsName = new String[files.size()];
        GetNames();
        ReadDocuments(folderPath, normalization, extention);
    }
    private void GetFiles(String folderPath, String extention) {
        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();
        for(File file : folderFiles) {
            String name = file.getName();
            if(name.length() < extention.length() + 1 ||
                    !name.substring(name.length() - extention.length()).equals(extention)
                    || name.charAt(name.length() - extention.length() - 1) != '.'){
                continue;
            }
            files.add(file);
        }
    }
    private void GetNames(){
        for(int i = 0; i < files.size(); i++){
            String name = files.get(i).getName();
            documentsName[i] = name.substring(0, name.length() - );
        }
    }
    private void ReadDocuments(String path, Normalization normalization, String extention) throws FileNotFoundException {
        File folder = new File(path);
        File[] files = folder.listFiles();
        Scanner sc = null;
        StringBuffer sd = new StringBuffer();
        for(File file : files) {
            String name = file.getName();
            if(name.length() < 4 || !name.substring(name.length() - 4).equals(".txt")){
                continue;
            }
            sc = new Scanner(file);
            documentsName.add(name.substring(0, name.length() - 4));
            Set<String> book = new HashSet<String>();
            while(sc.hasNextLine()){
                for(String word : sc.nextLine().strip().split(" ")) {
                    for(String w : normalization.Normalize(word)){
                        if(Stop_Words.words.contains(w))
                            continue;
                        book.add(w.toLowerCase());
                    }

                }
            }
            documents.add(book);
        }
    }
}