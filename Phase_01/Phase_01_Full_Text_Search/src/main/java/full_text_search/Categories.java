package full_text_search;

import lombok.AccessLevel;
import lombok.Getter;
import word_manipulation.normalization.Normalization;
import word_manipulation.StopWords;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Categories {

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
     * Processes the search input and separate words.
     * @param inputString   input string.
     */
    Categories(String inputString, Normalization normalization) throws Exception {
        includeWords = new HashSet<>();
        optionalWords = new HashSet<>();
        excludeWords = new HashSet<>();

        processWords(inputString, normalization);

        if(inputCount == 0 && containsStopWords){
            throw new Exception("Please be more specific!");
        }
    }

    private void processWords(String inputString, Normalization normalization){
        for (String word : inputString.split(" ")) {
            String w = normalization.normalize(word);
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