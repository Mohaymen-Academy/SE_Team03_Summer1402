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
     * @throws FileNotFoundException   if the file path dose not exists.
     */
    public Document getDocument(File file, Tokenizer tokenizer) throws FileNotFoundException {
        return null;
    }

    /**
     * Reads all files in the folder with the specified extension and get the documents from them.
     * @param folderPath   the folder path.
     * @param tokenizer   the tokenizer to get the separator.
     * @return a list of documents read from the folder.
     */
    public ArrayList<Document> getDocumentsInFolder(String folderPath, Tokenizer tokenizer){
        ArrayList<Document> result = new ArrayList<>();
        ArrayList<File> files = getFiles(folderPath);
        for (File file : files){
            try {
                result.add(getDocument(file, tokenizer));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    public ArrayList<Document> getDocumentsInFolder(String folderPath){
        return getDocumentsInFolder(folderPath, new StringTokenizer(" "));
    }
}

class TxtFileReader extends FileReader {

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