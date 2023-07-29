package unit_test;

import word_manipulation.StopWords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StopWordTest {

    @Test
    public void isStopWord_stopWords(){
        Assertions.assertTrue(StopWords.isStopWord("the"));
        Assertions.assertTrue(StopWords.isStopWord("a"));
        Assertions.assertTrue(StopWords.isStopWord(" "));
        Assertions.assertTrue(StopWords.isStopWord("my"));
    }

    @Test
    public void isStopWord_nonStopWords(){
        Assertions.assertFalse(StopWords.isStopWord("dog"));
        Assertions.assertFalse(StopWords.isStopWord("!"));
        Assertions.assertFalse(StopWords.isStopWord("book"));
        Assertions.assertFalse(StopWords.isStopWord("hello"));
    }

}
