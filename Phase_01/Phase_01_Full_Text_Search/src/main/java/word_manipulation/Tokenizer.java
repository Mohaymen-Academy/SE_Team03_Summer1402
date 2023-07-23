package word_manipulation;

/**
 * wordManipulation.Tokenizer interface.
 */
public interface Tokenizer {
    String separator();
    String[] tokenize(String inputString);

}

