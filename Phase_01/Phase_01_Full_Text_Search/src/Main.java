import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static ArrayList<Set<String>> documents;
    private static ArrayList<String> normalWords;
    private static ArrayList<String> plusWords;
    private static ArrayList<String> minusWords;
    private static InvertedIndex ii;
    private static ArrayList<Integer> searchResult;

    public static void main(String[] args) throws IOException {
        // construct inverted index for documents
        documents = FileReader.ReadBooks("..\\Books");
        ii = new InvertedIndex(documents);
        // input and search
        ReadInput();
        Search();
        System.out.println(searchResult);
    }

    public static void ReadInput() throws IOException {
        normalWords = new ArrayList<String>();
        plusWords = new ArrayList<String>();
        minusWords = new ArrayList<String>();
        System.out.println("Input your search phrase:");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String input = reader.readLine();
        for (String word : input.split(" ")){
            if (word.charAt(0) == '+'){
                plusWords.add(word.substring(1));
            }
            else if (word.charAt(0) == '-'){
                minusWords.add(word.substring(1));
            }
            else{
                normalWords.add(word);
            }
        }
    }

    public static void Search(){
        // intersection of normal words output
        Set<Integer> intersection = new HashSet<Integer>();
        if(normalWords.size() > 0){
            ArrayList<Set<Integer>> normalWordsResults = new ArrayList<Set<Integer>>();
            for (String word : normalWords){
                normalWordsResults.add(ii.GetDocumentSet(word));
            }
            for (int i : normalWordsResults.get(0)){
                boolean include = true;
                for (Set<Integer> set : normalWordsResults){
                    if(!set.contains(i)){
                        include = false;
                    }
                }
                if (include){
                    intersection.add(i);
                }
            }
        }
        else{
            for (int i = 0; i < documents.size(); i++){
                intersection.add(i);
            }
        }
        // minus words
        if(minusWords.size() > 0){
            for (String word : minusWords){
                for (int i : ii.GetDocumentSet(word)){
                    if (intersection.contains(i)){
                        intersection.remove(i);
                    }
                }
            }
        }
        // plus words
        searchResult = new ArrayList<Integer>();
        if(plusWords.size() > 0){
            ArrayList<Set<Integer>> plusWordsResults = new ArrayList<Set<Integer>>();
            for (String word : plusWords){
                plusWordsResults.add(ii.GetDocumentSet(word));
            }
            for (int i : intersection){
                boolean found = false;
                for (Set<Integer> set : plusWordsResults){
                    if (set.contains(i)){
                        found = true;
                    }
                }
                if (found){
                    searchResult.add(i);
                }
            }
        }
        else{
            searchResult.addAll(intersection);
        }
    }
}