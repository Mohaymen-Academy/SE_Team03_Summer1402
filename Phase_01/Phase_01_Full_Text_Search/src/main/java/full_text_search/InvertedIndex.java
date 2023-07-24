package full_text_search;

import java.util.*;

public class InvertedIndex {

    /**
     * The inverted index hashmap.
     */
    private final HashMap<String, Set<Integer>> indexMap;

    /**
     * Constructs an inverted index data structure.
     */
    public  InvertedIndex(){
        this.indexMap = new HashMap<>();
    }

    /**
     * Calculates the indexes and stores in the hashtable.
     */
    public void addData(int idx, String word){
        if (!indexMap.containsKey(word)){
            indexMap.put(word, new HashSet<>());
        } else {
            indexMap.get(word).add(idx);
        }
    }

    /**
     * Gets the documents that contains the input string.
     * @param word   the search string.
     * @return a set of indexes for documents.
     */
    public Set<Integer> getDocumentSet(String word){
        if (indexMap.get(word) != null){
            return indexMap.get(word);
        }
        return new HashSet<>();
    }

    /**
     * Gets the list of documents that contains the input string.
     * @param words   the search words.
     * @return a list of sets of indexes for documents.
     */
    public ArrayList<Set<Integer>> getDocumentSets(Set<String> words){
        ArrayList<Set<Integer>> result = new ArrayList<>();
        for (String word : words) {
            result.add(getDocumentSet(word));
        }
        return result;
    }

}