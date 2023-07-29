package word_manipulation.tokenization;

import java.util.HashMap;

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
    HashMap<String, Integer> tokenize(String inputString);

}

