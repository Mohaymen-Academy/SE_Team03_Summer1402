package unit_test;

import full_text_search.InputGroups;
import word_manipulation.normalization.Normalizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputGroupsTest {

    @Test
    public void inputGroups_onlyIncludeWords() throws Exception {
        Normalizer mockNormalizer = mock(Normalizer.class);
        when(mockNormalizer.normalize(anyString()))
                .thenAnswer(i -> switch (i.getArguments()[0].toString()) {
                    case "DoG" -> "DOG";
                    case "Hot" -> "HOT";
                    default -> "";
                });

        String inputString = "DoG Hot";
        InputGroups inputGroups = new InputGroups(inputString, mockNormalizer);
        Set<String> actual = inputGroups.getIncludeWords();
        Set<String> expected = new HashSet<>(Arrays.asList("DOG", "HOT"));
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new HashSet<>(), inputGroups.getExcludeWords());
        Assertions.assertEquals(new HashSet<>(), inputGroups.getOptionalWords());
    }

    @Test
    public void inputGroups_onlyExcludeWords() throws Exception {
        Normalizer mockNormalizer = mock(Normalizer.class);
        when(mockNormalizer.normalize(anyString()))
                .thenAnswer(i -> switch (i.getArguments()[0].toString()) {
                    case "-DoG" -> "DOG";
                    case "-Hot" -> "HOT";
                    default -> "";
                });

        String inputString = "-DoG -Hot";
        InputGroups inputGroups = new InputGroups(inputString, mockNormalizer);
        Set<String> actual = inputGroups.getExcludeWords();
        Set<String> expected = new HashSet<>(Arrays.asList("DOG", "HOT"));
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new HashSet<>(), inputGroups.getIncludeWords());
        Assertions.assertEquals(new HashSet<>(), inputGroups.getOptionalWords());
    }

    @Test
    public void inputGroups_onlyOptionalWords() throws Exception {
        Normalizer mockNormalizer = mock(Normalizer.class);
        when(mockNormalizer.normalize(anyString()))
                .thenAnswer(i -> switch (i.getArguments()[0].toString()) {
                    case "+DoG" -> "DOG";
                    case "+Hot" -> "HOT";
                    default -> "";
                });

        String inputString = "+DoG +Hot";
        InputGroups inputGroups = new InputGroups(inputString, mockNormalizer);
        Set<String> actual = inputGroups.getOptionalWords();
        Set<String> expected = new HashSet<>(Arrays.asList("DOG", "HOT"));
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(new HashSet<>(), inputGroups.getIncludeWords());
        Assertions.assertEquals(new HashSet<>(), inputGroups.getExcludeWords());
    }

    @Test
    public void inputGroups_allKindOfInput() throws Exception {
        Normalizer mockNormalizer = mock(Normalizer.class);
        when(mockNormalizer.normalize(anyString()))
                .thenAnswer(i -> switch (i.getArguments()[0].toString()) {
                    case "+DoG" -> "DOG";
                    case "Hello" -> "HELLO";
                    case "a" -> "A";
                    case "+Hot" -> "HOT";
                    case "-aLi" -> "ALI";
                    case "-Boss" -> "BOSS";
                    case "WorK" -> "WORK";
                    default -> "";
                });

        String inputString = "+DoG Hello a +Hot -aLi -Boss WorK";
        InputGroups inputGroups = new InputGroups(inputString, mockNormalizer);
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
        Normalizer mockNormalizer = mock(Normalizer.class);
        when(mockNormalizer.normalize(anyString()))
                .thenAnswer(i -> switch (i.getArguments()[0].toString()) {
                    case "+a" -> "A";
                    case "-my" -> "MY";
                    case "to" -> "TO";
                    case "+been" -> "BEEN";
                    default -> "";
                });

        String inputString = "+a -my to +been";
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            new InputGroups(inputString, mockNormalizer);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Please be more specific!";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void inputGroups_emptyInput() {
        Normalizer mockNormalizer = mock(Normalizer.class);
        when(mockNormalizer.normalize(anyString()))
                .thenAnswer(i -> switch (i.getArguments()[0].toString()) {
                    case " " -> "";
                    default -> "";
                });

        String inputString = " ";
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            new InputGroups(inputString, mockNormalizer);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "Please be more specific!";
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

}