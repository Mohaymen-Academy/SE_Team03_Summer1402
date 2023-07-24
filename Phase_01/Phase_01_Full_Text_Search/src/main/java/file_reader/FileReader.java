package file_reader;

import java.io.*;
import java.util.*;

public class FileReader {

    protected String extension;

    /**
     * Gets extension of files.
     * @param file   file of document.
     * @return   string of extension.
     */
    protected String getExtension(File file){
        String name = file.getName();
        String[] split = name.split("\\.");
        return split[split.length - 1];
    }

    /**
     * Finds all the documents in the folder (with the extension).
     * @param folderPath   path of documents' folder.
     */
    private ArrayList<File> getFiles(String folderPath) {
        ArrayList<File> result = new ArrayList<>();
        File folder = new File(folderPath);
        File[] folderFiles = folder.listFiles();
        assert folderFiles != null;
        for(File file : folderFiles) {
            if(isValidFile(file)){
                result.add(file);
            }
        }
        return result;
    }

    private Boolean isValidFile(File file){
        String name = file.getName();
        return name.length() >= extension.length() + 1 && getExtension(file).equals(extension);
    }

    /**
     * Gets file name and context and makes a document.
     * @param file   the file to get the data from.
     * @return returns the document.
     * @throws IOException   if the file context is not standard encoding.
     */
    public Document getDocument(File file, String separator) throws IOException {
        return null;
    }

    /**
     * Reads all files in the folder with the specified extension and get the documents from them.
     * @param folderPath   the folder path.
     * @param separator   the separator string to add to the end of the line before joining.
     * @return a list of documents read from the folder.
     */
    public ArrayList<Document> getDocumentsInFolder(String folderPath, String separator){
        ArrayList<Document> result = new ArrayList<>();
        ArrayList<File> files = getFiles(folderPath);
        for (File file : files){
            try {
                result.add(getDocument(file, separator));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public ArrayList<Document> getDocumentsInFolder(String folderPath){
        return getDocumentsInFolder(folderPath, " ");
    }
}

