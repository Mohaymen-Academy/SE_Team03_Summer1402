import file_reader.Document;
import file_reader.FileReader;
import file_reader.TxtFileReader;
import full_text_search.FullTextSearch;
import word_manipulation.normalization.RemoveMarksAndUpperCaseNormalizer;
import word_manipulation.tokenization.EdgeNgramTokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    /**
     * To use the full text search library, first you should construct
     * an instance of fullTextSearch.FullTextSearch class and give the path to your documents'
     * folder and normalization method and tokenizer. then you can search by
     * calling the Search method on your fullTextSearch.FullTextSearch instance.
     */
    public static void main(String[] args) throws IOException {
        // construct the fullTextSearch.FullTextSearch instance.
        FullTextSearch fts = new FullTextSearch(new RemoveMarksAndUpperCaseNormalizer(),
                new EdgeNgramTokenizer(2, 6));

        // read the documents in the folder and add them to search data.
        FileReader fileReader = new TxtFileReader();
        for (Document d : fileReader.getDocumentsInFolder("..\\Books")){
            fts.addDocument(d);
        }

        System.out.println("if you want to stop the program enter empty");
        while (true) {
            // get search input from the user
            System.out.println("Input your search phrase:");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String input = reader.readLine();

            // check to terminate the program
            if(input.strip().equals("")){
                break;
            }

            // call the search method and check for exception
            List<String> searchResult;
            try {
                searchResult = fts.search(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            // out put the result
            int i = 1;
            for (String s : searchResult){
                System.out.println(i + ". " + s);
                i++;
            }
        }
    }

}