package word_manipulation.normalization;

public class UpperCaseNormalizer implements Normalizer {

    /**
     * Makes every letter in string uppercase.
     * @param inputString   the string to normalize.
     * @return the normalized string with only upper case letter.
     */
    public String normalize(String inputString){
        return inputString.toUpperCase();
    }

}