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
     * Search input categories.
     */
    private Categories categories;

    private Set<Integer> resultSet;

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
        documentsName.add(document.getName());
        ArrayList<String> words = new ArrayList<>();
        for (String word : tokenizer.Tokenize(document.getContext())){
            words.addAll(Arrays.asList(normalization.Normalize(word)));
        }
        invertedIndex.addData(documentsName.size() - 1, words);
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
        categories = new Categories(searchInput, normalization);
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
        resultSet = new HashSet<>();
        for (int i = 0; i < documentsName.size(); i++) {
            resultSet.add(i);
        }

        checkIncludeWords();
        checkExcludeWords();
        checkOptionalWords();

        return resultSet;
    }

    private void checkIncludeWords() {
        if (categories.getIncludeWords().size() > 0) {
            ArrayList<Set<Integer>> normalWordsResultSets =
                    invertedIndex.getDocumentSets(categories.getIncludeWords());
            normalWordsResultSets.add(resultSet);
            resultSet = SetLogic.intersect(normalWordsResultSets);
        }
    }

    private void checkExcludeWords() {
        if (categories.getExcludeWords().size() > 0) {
            Set<Integer> minusWordsResultSet =
                    SetLogic.union(invertedIndex.getDocumentSets(categories.getExcludeWords()));
            resultSet = SetLogic.subtract(resultSet, minusWordsResultSet);
        }
    }

    private void checkOptionalWords() {
        if (categories.getOptionalWords().size() > 0) {
            Set<Integer> plusWordsResultSet =
                    SetLogic.union(invertedIndex.getDocumentSets(categories.getOptionalWords()));
            resultSet = SetLogic.intersect(new ArrayList<>(Arrays.asList(resultSet, plusWordsResultSet)));
        }
    }

}