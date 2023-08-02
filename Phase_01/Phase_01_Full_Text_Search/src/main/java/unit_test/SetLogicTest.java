package unit_test;

import logics.SetLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetLogicTest {

    @Test
    public void union_allSets () {
        Set<Integer> set1 = Stream.of(1, 2, 3 ).collect(Collectors.toCollection(HashSet::new));
        Set<Integer> set2 = Stream.of(2, 3, 5 ).collect(Collectors.toCollection(HashSet::new));
        Set<Integer> set3 = Stream.of(4, 6).collect(Collectors.toCollection(HashSet::new));
        ArrayList<Set<Integer>> arrayListSet = Stream.of(set1, set2, set3).collect((Collectors.toCollection((ArrayList::new))));

        Set<Integer> actual = SetLogic.union(arrayListSet);
        Set<Integer> expected = Stream.of(2, 1, 3, 4, 5, 6 ).collect(Collectors.toCollection(HashSet::new));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void intersect_allSets () {
        Set<Integer> set1 = Stream.of(1, 2, 3 ).collect(Collectors.toCollection(HashSet::new));
        Set<Integer> set2 = Stream.of(2, 3, 5 ).collect(Collectors.toCollection(HashSet::new));
        Set<Integer> set3 = Stream.of(2, 5).collect(Collectors.toCollection(HashSet::new));
        ArrayList<Set<Integer>> arrayListSet = Stream.of(set1, set2, set3).collect((Collectors.toCollection((ArrayList::new))));

        Set<Integer> actual = SetLogic.intersect(arrayListSet);
        Set<Integer> expected = Stream.of(2).collect(Collectors.toCollection(HashSet::new));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void subtract_twoSets () {
        Set<Integer> set1 = Stream.of(1, 2, 3, 4 ).collect(Collectors.toCollection(HashSet::new));
        Set<Integer> set2 = Stream.of(2, 3, 5 ).collect(Collectors.toCollection(HashSet::new));

        Set<Integer> actual = SetLogic.subtract(set1, set2);
        Set<Integer> expected = Stream.of(1, 4).collect(Collectors.toCollection(HashSet::new));

        Assertions.assertEquals(expected, actual);
    }

}