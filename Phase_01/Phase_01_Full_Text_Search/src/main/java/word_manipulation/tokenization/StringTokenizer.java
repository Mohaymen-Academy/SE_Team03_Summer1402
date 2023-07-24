package word_manipulation.tokenization;

public record StringTokenizer(String separator) implements Tokenizer {

    /**
     * Splits the input by the separator string.
     * @param inputString   the string to tokenize.
     * @return array of tokenized strings.
     */
    public String[] tokenize(String inputString) {
        return inputString.split(separator);
    }

}