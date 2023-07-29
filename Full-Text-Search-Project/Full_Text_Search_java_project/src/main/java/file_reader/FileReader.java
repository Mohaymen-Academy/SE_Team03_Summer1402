package file_reader;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public abstract class FileReader {

    protected String extension;

    /**
     * Gets extension of files.
     * @param file   file of document.
     * @return   string of extension.
     */
    protected String getFileExtension(File file) {
        String name = file.getName();
        String[] split = name.split("\\.");
        return split[split.length - 1];
    }

    /**
     * Finds all documents in the folder (with the extension).
     * @param folderPath   path of documents' folder.
     */
    private Stream<File> getFiles(String folderPath) {
        File folder = new File(folderPath);
        return Stream.of(folder.listFiles()).filter(this::checkFileExtension);
    }

    /**
     * Checks extension of file.
     * @param file   file of document.
     * @return   if extension was right return True else return False.
     */
    private Boolean checkFileExtension(File file) {
        String name = file.getName();
        Boolean isProperLength = name.length() >= extension.length() + 1;
        Boolean hasRightExtension = getFileExtension(file).equals(extension);
        return  isProperLength && hasRightExtension;
    }

    /**
     * Gets file name and context and makes a document.
     * @param file   the file to get the data from.
     * @return returns the document.
     * @throws IOException   if the file context is not standard encoding.
     */
    public abstract Document getDocument(File file, String separator) throws IOException;

    /**
     * Reads all files in the folder with the specified extension and get the documents from them.
     * @param folderPath   the folder path.
     * @param separator   the separator string to add to the end of the line before joining.
     * @return a list of documents read from the folder.
     */
    public ArrayList<Document> getDocumentsInFolder(String folderPath, String separator) {
        ArrayList<Document> result = new ArrayList<>();
        getFiles(folderPath).forEach(f -> {
            try {
                result.add(getDocument(f, separator));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }

    /**
     * Reads all files in the folder with ' ' extension and get the documents from them.
     * @param folderPath   the folder path.
     * @return   a list of documents read from the folder.
     */
    public ArrayList<Document> getDocumentsInFolder(String folderPath) {
        return getDocumentsInFolder(folderPath, " ");
    }

}