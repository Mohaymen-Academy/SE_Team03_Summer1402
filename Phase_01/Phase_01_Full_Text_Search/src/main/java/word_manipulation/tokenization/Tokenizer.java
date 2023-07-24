package word_manipulation.tokenization;

import java.util.List;

/**
 * wordManipulation.Tokenizer interface.
 */
public interface Tokenizer {
    String separator();
    String[] tokenize(String inputString);

}

