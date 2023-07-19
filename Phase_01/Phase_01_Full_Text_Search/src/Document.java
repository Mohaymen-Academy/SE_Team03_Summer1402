/**
 * Document class to store data set for documents.
 */
public class Document{

    /**
     * The name of the document.
     */
    public String name;

    /**
     * The context of the document.
     */
    public String context;

    /**
     * Construct the document instance
     * @param name   the string name.
     * @param context   the string context.
     */
    public Document(String name, String context){
        this.name = name;
        this.context = context;
    }
}