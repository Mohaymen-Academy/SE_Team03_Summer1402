import java.util.ArrayList;
import java.util.List;

public interface Normalization {
    String[] Normalize(String inputString);
}

class UpperCaseNormalization implements Normalization{
    public String[] Normalize(String inputString){
        String[] result = {inputString.toUpperCase()};
        return result;
    }
}

class RemoveMarksNormalization implements Normalization{
    public String[] Normalize(String inputString){
        List<String> list = new ArrayList<String>();
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
            list.add(inputString.substring(lastIdx, inputString.length()));
        }
        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }
}