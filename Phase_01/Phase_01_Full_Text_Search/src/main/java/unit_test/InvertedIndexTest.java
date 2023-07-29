package unit_test;

import full_text_search.InvertedIndex;
import full_text_search.Occurrence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvertedIndexTest {

    @Test
    public void addData_onWordInOnDocument() {
        InvertedIndex invertedIndex = new InvertedIndex();
        Occurrence occurrence = new Occurrence(3);
        invertedIndex.addData(occurrence, "Hossein");

        ArrayList<Occurrence> resultOccurrences = invertedIndex.getOccurrences("Hossein");
        Set<Integer> actual = new HashSet<>();
        resultOccurrences.forEach(x -> actual.add(x.getDocumentIndex()));
        Set<Integer> expected = Stream.of(3).collect(Collectors.toSet());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addData_onWordInManyDocuments() {
        InvertedIndex invertedIndex = new InvertedIndex();

        invertedIndex.addData(new Occurrence(1), "Hello");
        invertedIndex.addData(new Occurrence(2), "Hello");
        invertedIndex.addData(new Occurrence(3), "Hello");

        ArrayList<Occurrence> resultOccurrences = invertedIndex.getOccurrences("Hello");
        Set<Integer> actual = new HashSet<>();
        resultOccurrences.forEach(x -> actual.add(x.getDocumentIndex()));
        Set<Integer> expected = Stream.of(1, 2, 3).collect(Collectors.toSet());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addData_manyWordsInOneDocument() {
        InvertedIndex invertedIndex = new InvertedIndex();

        invertedIndex.addData(new Occurrence(1), "Hello");
        invertedIndex.addData(new Occurrence(2), "Hossein");
        invertedIndex.addData(new Occurrence(3), "Ali");

        ArrayList<Occurrence> resultOccurrences1 = invertedIndex.getOccurrences("Hello");
        Set<Integer> actual1 = new HashSet<>();
        resultOccurrences1.forEach(x -> actual1.add(x.getDocumentIndex()));
        Set<Integer> expected1 = Stream.of(1).collect(Collectors.toSet());
        Assertions.assertEquals(expected1, actual1);

        ArrayList<Occurrence> resultOccurrences2 = invertedIndex.getOccurrences("Hossein");
        Set<Integer> actual2 = new HashSet<>();
        resultOccurrences2.forEach(x -> actual2.add(x.getDocumentIndex()));
        Set<Integer> expected2 = Stream.of(2).collect(Collectors.toSet());
        Assertions.assertEquals(expected2, actual2);

        ArrayList<Occurrence> resultOccurrences3 = invertedIndex.getOccurrences("Ali");
        Set<Integer> actual3 = new HashSet<>();
        resultOccurrences3.forEach(x -> actual3.add(x.getDocumentIndex()));
        Set<Integer> expected3 = Stream.of(3).collect(Collectors.toSet());
        Assertions.assertEquals(expected3, actual3);

    }

    @Test
    public void addData_manyWordsInManyDocuments() {
        InvertedIndex invertedIndex = new InvertedIndex();

        invertedIndex.addData(new Occurrence(1), "Hello");
        invertedIndex.addData(new Occurrence(1), "Hossein");
        invertedIndex.addData(new Occurrence(1), "Ali");
        invertedIndex.addData(new Occurrence(2), "Ali");
        invertedIndex.addData(new Occurrence(3), "Ali");
        invertedIndex.addData(new Occurrence(2), "By");

        ArrayList<Set<Integer>> expected = new ArrayList<>();
        expected.add(Stream.of(2).collect(Collectors.toSet()));
        expected.add(Stream.of(1, 2, 3).collect(Collectors.toSet()));

        Set<String> input = Stream.of("By", "Ali").collect(Collectors.toSet());
        ArrayList<Set<Integer>> actual = invertedIndex.getDocumentSets(input);

        Assertions.assertEquals(expected, actual);
    }

}
