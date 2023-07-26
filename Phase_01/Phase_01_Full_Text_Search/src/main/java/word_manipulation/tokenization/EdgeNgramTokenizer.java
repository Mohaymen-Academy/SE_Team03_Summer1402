package word_manipulation.tokenization;

import lombok.AllArgsConstructor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    public String[] tokenize(String inputString) {
        String regex = "[^a-zA-Z0-9']+";
        String[] splitStrings = inputString.split(regex);
        Set<String> words = new HashSet<>(Arrays.asList(splitStrings));
        for (String word : splitStrings) {
            for (int i = minGram; i < maxGram + 1; i++) {
                for (int j = 0; j < word.length() - i + 1; j++) {
                    words.add(word.substring(j, j + i));
                }
            }
        }

        String[] result = words.stream().toArray(String[] ::new);
        return result;
    }

}