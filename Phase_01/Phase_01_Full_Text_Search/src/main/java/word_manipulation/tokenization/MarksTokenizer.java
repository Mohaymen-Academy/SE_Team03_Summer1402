package word_manipulation.tokenization;

import java.util.HashMap;

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
    public HashMap<String, Integer> tokenize(String inputString) {
        HashMap<String, Integer> result = new HashMap<>();
        String regex = "[^a-zA-Z0-9']+";
        for (String word : inputString.split(regex)){
            if(!result.containsKey(word)){
                result.put(word, 0);
            }
            result.put(word, result.get(word) + 1);
        }
        return result;
    }

}