package word_manipulation;

/**
 * Tokenizing by spiriting by an input string.
 * @param separator Separator string.
 */
public record StringTokenizer(String separator) implements Tokenizer {

    /**
     * Splits the input by the separator string.
     *
     * @param inputString input string to tokenize.
     * @return return the tokenized strings.
     */
    public String[] tokenize(String inputString) {
        return inputString.split(separator);
    }

}