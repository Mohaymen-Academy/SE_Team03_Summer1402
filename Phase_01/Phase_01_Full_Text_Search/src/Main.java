import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // construct inverted index for documents
        FullTextSearch fts = new FullTextSearch("..\\Books", new RemoveMarksAndUpperCaseNormalization());

        // input and search
        System.out.println("if you want to stop the program enter empty");
        while (true) {
            System.out.println("Input your search phrase:");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String input = reader.readLine();

            if(input.strip().equals("")){
                break;
            }

            String[] searchResult = fts.Search(input);
            int i = 1;
            for (String s : searchResult){
                System.out.println(i + ". " + s);
                i++;
            }
        }
    }

}