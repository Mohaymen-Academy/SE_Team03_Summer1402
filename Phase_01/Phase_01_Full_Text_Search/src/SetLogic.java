import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class SetLogic{

    /**
     * Unions input sets.
     * @param sets   input sets.
     * @return the union set of all input sets.
     */
    public static Set<Integer> Union(ArrayList<Set<Integer>> sets){
        Set<Integer> result = new HashSet<>();
        for (Set<Integer> set : sets) {
            result.addAll(set);
        }
        return result;
    }

    /**
     * Intersects input sets.
     * @param sets   input sets.
     * @return the intersection of all input sets.
     */
    public static Set<Integer> Intersection(ArrayList<Set<Integer>> sets){
        Set<Integer> result = new HashSet<>();
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

    /**
     * Subtracts second input set from the first one.
     * @param set1   first input set.
     * @param set2   second input set.
     * @return the subtraction on input sets.
     */
    public static Set<Integer> Subtract(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>();
        for(int num : set1){
            if(!set2.contains(num))
                result.add(num);
        }
        return result;
    }
}