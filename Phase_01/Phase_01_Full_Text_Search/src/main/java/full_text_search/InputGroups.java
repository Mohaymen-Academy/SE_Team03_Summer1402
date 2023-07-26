package full_text_search;

import word_manipulation.normalization.Normalizer;
import word_manipulation.StopWords;

import lombok.AccessLevel;
import lombok.Getter;
import java.util.HashSet;
import java.util.Set;

@Getter
public class InputGroups {

    /**
     * Count of accept words.
     */
    @Getter(AccessLevel.NONE)
    private int inputCount;

    /**
     * Have stop words.
     */
    @Getter(AccessLevel.NONE)
    private boolean containsStopWords;

    /**
     * Words that must be in the result.
     */
    private final Set<String> includeWords;

    /**
     * words that at least on of them must be in the result.
     */
    private final Set<String> optionalWords;

    /**
     * Words that mustn't be in the result.
     */
    private final Set<String> excludeWords;

    /**
     * Constructs the input groups and put each word in corresponding group.
     * @param inputString   input string.
     * @param normalizer   normalizer.
     * @throws Exception if the search input is not specific.
     */
    public InputGroups(String inputString, Normalizer normalizer) throws Exception {
        includeWords = new HashSet<>();
        optionalWords = new HashSet<>();
        excludeWords = new HashSet<>();

        processWords(inputString, normalizer);

        if(inputCount == 0 && containsStopWords){
            throw new Exception("Please be more specific!");
        }
        if(inputString.strip().equals("")){
            throw new Exception("Please be more specific!");
        }
    }

    /**
     * Distinguishes kind of word and checks stop words.
     * @param inputString   input string.
     * @param normalizer   normalizer.
     */
    private void processWords(String inputString, Normalizer normalizer){
        for (String word : inputString.split(" ")) {
            String w = normalizer.normalize(word);
            if (StopWords.isStopWord(w)) {
                containsStopWords = true;
                continue;
            }
            inputCount++;
            switch (word.charAt(0)) {
                case '+' -> optionalWords.add(w);
                case '-' -> excludeWords.add(w);
                default -> includeWords.add(w);
            }
        }
    }

}