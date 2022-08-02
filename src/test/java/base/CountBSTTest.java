package base;

import org.junit.jupiter.api.Test;
import tree.CountBST;

import static org.junit.jupiter.api.Assertions.*;

class CountBSTTest {
    @Test
    void shouldCountBST1() {
        final var expected = 14;
        final var actual = CountBST.countBST(4);
        assertEquals(expected, actual);
    }

    @Test
    void shouldCountBST2() {
        final var expected = 9694845;
        final var actual = CountBST.countBST(15);
        assertEquals(expected, actual);
    }
}