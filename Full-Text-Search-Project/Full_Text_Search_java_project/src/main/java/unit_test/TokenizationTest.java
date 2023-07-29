package unit_test;

import word_manipulation.tokenization.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class TokenizationTest {

    @Test
    public void tokenize_stringTokenizerWhiteSpace(){
        Tokenizer tokenizer = new StringTokenizer(" ");
        HashMap<String, Integer> actual = tokenizer.tokenize("a bc !d a $");
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("a", 2);
        expected.put("bc", 1);
        expected.put("!d", 1);
        expected.put("$", 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void tokenize_stringTokenizerLongSeparator(){
        Tokenizer tokenizer = new StringTokenizer("_ ");
        HashMap<String, Integer> actual = tokenizer.tokenize("a_ a_ bc !d_ _e$");
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("a", 2);
        expected.put("bc !d", 1);
        expected.put("_e$", 1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void tokenize_marksTokenizer(){
        Tokenizer tokenizer = new MarksTokenizer();
        HashMap<String, Integer> actual = tokenizer.tokenize("e!a_a!e bc !d_ _e$");
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("a", 2);
        expected.put("bc", 1);
        expected.put("d", 1);
        expected.put("e", 3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void tokenize_edgeNgramTokenizer(){
        Tokenizer tokenizer = new EdgeNgramTokenizer(2, 3);
        HashMap<String, Integer> actual = tokenizer.tokenize("AbCd_ _e12$e1");
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("Ab", 1);
        expected.put("bC", 1);
        expected.put("Cd", 1);
        expected.put("AbC", 1);
        expected.put("bCd", 1);
        expected.put("AbCd", 1);
        expected.put("e1", 2);
        expected.put("12", 1);
        expected.put("e12", 1);
        Assertions.assertEquals(expected, actual);
    }

}