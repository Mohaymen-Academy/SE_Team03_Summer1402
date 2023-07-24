package logics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SetLogic{

    /**
     * Unions input sets.
     * @param sets   input sets.
     * @return the union set of all input sets.
     */
    public static Set<Integer> union(Stream<Set<Integer>> sets){
        Set<Integer> result = new HashSet<>();
        sets.forEach(result::addAll);
        return result;
    }

    /**
     * Intersects input sets.
     * @param sets   input sets.
     * @return the intersection of all input sets.
     */
    public static Set<Integer> intersect(Stream<Set<Integer>> sets){
        if(sets.findAny().isEmpty())
            return new HashSet<>();

        Set<Integer> result = new HashSet<>(sets.findAny().get());
        sets.forEach(result::retainAll);
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