package word_manipulation.tokenization;

/**
 * wordManipulation.Tokenizer interface.
 */
public interface Tokenizer {

    /**
     * @return a separator that can be used to join strings before tokenizing.
     */
    String separator();

    /**
     * Tokenizes the input string.
     * @param inputString   the string to tokenize.
     * @return array of tokenized strings.
     */
    String[] tokenize(String inputString);

}

