/**
 * Tokenizer interface.
 */
public interface Tokenizer {
    String separator();
    String[] Tokenize(String inputString);

}

/**
 * Tokenizing by spiriting by an input string.
 *
 * @param separator Separator string.
 */
record StringTokenizer(String separator) implements Tokenizer {

    /**
     * Constructs the tokenizer with custom separator.
     *
     * @param separator the separator string.
     */
    StringTokenizer {
    }

    /**
     * Splits the input by the separator string.
     *
     * @param inputString input string to tokenize.
     * @return return the tokenized strings.
     */
    public String[] Tokenize(String inputString) {
        return inputString.split(separator);
    }

}