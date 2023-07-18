import java.io.*;
import java.util.*;

public class FileReader {
    public ArrayList<Set<String>> documents;
    public String[] documentsName;
    private List<File> files;

    public FileReader(String folderPath, Normalization normalization, String extension) throws FileNotFoundException {
        files = new ArrayList<File>();
        GetFiles(folderPath, extension);
        documentsName = new String[files.size()];
        GetNames();
        documents = new ArrayList<Set<String>>();
        ReadDocuments(folderPath, normalization, extension);
    }
    private String GetExtention(File file){
        String name = file.getName();
        String[] splited = name.split("\\.");
        return splited[splited.length - 1];
    }
    private void GetFiles(String folderPath, String extention) {
        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();
        for(File file : folderFiles) {
            String name = file.getName();
            if(name.length() < extention.length() + 1 || !GetExtention(file).equals(extention)){
                continue;
            }
            files.add(file);
        }
    }
    private void GetNames(){
        for(int i = 0; i < files.size(); i++){
            String name = files.get(i).getName();
            documentsName[i] = name.substring(0, name.length() - GetExtention(files.get(i)).length());
        }
    }
    private void ReadDocuments(String path, Normalization normalization, String extention) throws FileNotFoundException {
        Scanner sc = null;
        StringBuffer sd = new StringBuffer();
        for(File file : files) {
            sc = new Scanner(file);
            Set<String> document = new HashSet<String>();
            while(sc.hasNextLine()){
                for(String word : sc.nextLine().strip().split(" ")) {
                    for(String w : normalization.Normalize(word)){
                        if(Stop_Words.words.contains(w))
                            continue;
                        document.add(w.toLowerCase());
                    }
                }
            }
            documents.add(document);
        }
    }
}