package word_manipulation.tokenization;

import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.Arrays;

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
     * Length difference between consecutive substring.
     */
    private final int step;

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
        ArrayList<String> words = new ArrayList<>(Arrays.asList(inputString.split(regex)));
        for (int i = 0; i < words.size(); i++) {
            for (int j = minGram; j < maxGram; j+= step) {
                if(j < words.get(i).length()){
                    words.add(words.get(i).substring(0, j));
                }
            }
        }
        String[] result = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            result[i] = words.get(i);
        }
        return result;
    }

}