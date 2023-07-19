import java.util.HashSet;
import java.util.Set;

public class Categories {

    /**
     * Count of accept words.
     */
    private int inputCount;

    /**
     * Have stop words.
     */
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

    public Set<String> getIncludeWords() { return includeWords; }
    public Set<String> getOptionalWords() { return optionalWords; }
    public Set<String> getExcludeWords() { return excludeWords; }

    /**
     * Processes the search input and separate words.
     * @param inputString   input string.
     */
    Categories(String inputString, Normalization normalization) throws Exception {
        includeWords = new HashSet<>();
        optionalWords = new HashSet<>();
        excludeWords = new HashSet<>();
        for (String word : inputString.split(" ")) {
            for (String w : normalization.Normalize(word)) {
                if (Stop_Words.words.contains(w.toLowerCase())) {
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
        if(inputCount == 0 && containsStopWords){
            throw new Exception("Please be more specific!");
        }
    }

}