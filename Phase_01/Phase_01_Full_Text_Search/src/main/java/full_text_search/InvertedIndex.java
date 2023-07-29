package full_text_search;

import file_reader.Document;

import java.util.*;

public class InvertedIndex {

    /**
     * The inverted index hashmap.
     */
    private final HashMap<String, ArrayList<Occurrence>> indexMap;

    /**
     * Constructs an inverted index data structure.
     */
    public  InvertedIndex(){
        this.indexMap = new HashMap<>();
    }

    /**
     * Calculates the indexes and stores in the hashtable.
     */
    public void addData(Occurrence occurrence, String word){
        if (!indexMap.containsKey(word))
            indexMap.put(word, new ArrayList<>());
        indexMap.get(word).add(occurrence);
    }

    public void sortData(ArrayList<Document> documents){
        indexMap.forEach((key, value) -> {
            for (Occurrence occurrence : value){
                occurrence.calculateScore(documents.get(occurrence.getDocumentIndex()).getWordCount());
            }
            value.sort(new OccurrenceComparator());
        });
    }

    /**
     * Gets the documents that contains the input string.
     * @param word   the search string.
     * @return a set of indexes for documents.
     */
    public ArrayList<Occurrence> getOccurrences(String word){
        if (indexMap.get(word) != null){
            return indexMap.get(word);
        }
        return new ArrayList<>();
    }

    /**
     * Gets the list of documents that contains the input string.
     * @param words   the search words.
     * @return a list of sets of indexes for documents.
     */
    public ArrayList<ArrayList<Occurrence>> getListOccurrences(Set<String> words){
        ArrayList<ArrayList<Occurrence>> result = new ArrayList<>();
        for (String word : words) {
            result.add(getOccurrences(word));
        }
        return result;
    }

    public ArrayList<Set<Integer>> getDocumentSets(Set<String> words){
        ArrayList<Set<Integer>> result = new ArrayList<>();
        for (String word : words) {
            HashSet<Integer> set = new HashSet<>();
            for (Occurrence occurrence : getOccurrences(word)){
                System.out.println(occurrence.getDocumentIndex() + " = " + occurrence.getScore());
                set.add(occurrence.getDocumentIndex());
            }
            result.add(set);
        }
        return result;
    }

}