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



    @Test
    void shouldFind() {
        final int[] arr = new int[]{1, 3, 5 , 4};
        final int k = 3;
        final int actual = TrashIndicesQueue.findKthMin(arr, k);
        final int expected = 4;

        assertEquals(expected, actual);
    }

    @Test
    void shouldFind1() {
        final int[] arr = new int[]{937, 850, 232, 175, 756, 330, 107, 389};
        final int k = 10;
        final int actual = TrashIndicesQueue.findKthMin(arr, k);
        final int expected = 181;

        assertEquals(expected, actual);
    }

    @Test
    void shouldFind3() throws IOException {


        try (final BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("")))) {
            final int size = Integer.parseInt(reader.readLine());
            final int[] arr = readList(reader, size);
            final int k = Integer.parseInt(reader.readLine());
//            final int res = TrashIndicesQueue.findKthMin(arr, k);
            final int res = TrashIndices.findKthMin(arr, k);
            assertEquals(50, res);
        }
    }

    public static int[] readList(BufferedReader reader, int size) throws IOException {
        final int[] arr = new int[size];
        int i = 0;
        final StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            arr[i] = Integer.parseInt(st.nextToken());
            i++;
        }
        return arr;
    }

    @Test
    void randomTest() throws Exception {
        final int[] arr = generateRandom(1, 100_00);
        final int k = getRandom(arr);
        final int expected = TrashIndicesQueue.findKthMin(arr, k);
        final int actual = TrashIndices.findKthMin(arr, k);
        if (expected != actual) {
            Files.writeString(Path.of(""),
                    Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
        assertEquals(expected, actual);
    }

    private int getRandom(int[] arr) {
        return ThreadLocalRandom.current().nextInt(1, Math.max(2, (2 * arr.length / 2)));
    }

    private int[] generateRandom(int minSize, int maxSize) {
        final int randomNum = ThreadLocalRandom.current().nextInt(minSize, maxSize + 1);
        final var arr = new int[randomNum];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(1, 1_000_000);
        }
        return arr;
    }
}