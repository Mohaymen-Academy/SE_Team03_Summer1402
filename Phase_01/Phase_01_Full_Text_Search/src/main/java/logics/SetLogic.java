package logics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetLogic{

    /**
     * Unions input sets.
     * @param sets   input sets.
     * @return the union set of all input sets.
     */
    public static Set<Integer> union(ArrayList<Set<Integer>> sets){
        Set<Integer> result = new HashSet<>(sets.get(0));
        for (int i = 1; i < sets.size(); i++){
            result.addAll(sets.get(i));
        }
        return result;
    }

    /**
     * Intersects input sets.
     * @param sets   input sets.
     * @return the intersection of all input sets.
     */
    public static Set<Integer> intersect(ArrayList<Set<Integer>> sets){
        Set<Integer> result = new HashSet<>(sets.get(0));
        for (int i = 1; i < sets.size(); i++){
            result.retainAll(sets.get(i));
        }
        return result;
    }

    /**
     * Subtracts second input set from the first one.
     * @param set1   first input set.
     * @param set2   second input set.
     * @return the subtraction on input sets.
     */
    public static Set<Integer> subtract(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }
}