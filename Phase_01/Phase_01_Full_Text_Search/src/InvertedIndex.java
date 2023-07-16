import java.util.Hashtable;
import java.util.ArrayList;

public class InvertedIndex {
    private Hashtable<String, ArrayList<Integer>> inverted_map;
    private String[][] books;

    public  InvertedIndex(String[][] books){
        this.books = books;
        this.inverted_map = new Hashtable<String, ArrayList<Integer>>();
        CalculateIndexes();
    }

    private void CalculateIndexes(){
        for (int i = 0; i < books.length; i++){
            for (String word : books[i]){
                word = word.toLowerCase();
                if(!inverted_map.containsKey(word)){
                    inverted_map.put(word, new ArrayList<Integer>());
                }
                inverted_map.get(word).add(i);
            }
        }
    }

    public void PrintOutput(){
        for (String word : inverted_map.keySet()) {
            System.out.println("word : " + word
                    + "\t\t books : "
                    + inverted_map.get(word));
        }
    }
}