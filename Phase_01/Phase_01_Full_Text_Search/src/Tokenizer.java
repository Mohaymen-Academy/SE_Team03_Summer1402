public interface Tokenizer {

    String[] Tokenize(String inputString);

}

class StringTokenizer implements Tokenizer{

    private final String separator;

    public StringTokenizer(String separator){
        this.separator = separator;
    }
    public String[] Tokenize(String inputString){
        return inputString.split(separator);
    }

}