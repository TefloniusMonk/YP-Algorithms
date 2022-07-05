import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    @Test
    void shouldSiftDown() {
        final var arr = new int[]{-1,13, 12, 1, 8, 3, 4, 7};
        final var expectedPos = 1;
        final var expected = new int[]{-1,13,  12, 4, 8, 3, 1, 7};
        final var actual = Heap.siftDown(arr, 1);
        assertEquals(expectedPos, actual);
    }

    @Test
    void shouldSiftUp() {
        final var arr = new int[]{-1,0, 12, 1, 8, 3, 4, 7};
        final var expectedPos = 1;
        final var expected = new int[]{-1,0,  12, 4, 8, 3, 1, 7};
        final var actual = Heap.siftUp(arr, 2);
        assertEquals(expectedPos, actual);
    }
}