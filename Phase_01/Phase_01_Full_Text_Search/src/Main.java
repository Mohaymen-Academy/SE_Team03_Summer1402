import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * To use the full text search library, first you should construct
     * an instance of FullTextSearch class and give the path to your documents'
     * folder and normalization method and tokenizer. then you can search by
     * calling the Search method on your FullTextSearch instance.
     */
    public static void main(String[] args) throws IOException {
        // construct the FullTextSearch instance.
        FullTextSearch fts = new FullTextSearch(new RemoveMarksAndUpperCaseNormalization(),
                new StringTokenizer(" "));

        // read the documents in the folder and add them to search data.
        for (Document d : FileReader.GetDocumentsInFolder("..\\Books", "txt")){
            fts.AddDocument(d);
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
            String[] searchResult;
            try {
                searchResult = fts.Search(input);
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