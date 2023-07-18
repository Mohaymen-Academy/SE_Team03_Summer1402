/**
 * Tokenizer interface.
 */
public interface Tokenizer {

    String[] Tokenize(String inputString);

}

/**
 * Tokenizing by spiriting by an input string.
 */
class StringTokenizer implements Tokenizer{

    /**
     * Separator string.
     */
    private final String separator;

    /**
     * Constructs the tokenizer with custom separator.
     * @param separator   the separator string.
     */
    public StringTokenizer(String separator){
        this.separator = separator;
    }

    /**
     * Splits the input by the separator string.
     * @param inputString   input string to tokenize.
     * @return return the tokenized strings.
     */
    public String[] Tokenize(String inputString){
        return inputString.split(separator);
    }

}