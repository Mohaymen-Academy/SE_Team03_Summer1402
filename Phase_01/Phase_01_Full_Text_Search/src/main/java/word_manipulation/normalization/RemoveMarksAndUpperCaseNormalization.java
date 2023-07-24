package word_manipulation.normalization;

public class RemoveMarksAndUpperCaseNormalization implements Normalization {

    /**
     * Makes every character in string uppercase and then removes every
     * character except letters and numbers.
     * @param inputString   the string to normalize.
     * @return the normalized string with only alphanumeric characters and uppercase letters.
     */
    public String normalize(String inputString){
        Normalization upperCaseNormalization = new UpperCaseNormalization();
        Normalization removeMarksNormalization = new RemoveMarksNormalization();
        String upperCaseString = upperCaseNormalization.normalize(inputString);
        return removeMarksNormalization.normalize(upperCaseString);
    }

}