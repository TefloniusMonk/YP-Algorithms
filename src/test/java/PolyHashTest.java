import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolyHashTest {
    @Test
    void shouldConvert() {
        var a = 123;
        var m = 100003;
        var hash = "hash";
        var expected = 6080;
        var actual = PolyHash.polyHash(a, m, hash);
        assertEquals(expected, actual);
    }


    @Test
    void shouldConvert2() {
        var a = 123;
        var m = 100003;
        var hash = "a";
        var expected = 97;
        var actual = PolyHash.polyHash(a, m, hash);
        assertEquals(expected, actual);
    }

    @Test
    void shouldConvert1() {
        var a = 123;
        var m = 100003;
        var hash = "HaSH";
        var expected = 56156;
        var actual = PolyHash.polyHash(a, m, hash);
        assertEquals(expected, actual);
    }
}