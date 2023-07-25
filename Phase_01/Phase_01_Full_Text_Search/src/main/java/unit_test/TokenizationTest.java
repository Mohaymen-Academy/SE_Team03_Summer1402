package unit_test;

import word_manipulation.tokenization.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TokenizationTest {

    @Test
    public void tokenize_stringTokenizerWhiteSpace(){
        Tokenizer tokenizer = new StringTokenizer(" ");
        String[] actual = tokenizer.tokenize("a bc !d $");
        String[] expected = {"a", "bc", "!d", "$"};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void tokenize_stringTokenizerLongSeparator(){
        Tokenizer tokenizer = new StringTokenizer("_ ");
        String[] actual = tokenizer.tokenize("a_ bc !d_ _e$");
        String[] expected = {"a", "bc !d", "_e$"};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void tokenize_marksTokenizer(){
        Tokenizer tokenizer = new MarksTokenizer();
        String[] actual = tokenizer.tokenize("a_ bc !d_ _e$");
        String[] expected = {"a", "bc", "d", "e"};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void tokenize_edgeNgramTokenizer(){
        Tokenizer tokenizer = new EdgeNgramTokenizer(2, 3);
        String[] actual = tokenizer.tokenize("AbCd_ _e123$");
        Arrays.sort(actual);
        String[] expected = {
                "Ab", "bC", "Cd",
                "AbC", "bCd",
                "AbCd",
                "e1", "12", "23",
                "e12", "123",
                "e123"};
        Arrays.sort(expected);
        Assertions.assertArrayEquals(expected, actual);
    }

}
