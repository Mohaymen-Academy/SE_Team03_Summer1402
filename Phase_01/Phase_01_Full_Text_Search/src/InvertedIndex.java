import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;

public class InvertedIndex {
    private Hashtable<String, ArrayList<Integer>> inverted_map;
    private ArrayList<Set<String>> books;

    public  InvertedIndex(ArrayList<Set<String>> books){
        this.books = books;
        this.inverted_map = new Hashtable<String, ArrayList<Integer>>();
        CalculateIndexes();
    }

    private void CalculateIndexes(){
        for (int i = 0; i < books.stream().count(); i++){
            for (String word : books.get(i)){
                word = word.toLowerCase();
                if(word.equals("")){
                    continue;
                }
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