import java.util.ArrayList;
import java.util.List;

/**
 * Normalization interface.
 */
public interface Normalization {

    /**
     * Normalizes the input string.
     * @param inputString   string to normalize.
     * @return   array of string containing the normalized string from input string.
     */
    String[] Normalize(String inputString);

}

class UpperCaseNormalization implements Normalization{

    /**
     * Makes every letter in string uppercase.
     * @param inputString   string to normalize.
     * @return return an array on string containing only one upper case string.
     */
    public String[] Normalize(String inputString){
        return new String[]{inputString.toUpperCase()};
    }

}

class RemoveMarksNormalization implements Normalization{

    /**
     * Removes every character except letters and numbers and split the string into parts with only alphanumeric characters.
     * @param inputString   string to normalize.
     * @return return array of normalized strings with only alphanumeric characters.
     */
    public String[] Normalize(String inputString){
        List<String> list = new ArrayList<>();
        int lastIdx = 0;
        for(int i = 0; i < inputString.length(); i++){
            if(Character.isDigit(inputString.charAt(i)) || Character.isLetter((inputString.charAt(i)))) {
                continue;
            }
            if(i - lastIdx > 0){
                list.add(inputString.substring(lastIdx, i));
            }
            lastIdx = i + 1;
        }
        if(inputString.length() - lastIdx > 0){
            list.add(inputString.substring(lastIdx));
        }
        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }

}

class RemoveMarksAndUpperCaseNormalization implements Normalization{

    /**
     * Makes every character in string uppercase and then removes every
     * character except letters and numbers and split the string into parts with only alphanumeric characters.
     * @param inputString   string to normalize.
     * @return return array of normalized strings with only alphanumeric characters and uppercase letters.
     */
    public String[] Normalize(String inputString){
        Normalization upperCaseNormalization = new UpperCaseNormalization();
        Normalization removeMarksNormalization = new RemoveMarksNormalization();
        String upperCaseString = upperCaseNormalization.Normalize(inputString)[0];
        return removeMarksNormalization.Normalize(upperCaseString);
    }

}