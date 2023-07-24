package word_manipulation.normalization;

/**
 * wordManipulation.Normalization interface.
 */
public interface Normalization {

    /**
     * Normalizes the input string.
     * @param inputString   string to normalize.
     * @return   array of string containing the normalized string from input string.
     */
    String[] normalize(String inputString);

}