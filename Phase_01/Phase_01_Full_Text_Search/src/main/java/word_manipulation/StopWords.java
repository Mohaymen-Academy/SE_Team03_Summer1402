package word_manipulation;

import java.util.*;

public class StopWords {

    /**
     * The words that should be removed from query and documents.
     */
    private static final Set<String> words = new HashSet<>(Arrays.asList("", " ", "i", "me", "my", "myself", "we", "our",
            "ours", "ourselves", "you", "your", "yours", "yourself", "yourselves",
            "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their",
            "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "these", "those", "am", "is", "are", "was",
            "were", "be", "been", "being", "have", "has", "had", "having", "do", "does", "did", "doing", "a", "an", "the",
            "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for", "with", "about", "against",
            "between", "into", "through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out",
            "on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how",
            "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own",
            "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"));

    /**
     * Checks if the input string is a stop word.
     * @param word   the string to check.
     * @return a boolean that is true if the input string is a stop word.
     */
    public static Boolean isStopWord(String word){
        return words.contains(word.toLowerCase());
    }

}