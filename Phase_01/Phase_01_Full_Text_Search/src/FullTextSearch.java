import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FullTextSearch{

    private FileReader fileReader;
    private InvertedIndex invertedIndex;
    private static Normalization normalization;

    private static int inputCount;
    private static boolean containsStopWords;
    private static ArrayList<String> normalWords;
    private static ArrayList<String> plusWords;
    private static ArrayList<String> minusWords;

    public FullTextSearch(String documentsFolderPath, Normalization normalization) throws FileNotFoundException {
        this.normalization = normalization;
        fileReader = new FileReader(documentsFolderPath, normalization, "txt");
        invertedIndex = new InvertedIndex(fileReader.documents);
    }

    public String[] Search(String searchInput){
        ReadInput(searchInput);
        ArrayList<Integer> searchResult;
        Set<Integer> intersection = new HashSet<Integer>();
        if(normalWords.size() > 0){
            ArrayList<Set<Integer>> normalWordsResults = new ArrayList<Set<Integer>>();
            for (String word : normalWords){
                normalWordsResults.add(invertedIndex.GetDocumentSet(word));
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
            for (int i = 0; i < fileReader.documents.size(); i++){
                intersection.add(i);
            }
        }
        // minus words
        if(minusWords.size() > 0){
            for (String word : minusWords){
                for (int i : invertedIndex.GetDocumentSet(word)){
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
                plusWordsResults.add(invertedIndex.GetDocumentSet(word));
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
        String[] resultDocumentsNames = new String[searchResult.size()];
        for (int i = 0; i < resultDocumentsNames.length; i++) {
            resultDocumentsNames[i] = fileReader.documentsName[i];
        }
        return resultDocumentsNames;
    }

    public static Boolean ReadInput(String inputString){
        normalWords = new ArrayList<String>();
        plusWords = new ArrayList<String>();
        minusWords = new ArrayList<String>();
        for (String word : inputString.split(" ")){
            for(String w : normalization.Normalize(word)) {
                if (Stop_Words.words.contains(w.toLowerCase())) {
                    containsStopWords = true;
                    continue;
                }
                inputCount++;
                if (word.charAt(0) == '+') {
                    plusWords.add(w);
                } else if (word.charAt(0) == '-') {
                    minusWords.add(w);
                } else {
                    normalWords.add(w);
                }
            }
        }
        return true;
    }

}