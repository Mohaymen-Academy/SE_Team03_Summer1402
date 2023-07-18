import java.util.HashSet;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;

public class InvertedIndex {

    /**
     * The inverted index hashtable.
     */
    private Hashtable<String, Set<Integer>> indexMap;

    /**
     * The documents' content.
     */
    private ArrayList<Set<String>> documents;


    /**
     * Constructs an inverted index data structure and calculates the indexes.
     * @param documents   the documents content.
     */
    public  InvertedIndex(ArrayList<Set<String>> documents){
        this.documents = documents;
        this.indexMap = new Hashtable<String, Set<Integer>>();
        CalculateIndexes();
    }

    /**
     * Calculates the indexes and stores in the hashtable.
     */
    private void CalculateIndexes(){
        for (int i = 0; i < documents.size(); i++){
            for (String word : documents.get(i)){
                if(!indexMap.containsKey(word)){
                    indexMap.put(word, new HashSet<Integer>());
                }
                indexMap.get(word).add(i);
            }
        }
    }

    /**
     * Gets the documents that contains the word.
     * @param word   the search word.
     * @return a set on indexes for documents.
     */
    public Set<Integer> GetDocumentSet(String word){
        if (indexMap.get(word) != null){
            return indexMap.get(word);
        }
        return new HashSet<Integer>();
    }

    public void PrintOutput(){
        for (String word : indexMap.keySet()) {
            System.out.println("word : " + word
                    + "\t\t books : "
                    + indexMap.get(word));
        }
    }

}