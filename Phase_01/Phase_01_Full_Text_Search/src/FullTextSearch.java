import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FullTextSearch {

    /**
     * The name of documents.
     */
    private final ArrayList<String> documentsName;

    /**
     * Inverted index.
     */
    private final InvertedIndex invertedIndex;
    /**
     * Normalize content.
     */
    private final Normalization normalization;

    /**
     * Tokenize method.
     */
    private final Tokenizer tokenizer;

    /**
     * Count of accept words.
     */
    private int inputCount;

    /**
     * Have stop words.
     */
    private boolean containsStopWords;

    /**
     * Words that must be in the result.
     */
    private ArrayList<String> normalWords;

    /**
     * words that at least on of them must be in the result.
     */
    private ArrayList<String> plusWords;

    /**
     * Words that mustn't be in the result.
     */
    private ArrayList<String> minusWords;

    /**
     * Constructor of full text search.
     * @param normalization   normalization method.
     * @param tokenizer   tokenizer method.
     */
    public FullTextSearch(Normalization normalization, Tokenizer tokenizer) {
        this.normalization = normalization;
        this.tokenizer = tokenizer;
        documentsName = new ArrayList<>();
        invertedIndex = new InvertedIndex();
    }

    /**
     * Add data to inverted index.
     * @param document   the document to add.
     */
    public void AddDocument(Document document){
        documentsName.add(document.name);
        ArrayList<String> words = new ArrayList<>();
        for (String word : tokenizer.Tokenize(document.context)){
            words.addAll(Arrays.asList(normalization.Normalize(word)));
        }
        invertedIndex.AddDada(documentsName.size() - 1, words);
    }

    /**
     * Search query.
     * @param searchInput   query.
     * @return   name of documents that you request.
     * @throws Exception   if query is null or query just have stop words.
     */
    public String[] Search(String searchInput) throws Exception {
        if(searchInput.strip().equals("")){
            throw new Exception("Please enter some words!");
        }
        ReadInput(searchInput);
        if(inputCount == 0 && containsStopWords){
            throw new Exception("Please be more specific!");
        }
        Set<Integer> resultSet = GetSearchResult();
        return GetResultDocumentsNames(resultSet);
    }

    /**
     * Gets name of documents.
     * @param resultSet   documents' number.
     * @return   name of documents.
     */
    private String[] GetResultDocumentsNames(Set<Integer> resultSet){
        String[] resultDocumentsNames = new String[resultSet.size()];
        int j = 0;
        for (int i : resultSet) {
            resultDocumentsNames[j] = documentsName.get(i);
            j++;
        }
        return resultDocumentsNames;
    }

    /**
     * finds number of documents with calculate logic set.
     * @return   number of documents.
     */
    private Set<Integer> GetSearchResult(){
        Set<Integer> resultSet = new HashSet<>();
        for (int i = 0; i < documentsName.size(); i++) {
            resultSet.add(i);
        }

        if (normalWords.size() > 0) {
            ArrayList<Set<Integer>> normalWordsResultSets = invertedIndex.GetDocumentSets(normalWords);
            normalWordsResultSets.add(resultSet);
            resultSet = SetLogic.Intersection(normalWordsResultSets);
        }

        if (minusWords.size() > 0) {
            Set<Integer> minusWordsResultSet = SetLogic.Union(invertedIndex.GetDocumentSets(minusWords));
            resultSet = SetLogic.Subtract(resultSet, minusWordsResultSet);
        }

        if (plusWords.size() > 0) {
            Set<Integer> plusWordsResultSet = SetLogic.Union(invertedIndex.GetDocumentSets(plusWords));
            resultSet = SetLogic.Intersection(new ArrayList<>(Arrays.asList(resultSet, plusWordsResultSet)));
        }

        return resultSet;
    }

    /**
     * Processes the search input and separate words.
     * @param inputString   input string.
     */
    private void ReadInput(String inputString) {
        normalWords = new ArrayList<>();
        plusWords = new ArrayList<>();
        minusWords = new ArrayList<>();
        for (String word : inputString.split(" ")) {
            for (String w : normalization.Normalize(word)) {
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
    }

}