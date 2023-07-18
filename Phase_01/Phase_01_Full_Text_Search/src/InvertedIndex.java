import java.util.HashSet;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;

public class InvertedIndex {
    private Hashtable<String, Set<Integer>> inverted_map;
    private ArrayList<Set<String>> books;

    public  InvertedIndex(ArrayList<Set<String>> books){
        this.books = books;
        this.inverted_map = new Hashtable<String, Set<Integer>>();
        CalculateIndexes();
    }

    private void CalculateIndexes(){
        for (int i = 0; i < books.size(); i++){
            for (String word : books.get(i)){
                if(word.equals("")){
                    continue;
                }
                if(!inverted_map.containsKey(word)){
                    inverted_map.put(word, new HashSet<Integer>());
                }
                inverted_map.get(word).add(i);
            }
        }
    }

    public Set<Integer> GetDocumentSet(String word){
        if (inverted_map.get(word) != null){
            return inverted_map.get(word);
        }
        return new HashSet<Integer>();
    }

    public void PrintOutput(){
        for (String word : inverted_map.keySet()) {
            System.out.println("word : " + word
                    + "\t\t books : "
                    + inverted_map.get(word));
        }
    }
}