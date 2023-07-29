package word_manipulation.normalization;

public class RemoveMarksAndUpperCaseNormalizer implements Normalizer {

    /**
     * Makes every character in string uppercase and then removes every
     * character except letters and numbers.
     * @param inputString   the string to normalize.
     * @return the normalized string with only alphanumeric characters and uppercase letters.
     */
    public String normalize(String inputString){
        Normalizer upperCaseNormalizer = new UpperCaseNormalizer();
        Normalizer removeMarksNormalizer = new RemoveMarksNormalizer();
        String upperCaseString = upperCaseNormalizer.normalize(inputString);
        return removeMarksNormalizer.normalize(upperCaseString);
    }

}