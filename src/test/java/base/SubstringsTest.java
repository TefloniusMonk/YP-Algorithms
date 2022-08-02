package base;

import hash.Substrings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubstringsTest {

    @Test
    void shouldFindMaxSubstring1(){
        final var str = "abcadcbaf";
        final var expected = 5;
        final var actual = Substrings.maxSubString(str);
        assertEquals(expected,actual);
    }

    @Test
    void shouldFindMaxSubstring2(){
        final var str = "abcabcbb";
        final var expected = 3;
        final var actual = Substrings.maxSubString(str);
        assertEquals(expected,actual);
    }
    @Test
    void shouldFindMaxSubstring3(){
        final var str = "bbbbbbb";
        final var expected = 1;
        final var actual = Substrings.maxSubString(str);
        assertEquals(expected,actual);
    }

    @Test
    void shouldFindMaxSubstring4(){
        final var str = "bbbbbbba";
        final var expected = 2;
        final var actual = Substrings.maxSubString(str);
        assertEquals(expected,actual);
    }

    @Test
    void shouldFindMaxSubstring5(){
        final var str = "bbbbbbbabacbbbbccccasdsdfs";
        final var expected = 4;
        final var actual = Substrings.maxSubString(str);
        assertEquals(expected,actual);
    }
}