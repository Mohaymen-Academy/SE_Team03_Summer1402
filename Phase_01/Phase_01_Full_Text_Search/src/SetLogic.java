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

    public static Set<Integer> Intersection(ArrayList<Set<Integer>> sets){
        Set<Integer> result = new HashSet<Integer>();
        for (int num : sets.get(0)){
            boolean allSetsContainNumber = true;
            for (int i = 1; i < sets.size(); i++) {
                if(!sets.get(i).contains(num)){
                    allSetsContainNumber = false;
                    break;
                }
            }
            if(allSetsContainNumber){
                result.add(num);
            }
        }
        return result;
    }

    public static Set<Integer> Subtract(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<Integer>();
        for(int num : set1){
            if(!set2.contains(num))
                result.add(num);
        }
        return result;
    }
}