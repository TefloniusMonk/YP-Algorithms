import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MTest {

    @Test
    void shouldSort1(){
        int[] a = {-6, -12, -14, 14};
        Solution.merge_sort(a, 0, a.length);
        int[] expected = {-14, -12, -6, 14};
        assertArrayEquals(expected, a);
    }

    @Test
    void shouldSort2(){
        int[] a = Arrays.stream("-27 78 95 -64 18".split(" ")).mapToInt(Integer::parseInt).toArray();
        Solution.merge_sort(a, 0, a.length);
        int[] expected =Arrays.stream("-64 -27 18 78 95".split(" ")).mapToInt(Integer::parseInt).toArray();
        assertArrayEquals(expected, a);
    }

    @Test
    void shouldSort3(){
        int[] a = Arrays.stream("71 78 -76 16 53 27 55 -96 31 10 47 -5 59 40 93 11 0 -55 -86 -73 -69 -4 -61 -76 -59 31 26 -68 97 -1 52 29 -31 47 -54 -4 -84 -88 48 -85 -53 -85 -58 -25 -30 -81 33 59".split(" ")).mapToInt(Integer::parseInt).toArray();
        Solution.merge_sort(a, 0, a.length);
        int[] expected =Arrays.stream("-96 -88 -86 -85 -85 -84 -81 -76 -76 -73 -69 -68 -61 -59 -58 -55 -54 -53 -31 -30 -25 -5 -4 -4 -1 0 10 11 16 26 27 29 31 31 33 40 47 47 48 52 53 55 59 59 71 78 93 97".split(" ")).mapToInt(Integer::parseInt).toArray();
        assertArrayEquals(expected, a);
    }

    @Test
    void shouldMerge(){
        int[] a = {1, 4, 9, 2, 10, 11};
        int[] actual = Solution.merge(a, 0, 3, 6);
        int[] expected = {1, 2, 4, 9, 10, 11};
        assertArrayEquals(expected, actual);
    }
}