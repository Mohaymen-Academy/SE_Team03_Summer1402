package unit_test;

import word_manipulation.normalization.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NormalizationTest{

    @Test
    public void normalize_upperCase(){
        Normalizer normalizer = new UpperCaseNormalizer();
        String actual1 = normalizer.normalize("abcd");
        String actual2 = normalizer.normalize("aBcD");
        String actual3 = normalizer.normalize("aB_cD");
        Assertions.assertEquals("ABCD", actual1);
        Assertions.assertEquals("ABCD", actual2);
        Assertions.assertEquals("AB_CD", actual3);
    }

    @Test
    public void normalize_removeMarks(){
        Normalizer normalizer = new RemoveMarksNormalizer();
        String actual1 = normalizer.normalize("aBcD");
        String actual2 = normalizer.normalize("aB_cD");
        String actual3 = normalizer.normalize("!@aB_c D#$");
        Assertions.assertEquals("aBcD", actual1);
        Assertions.assertEquals("aBcD", actual2);
        Assertions.assertEquals("aBcD", actual3);
    }

    @Test
    public void normalize_removeMarksAndUpperCase(){
        Normalizer normalizer = new RemoveMarksAndUpperCaseNormalizer();
        String actual1 = normalizer.normalize("aBcD");
        String actual2 = normalizer.normalize("aB_cD");
        String actual3 = normalizer.normalize("!@aB_c D#$");
        Assertions.assertEquals("ABCD", actual1);
        Assertions.assertEquals("ABCD", actual2);
        Assertions.assertEquals("ABCD", actual3);
    }

}