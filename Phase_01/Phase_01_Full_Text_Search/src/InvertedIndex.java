import java.util.HashSet;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;

public class InvertedIndex {

    /**
     * The inverted index hashtable.
     */
    private final Hashtable<String, Set<Integer>> indexMap;


    /**
     * Constructs an inverted index data structure.
     */
    public  InvertedIndex(){
        this.indexMap = new Hashtable<>();
    }

    /**
     * Calculates the indexes and stores in the hashtable.
     */
    public void AddDada(int idx, ArrayList<String> words){
        for (String word : words){
            if(!indexMap.containsKey(word)){
                indexMap.put(word, new HashSet<>());
            }
            indexMap.get(word).add(idx);
        }
    }

    /**
     * Gets the documents that contains the word.
     * @param word   the search word.
     * @return a set of indexes for documents.
     */
    public Set<Integer> GetDocumentSet(String word){
        if (indexMap.get(word) != null){
            return indexMap.get(word);
        }
        return new HashSet<>();
    }

    /**
     * Gets the list of documents that contains the words.
     * @param words   the search words.
     * @return a list of sets of indexes for documents.
     */
    public ArrayList<Set<Integer>> GetDocumentSets(Set<String> words){
        ArrayList<Set<Integer>> result = new ArrayList<>();
        for (String word : words) {
            result.add(GetDocumentSet(word));
        }
        return result;
    }

}