import java.util.*;

class Normalize{
    public static List<String> Normalized(String word)
    {
        List<String> result = new ArrayList<String>();
        int lastIdx = 0;
        for(int i = 0; i < word.length(); i++){
            if(Character.isDigit(word.charAt(i)) || Character.isLetter((word.charAt(i)))) {
                continue;
            }
            if(i - lastIdx > 0){
                result.add(word.substring(lastIdx, i));
            }
            lastIdx = i + 1;
        }
        if(word.length() - lastIdx > 0){
            result.add(word.substring(lastIdx, word.length()));
        }
        return result;
    }
}