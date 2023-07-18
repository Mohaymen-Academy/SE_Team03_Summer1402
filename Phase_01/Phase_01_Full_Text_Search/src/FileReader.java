import java.io.*;
import java.util.*;

public class FileReader {

    /**
     * Gets extension of files.
     * @param file   file of document.
     * @return   string of extension.
     */
    private static String GetExtension(File file){
        String name = file.getName();
        String[] split = name.split("\\.");
        return split[split.length - 1];
    }

    /**
     * Finds all the documents in the folder (with the extension).
     * @param folderPath   path of documents' folder.
     * @param extension   documents' file extension.
     */
    private static ArrayList<File> GetFiles(String folderPath, String extension) {
        ArrayList<File> result = new ArrayList<>();
        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();
        assert folderFiles != null;
        for(File file : folderFiles) {
            String name = file.getName();
            if(name.length() < extension.length() + 1 || !GetExtension(file).equals(extension)){
                continue;
            }
            result.add(file);
        }
        return result;
    }

    /**
     * Gets file name and context and makes a document.
     * @param file   the file to get the data from.
     * @return returns the document.
     * @throws FileNotFoundException   if the file path dose not exists.
     */
    private static Document GetDocument(File file) throws FileNotFoundException {
        String name = file.getName();
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while(sc.hasNextLine()){
            sb.append(sc.nextLine().strip()).append(" ");
        }
        return new Document(name.substring(0, name.length() - GetExtension(file).length()), sb.toString());
    }

    /**
     * Reads all files in the folder with the specified extension and get the documents from them.
     * @param folderPath   the folder path.
     * @param extension   the file extension to read.
     * @return a list of documents read from the folder.
     */
    public static ArrayList<Document> GetDocumentsInFolder(String folderPath, String extension){
        ArrayList<Document> result = new ArrayList<>();
        ArrayList<File> files = GetFiles(folderPath, extension);
        for (File file : files){
            try {
                result.add(GetDocument(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}