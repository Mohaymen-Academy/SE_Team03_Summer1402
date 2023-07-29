package word_manipulation.normalization;

/**
 * wordManipulation.Normalization interface.
 */
public interface Normalizer {

    /**
     * Normalizes the input string.
     * @param inputString   the string to normalize.
     * @return normalized string.
     */
    String normalize(String inputString);

}