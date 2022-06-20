import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BrokenSearchTest {
    @Test
    void shouldFind() {
        final int[] arr = {
                28, 29, 100, 101, 125, 134, 234, 45656, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 21, 22, 23, 25, 26
        };
        final int actual = BrokenSearch.brokenSearch(arr, 5);
        final int expected = 12;
        assertEquals(expected, actual);
    }

    @Test
    void shouldFind1() {
        final int[] arr = {
                19, 21, 100, 101, 1, 4, 5, 7, 12
        };
        final int actual = BrokenSearch.brokenSearch(arr, 5);
        final int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    void shouldFind2() {
        final int[] arr = {
                1, 4, 5, 7, 12, 19, 21, 100, 101
        };
        final int actual = BrokenSearch.brokenSearch(arr, 5);
        final int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void shouldFind3() {
        final int[] arr = {
                5, 7, 12, 19, 21, 100, 101, 1, 4,
        };
        final int actual = BrokenSearch.brokenSearch(arr, 5);
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFind() {
        final int[] arr = {
                5, 7, 12, 19, 21, 100, 101, 1, 4,
        };
        final int actual = BrokenSearch.brokenSearch(arr, 6);
        final int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void shouldFind4() {
        final int[] arr = {
                0, 0, 0, 0, 0, 0, 0, 0, 1
        };
        final int actual = BrokenSearch.brokenSearch(arr, 1);
        final int expected = 8;
        assertEquals(expected, actual);
    }

    @Test
    void shouldFind5() {
        final int[] arr =
                Arrays.stream(("2472 2473 2486 2534 2628 2977 3016 3155 3215 3383 3522 3533 3572 3599 3754 3856 3888 3890 4082 4130" +
                        " 4155 4207 4555 4556 4594 4597 4854 4861 4948 5085 5107 5251 5257 5378 5408 5452 5484 5584 5626 5701 5760 5781 " +
                        "5851 5855 6025 6047 6133 6243 6296 6314 6409 6516 6521 6659 6735 6813 6917 7059 7120 7296 7310 7345 7360 7379 " +
                        "7425 7498 7693 7925 7993 8035 8165 8277 8310 8363 8544 8562 8774 8827 8860 8952 9163 9177 9255 9793 9894 199 213" +
                        " 227 429 465 498 728 939 1502 1744 1768 1821 2317 2428 2471")
                        .split(" ")).mapToInt(Integer::parseInt).toArray();
        final int actual = BrokenSearch.brokenSearch(arr, 227);
        final int expected = 87;
        assertEquals(expected, actual);
    }
}