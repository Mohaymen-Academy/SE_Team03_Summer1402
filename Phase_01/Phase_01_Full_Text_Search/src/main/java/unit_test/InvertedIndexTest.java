package unit_test;

import full_text_search.InvertedIndex;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvertedIndexTest {

//    @Test
//    public void addData_onWordInOnDocument() {
//        InvertedIndex invertedIndex = new InvertedIndex();
//
//        invertedIndex.addData(3, "Hossein");
//
//        Set<Integer> actual = Stream.of(3).collect(Collectors.toSet());
//        Set<Integer> expected = invertedIndex.getOccurrence("Hossein");
//
//        Assertions.assertEquals(actual, expected);
//    }
//
//    @Test
//    public void addData_onWordInManyDocuments() {
//        InvertedIndex invertedIndex = new InvertedIndex();
//
//        invertedIndex.addData(1, "Hello");
//        invertedIndex.addData(2, "Hello");
//        invertedIndex.addData(3, "Hello");
//
//
//        Set<Integer> actual = Stream.of(1, 2, 3).collect(Collectors.toSet());
//        Set<Integer> expected = invertedIndex.getOccurrence("Hello");
//        Assertions.assertEquals(actual, expected);
//    }
//
//    @Test
//    public void addData_manyWordsInOneDocument() {
//        InvertedIndex invertedIndex = new InvertedIndex();
//
//        invertedIndex.addData(1, "Hello");
//        invertedIndex.addData(2, "Hossein");
//        invertedIndex.addData(3, "Ali");
//
//        Set<Integer> actual1 = Stream.of(1).collect(Collectors.toSet());
//        Set<Integer> expected1 = invertedIndex.getOccurrence("Hello");
//        Assertions.assertEquals(actual1, expected1);
//
//        Set<Integer> actual2 = Stream.of(2).collect(Collectors.toSet());
//        Set<Integer> expected2 = invertedIndex.getOccurrence("Hossein");
//        Assertions.assertEquals(actual2, expected2);
//
//        Set<Integer> actual3 = Stream.of(3).collect(Collectors.toSet());
//        Set<Integer> expected3 = invertedIndex.getOccurrence("Ali");
//        Assertions.assertEquals(actual3, expected3);
//
//    }
//
//    @Test
//    public void addData_manyWordsInManyDocuments() {
//        InvertedIndex invertedIndex = new InvertedIndex();
//
//        invertedIndex.addData(1, "Hello");
//        invertedIndex.addData(1, "Hossein");
//        invertedIndex.addData(1, "Ali");
//        invertedIndex.addData(2, "Ali");
//        invertedIndex.addData(3, "Ali");
//        invertedIndex.addData(2, "By");
//
//        ArrayList<Set<Integer>> actual = new ArrayList<>();
//        actual.add(Stream.of(2).collect(Collectors.toSet()));
//        actual.add(Stream.of(1, 2, 3).collect(Collectors.toSet()));
//        Set<String> input = Stream.of("By", "Ali").collect(Collectors.toSet());
//        ArrayList<Set<Integer>> expected = invertedIndex.getDocumentSets(input);
//        Assertions.assertEquals(actual, expected);
//    }

}
