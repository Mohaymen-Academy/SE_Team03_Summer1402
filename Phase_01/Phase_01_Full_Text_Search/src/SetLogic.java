import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class SetLogic{
    public static Set<Integer> Union(ArrayList<Set<Integer>> sets){
        Set<Integer> result = new HashSet<Integer>();
        for(int i = 0; i < sets.size(); i++)
        {
            for(int num : sets.get(i)){
               result.add(num);
            }
        }
        return result;
    }

    public static Set<Integer> subtract(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<Integer>();
        for(int num : set1){
            if(!set2.contains(num))
                result.add(num);
        }
        return result;
    }
}