package unit_test;

import full_text_search.InputGroups;
import word_manipulation.normalization.RemoveMarksAndUpperCaseNormalizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InputGroupsTest {

    @Test
    public void inputGroups_onlyIncludeWords() throws Exception {
        String inputString = "DoG Hot";
        InputGroups inputGroups = new InputGroups(inputString, new RemoveMarksAndUpperCaseNormalizer());
        Set<String> actual = inputGroups.getIncludeWords();
        Set<String> expected = new HashSet<>(Arrays.asList("DOG", "HOT"));
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new HashSet<>(), inputGroups.getExcludeWords());
        Assertions.assertEquals(new HashSet<>(), inputGroups.getOptionalWords());
    }

    @Test
    public void inputGroups_onlyExcludeWords() throws Exception {
        String inputString = "-DoG -Hot";
        InputGroups inputGroups = new InputGroups(inputString, new RemoveMarksAndUpperCaseNormalizer());
        Set<String> actual = inputGroups.getExcludeWords();
        Set<String> expected = new HashSet<>(Arrays.asList("DOG", "HOT"));
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new HashSet<>(), inputGroups.getIncludeWords());
        Assertions.assertEquals(new HashSet<>(), inputGroups.getOptionalWords());
    }

    @Test
    public void inputGroups_onlyOptionalWords() throws Exception {
        String inputString = "+DoG +Hot";
        InputGroups inputGroups = new InputGroups(inputString, new RemoveMarksAndUpperCaseNormalizer());
        Set<String> actual = inputGroups.getOptionalWords();
        Set<String> expected = new HashSet<>(Arrays.asList("DOG", "HOT"));
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new HashSet<>(), inputGroups.getIncludeWords());
        Assertions.assertEquals(new HashSet<>(), inputGroups.getExcludeWords());
    }

    @Test
    public void inputGroups_allKindOfInput() throws Exception {
        String inputString = "+DoG Hello a +Hot -aLi -Boss WorK";
        InputGroups inputGroups = new InputGroups(inputString, new RemoveMarksAndUpperCaseNormalizer());
        Set<String> actual1 = inputGroups.getIncludeWords();
        Set<String> expected1 = new HashSet<>(Arrays.asList("HELLO", "WORK"));
        Set<String> actual2 = inputGroups.getExcludeWords();
        Set<String> expected2 = new HashSet<>(Arrays.asList("ALI", "BOSS"));
        Set<String> actual3 = inputGroups.getOptionalWords();
        Set<String> expected3 = new HashSet<>(Arrays.asList("DOG", "HOT"));
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);
        Assertions.assertEquals(expected3, actual3);
    }

    @Test
    public void inputGroups_onlyStopWords() {
        String inputString = "+a -my to +been";
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            new InputGroups(inputString, new RemoveMarksAndUpperCaseNormalizer());
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Please be more specific!";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void inputGroups_emptyInput() {
        String inputString = " ";
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            new InputGroups(inputString, new RemoveMarksAndUpperCaseNormalizer());
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Please be more specific!";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

}
