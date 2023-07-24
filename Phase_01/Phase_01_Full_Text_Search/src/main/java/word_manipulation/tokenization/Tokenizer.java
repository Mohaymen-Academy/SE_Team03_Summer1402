package word_manipulation.tokenization;

/**
 * wordManipulation.Tokenizer interface.
 */
public interface Tokenizer {
    String separator();
    String[] tokenize(String inputString);

}

