package word_manipulation.tokenization;

public class MarksTokenizer implements Tokenizer {

    @Override
    public String separator() {
        return " ";
    }

    @Override
    public String[] tokenize(String inputString) {
        String regex = "[^a-zA-Z0-9']+";
        return inputString.split(regex);
    }
}