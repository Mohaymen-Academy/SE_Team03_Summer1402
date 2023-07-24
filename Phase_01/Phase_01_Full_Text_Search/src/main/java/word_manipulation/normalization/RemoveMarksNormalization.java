package word_manipulation.normalization;

public class RemoveMarksNormalization implements Normalization {

    /**
     * Removes every character except letters and numbers.
     * @param inputString   the string to normalize.
     * @return the normalized string with only alphanumeric characters.
     */
    public String normalize(String inputString){
        String regex = "[^a-zA-Z0-9']+";
        return inputString.replaceAll(regex, "");
    }

}