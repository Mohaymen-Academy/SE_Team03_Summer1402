import file_reader.*;
import full_text_search.FullTextSearch;
import word_manipulation.normalization.RemoveMarksAndUpperCaseNormalizer;
import word_manipulation.tokenization.EdgeNgramTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static String endingString = "$";

    /**
     * To use the full text search library, first you should construct
     * an instance of fullTextSearch.FullTextSearch class and give the path to your documents'
     * folder and normalization method and tokenizer. then you can search by
     * calling the Search method on your fullTextSearch.FullTextSearch instance.
     */
    public static void main(String[] args) throws IOException {
        FullTextSearch fts = FullTextSearch.builder()
                .normalizer(new RemoveMarksAndUpperCaseNormalizer())
                .tokenizer(new EdgeNgramTokenizer(2, 6))
                .build();

        FileReader fileReader = new TxtFileReader();
        for (Document d : fileReader.getDocumentsInFolder("..\\Books")){
            fts.addDocument(d);
        }

        System.out.println("if you want to stop the program enter " + endingString);
        while (true) {
            System.out.println("Input your search phrase:");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String input = reader.readLine();

            if(input.strip().equals(endingString)){
                break;
            }

            List<String> searchResult = Search(fts, input);
            DisplayResults(searchResult);
        }
    }

    private static List<String> Search(FullTextSearch fts, String input){
        List<String> searchResult = new ArrayList<>();
        try {
            searchResult = fts.search(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return searchResult;
    }

    private static void DisplayResults(List<String> searchResult){
        int i = 1;
        for (String s : searchResult){
            System.out.println(i + ". " + s);
            i++;
        }
    }

}