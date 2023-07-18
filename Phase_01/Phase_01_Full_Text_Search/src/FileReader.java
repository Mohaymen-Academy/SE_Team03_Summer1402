import java.io.*;
import java.util.*;

public class FileReader {

    /**
     * Document contents.
     */
    public ArrayList<Set<String>> documents;

    /**
     * Document name.
     */
    public String[] documentsName;

    /**
     * Document file.
     */
    private final List<File> files;

    /**
     * Constructs file reader.
     * @param folderPath   path of documents' folder.
     * @param normalization   normalization method.
     * @param extension   documents' file extension.
     * @throws FileNotFoundException   if path doesn't exist.
     */
    public FileReader(String folderPath, Normalization normalization, String extension, Tokenizer tokenizer) throws FileNotFoundException {
        files = new ArrayList<>();
        GetFiles(folderPath, extension);
        documentsName = new String[files.size()];
        GetNames();
        documents = new ArrayList<>();
        ReadDocuments(normalization, tokenizer);
    }

    /**
     * Gets extension of files.
     * @param file   file of document.
     * @return   string of extension.
     */
    private String GetExtension(File file){
        String name = file.getName();
        String[] split = name.split("\\.");
        return split[split.length - 1];
    }

    /**
     * Finds all the documents in the folder (with the extension).
     * @param folderPath   path of documents' folder.
     * @param extension   documents' file extension.
     */
    private void GetFiles(String folderPath, String extension) {
        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();
        assert folderFiles != null;
        for(File file : folderFiles) {
            String name = file.getName();
            if(name.length() < extension.length() + 1 || !GetExtension(file).equals(extension)){
                continue;
            }
            files.add(file);
        }
    }

    /**
     * Stores name of document without extension.
     */
    private void GetNames(){
        for(int i = 0; i < files.size(); i++){
            String name = files.get(i).getName();
            documentsName[i] = name.substring(0, name.length() - GetExtension(files.get(i)).length());
        }
    }

    /**
     * Reads documents' content and stores them.
     * @param normalization   normalization method.
     * @param tokenizer   tokenize method.
     * @throws FileNotFoundException   if path doesn't exist.
     */
    private void ReadDocuments(Normalization normalization, Tokenizer tokenizer) throws FileNotFoundException {
        Scanner sc;
        for(File file : files) {
            sc = new Scanner(file);
            Set<String> document = new HashSet<>();
            while(sc.hasNextLine()){
                for(String word : tokenizer.Tokenize(sc.nextLine().strip())) {
                    for(String w : normalization.Normalize(word)){
                        if(Stop_Words.words.contains(w.toLowerCase()))
                            continue;
                        document.add(w);
                    }
                }
            }
            documents.add(document);
        }
    }

}