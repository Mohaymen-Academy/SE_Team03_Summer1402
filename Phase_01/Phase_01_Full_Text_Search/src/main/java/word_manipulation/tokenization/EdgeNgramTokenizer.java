package word_manipulation.tokenization;

import lombok.AllArgsConstructor;
import java.util.HashMap;

@AllArgsConstructor
public class EdgeNgramTokenizer implements Tokenizer {

    /**
     * Length of the smallest substring.
     */
    private final int minGram;

    /**
     * Limit for length of the largest substring.
     */
    private final int maxGram;

    /**
     * This tokenizer split with any character other than letters and digit, so whitespace can be used as separator.
     * @return whitespace character.
     */
    @Override
    public String separator() {
        return " ";
    }

    /**
     * Splits input string with any character other than letters and digit. then add substrings according to min gram and max gram and step.
     * @param inputString   the string to tokenize.
     * @return array of tokenized strings.
     */
    @Override
    public HashMap<String, Integer> tokenize(String inputString) {
        HashMap<String, Integer> result = new HashMap<>();
        String regex = "[^a-zA-Z0-9']+";
        for (String word : inputString.split(regex)) {
            if(maxGram < word.length()) {
                if(!result.containsKey(word))
                    result.put(word, 0);
                result.put(word, result.get(word) + 1);
            }
            for (int i = minGram; i < maxGram + 1; i++) {
                for (int j = 0; j < word.length() - i + 1; j++) {
                    String w = word.substring(j, j + i);
                    if(!result.containsKey(w))
                        result.put(w, 0);
                    result.put(w, result.get(w) + 1);
                }
            }
        }
        return result;
    }

}