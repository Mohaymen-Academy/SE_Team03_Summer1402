package file_reader;

/**
 * fileReader.Document class to store data set for documents.
 */
public class Document{

    /**
     * The name of the document.
     */
    private String name;

    /**
     * The context of the document.
     */
    private String context;

    /**
     * Construct the document instance
     * @param name   the string name.
     * @param context   the string context.
     */
    public Document(String name, String context){
        this.name = name;
        this.context = context;
    }

    public String getName() { return name; }

    public String getContext() { return context; }
}