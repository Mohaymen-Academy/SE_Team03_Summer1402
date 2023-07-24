package word_manipulation.normalization;

public class UpperCaseNormalization implements Normalization {

    /**
     * Makes every letter in string uppercase.
     * @param inputString   the string to normalize.
     * @return the normalized string with only upper case letter.
     */
    public String normalize(String inputString){
        return inputString.toUpperCase();
    }

}