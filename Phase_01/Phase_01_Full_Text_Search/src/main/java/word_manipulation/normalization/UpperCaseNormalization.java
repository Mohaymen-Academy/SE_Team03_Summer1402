package word_manipulation.normalization;

public class UpperCaseNormalization implements Normalization {

    /**
     * Makes every letter in string uppercase.
     * @param inputString   string to normalize.
     * @return return an array on string containing only one upper case string.
     */
    public String normalize(String inputString){
        return inputString.toUpperCase();
    }

}