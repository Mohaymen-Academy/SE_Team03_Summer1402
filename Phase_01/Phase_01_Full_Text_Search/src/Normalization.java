/**
 * Normalization interface.
 */
public interface Normalization {

    /**
     * Normalizes the input string.
     * @param inputString   string to normalize.
     * @return   array of string containing the normalized string from input string.
     */
    String[] normalize(String inputString);

}

class UpperCaseNormalization implements Normalization{

    /**
     * Makes every letter in string uppercase.
     * @param inputString   string to normalize.
     * @return return an array on string containing only one upper case string.
     */
    public String[] normalize(String inputString){
        return new String[]{inputString.toUpperCase()};
    }

}

class RemoveMarksNormalization implements Normalization{

    /**
     * Removes every character except letters and numbers and split the string into parts with only alphanumeric characters.
     * @param inputString   string to normalize.
     * @return return array of normalized strings with only alphanumeric characters.
     */
    public String[] normalize(String inputString){
        String regex = "[^a-zA-Z0-9']+";
        return inputString.split(regex);
    }

}

class RemoveMarksAndUpperCaseNormalization implements Normalization{

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