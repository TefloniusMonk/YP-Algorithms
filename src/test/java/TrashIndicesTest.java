import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrashIndicesTest {
    public static int naive(int[] arr, int k) {
        int[] res = new int[arr.length * arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                res[i] = (abs(arr[i] - arr[j]));
            }
        }
        Arrays.sort(res);
        return res[(k - 1)];
    }


    @Test
    void shouldFind() {
        int[] arr = new int[]{1, 3, 5};
        int k = 3;
        int actual = naive(arr, k);
        int expected = 4;

        assertEquals(expected, actual);
    }

    @Test
    void shouldFind1() {
        int[] arr = new int[]{937, 850, 232, 175, 756, 330, 107, 389};
        int k = 10;
        int actual = naive(arr, k);
        int expected = 181;

        assertEquals(expected, actual);
    }

    @Test
    void shouldFind3() throws IOException {


        try (final BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("/Users/mbelykh/IdeaProjects/YP-Algorithms/src/test/resources/41")))) {
            final int size = Integer.parseInt(reader.readLine());
            final int[] arr = readList(reader, size);
            final int k = Integer.parseInt(reader.readLine());
            final int res = naive(arr, k);
//            final int res = TrashIndices.findKthMin(arr, k);
            assertEquals(res, 50);
        }
    }

    public static int[] readList(BufferedReader reader, int size) throws IOException {
        int[] arr = new int[size];
        int i = 0;
        StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            arr[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        return arr;
    }

    @Test
    void randomTest() throws Exception {
        int[] arr = generateRandom(1, 1000);
        int k = getRandom(arr);
        int expected = naive(arr, k);
        int actual = TrashIndices.findKthMin(arr, k);
        if (expected != actual) {
            Files.writeString(Path.of("/Users/mbelykh/IdeaProjects/YP-Algorithms/src/test/resources/output.txt"),
                    Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
        assertEquals(expected, actual);
    }

    private int getRandom(int[] arr) {
        return ThreadLocalRandom.current().nextInt(1, Math.max(2, (2 * arr.length / 2)));
    }

    private int[] generateRandom(int minSize, int maxSize) {
        int randomNum = ThreadLocalRandom.current().nextInt(minSize, maxSize + 1);
        var arr = new int[randomNum];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(1, 1_000_001);
        }
        return arr;
    }
}