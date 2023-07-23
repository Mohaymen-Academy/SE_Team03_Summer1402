package word_manipulation;

public class RemoveMarksAndUpperCaseNormalization implements Normalization {

    /**
     * Makes every character in string uppercase and then removes every
     * character except letters and numbers and split the string into parts with only alphanumeric characters.
     * @param inputString   string to normalize.
     * @return return array of normalized strings with only alphanumeric characters and uppercase letters.
     */
    public String[] normalize(String inputString){
        Normalization upperCaseNormalization = new UpperCaseNormalization();
        Normalization removeMarksNormalization = new RemoveMarksNormalization();
        String upperCaseString = upperCaseNormalization.normalize(inputString)[0];
        return removeMarksNormalization.normalize(upperCaseString);
    }

}