package word_manipulation.tokenization;

import java.util.HashMap;

public record StringTokenizer(String separator) implements Tokenizer {

    /**
     * Splits the input by the separator string.
     * @param inputString   the string to tokenize.
     * @return array of tokenized strings.
     */
    public HashMap<String, Integer> tokenize(String inputString) {
        HashMap<String, Integer> result = new HashMap<>();
        for (String word : inputString.split(separator)) {
            if(!result.containsKey(word))
                result.put(word, 0);
            result.put(word, result.get(word) + 1);
        }
        return result;
    }

}