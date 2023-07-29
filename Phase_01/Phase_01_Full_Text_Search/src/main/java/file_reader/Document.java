package file_reader;

/**
 * fileReader.Document class to store data set for documents.
 * @param name    The name of the document.
 * @param context The context of the document.
 */
public record Document(String name, String context) { }