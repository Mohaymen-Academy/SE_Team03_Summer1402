package word_manipulation.tokenization;

public class MarksTokenizer implements Tokenizer {

    /**
     * This tokenizer split with any character other than letters and digit, so whitespace can be used as separator.
     * @return whitespace character.
     */
    @Override
    public String separator() {
        return " ";
    }

    /**
     * Splits input string with any character other than letters and digit.
     * @param inputString   the string to tokenize.
     * @return array of tokenized strings.
     */
    @Override
    public String[] tokenize(String inputString) {
        String regex = "[^a-zA-Z0-9']+";
        return inputString.split(regex);
    }

}