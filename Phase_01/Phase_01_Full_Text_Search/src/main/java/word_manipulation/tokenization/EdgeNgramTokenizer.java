package word_manipulation.tokenization;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class EdgeNgramTokenizer implements Tokenizer {

    private final int minGram;
    private final int maxGram;
    private final int steps;

    @Override
    public String separator() {
        return " ";
    }

    @Override
    public String[] tokenize(String inputString) {
        String regex = "[^a-zA-Z0-9']+";
        ArrayList<String> words = new ArrayList<>(Arrays.asList(inputString.split(regex)));
        for (int i = 0; i < words.size(); i++) {
            for (int j = minGram; j < maxGram; j+=steps) {
                if(j < words.get(i).length()){
                    words.add(words.get(i).substring(0, j));
                }
            }
        }
        String[] result = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            result[i] = words.get(i);
        }
        return result;
    }
}