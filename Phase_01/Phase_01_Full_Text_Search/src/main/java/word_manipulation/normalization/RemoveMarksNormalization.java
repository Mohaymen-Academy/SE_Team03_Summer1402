package word_manipulation.normalization;

public class RemoveMarksNormalization implements Normalization {

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