package full_text_search;

import file_reader.Document;
import logics.SetLogic;
import word_manipulation.normalization.Normalizer;
import word_manipulation.tokenization.Tokenizer;
import lombok.Builder;
import java.util.*;
import java.util.stream.Collectors;

public class FullTextSearch {

    private final ArrayList<Document> documents;

    private final InvertedIndex invertedIndex;

    private final Normalizer normalizer;

    private final Tokenizer tokenizer;

    private InputGroups inputGroups;

    private Set<Integer> resultSet;

    /**
     * Constructs of full text search.
     * @param normalizer   normalizer.
     * @param tokenizer   tokenizer.
     */
    @Builder
    public FullTextSearch(Normalizer normalizer, Tokenizer tokenizer) {
        this.normalizer = normalizer;
        this.tokenizer = tokenizer;
        documents = new ArrayList<>();
        invertedIndex = new InvertedIndex();
    }

    /**
     * Add data to inverted index.
     * @param document   the document to add.
     */
    public void addDocument(Document document){
        int idx = documents.size();
        documents.add(document);
        HashMap<String, Integer> normalized = new HashMap<>();
        tokenizer.tokenize(document.getContext()).forEach((key, value) ->{
            String word = normalizer.normalize(key);
            if(!normalized.containsKey(word)){
                normalized.put(word, 0);
            }
            document.setWordCount(document.getWordCount() + value);
            normalized.put(word, normalized.get(word) + value);
        });
        for (Map.Entry<String, Integer> entry : normalized.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Occurrence occurrence = new Occurrence(idx);
            occurrence.setWordCount(value);
            occurrence.setIsInTitle(normalizer.normalize(document.getName()).contains(key));
            invertedIndex.addData(occurrence, key);
        }
        invertedIndex.sortData(documents);
    }

    /**
     * Search query.
     * @param searchInput   query.
     * @return name of documents that you request.
     * @throws Exception   if query is null or query just have stop words.
     */
    public List<String> search(String searchInput) throws Exception {
        if(searchInput.strip().equals(""))
            throw new Exception("Please enter some words!");

        inputGroups = new InputGroups(searchInput, normalizer);

        if(inputGroups.hasOnlyOneNormalWord()){
            return invertedIndex.getOccurrences(inputGroups.getIncludeWords().iterator().next())
                    .stream()
                    .map(x -> documents.get(x.getDocumentIndex()).getName())
                    .collect(Collectors.toList());
        }
        else{
            Set<Integer> resultSet = getSearchResult();
            return resultSet.stream()
                    .map(x -> documents.get(x).getName())
                    .collect(Collectors.toList());
        }
    }

    /**
     * finds number of documents with calculate logic set.
     * @return number of documents.
     */
    private Set<Integer> getSearchResult(){
        resultSet = new HashSet<>();
        for (int i = 0; i < documents.size(); i++) {
            resultSet.add(i);
        }

        checkIncludeWords();
        checkExcludeWords();
        checkOptionalWords();

        return resultSet;
    }

    /**
     * Removes any document in the result set that don't have the words that must be included.
     */
    private void checkIncludeWords() {
        if (!inputGroups.getIncludeWords().isEmpty()) {
            ArrayList<Set<Integer>> normalWordsResultSets =
                    invertedIndex.getDocumentSets(inputGroups.getIncludeWords());
            normalWordsResultSets.add(resultSet);
            resultSet = SetLogic.intersect(normalWordsResultSets);
        }
    }

    /**
     * Removes documents from result set if they have any words that must be excluded.
     */
    private void checkExcludeWords() {
        if (inputGroups.getExcludeWords().size() > 0) {
            Set<Integer> minusWordsResultSet =
                    SetLogic.union(invertedIndex.getDocumentSets(inputGroups.getExcludeWords()));
            resultSet = SetLogic.subtract(resultSet, minusWordsResultSet);
        }
    }

    /**
     * Removes document from result set that don't either of the optional words.
     */
    private void checkOptionalWords() {
        if (inputGroups.getOptionalWords().size() > 0) {
            Set<Integer> plusWordsResultSet =
                    SetLogic.union(invertedIndex.getDocumentSets(inputGroups.getOptionalWords()));
            resultSet = SetLogic.intersect(new ArrayList<>(Arrays.asList(resultSet, plusWordsResultSet)));
        }
    }

}