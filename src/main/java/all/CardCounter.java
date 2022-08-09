package all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CardCounter {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int n = readInt(reader);
            final int k = readInt(reader);
            final int[] arr = splitLine(reader, n);
            final long[] dpLeft = new long[k + 1];
            for (int i = 1; i <= k; i++) {
                dpLeft[i] = dpLeft[i - 1] + arr[i - 1];
            }
            final long[] dpRight = new long[k + 1];
            for (int i = 1; i <= k; i++) {
                dpRight[i] = dpRight[i - 1] + arr[n - i];
            }
            long max = 0;
            for (int i = 0; i <= k; i++) {
                max = Math.max(max, dpLeft[i] + dpRight[k - i]);
            }
            return String.valueOf(max);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] splitLine(BufferedReader reader, int size) throws IOException {
        final var tokenizer = new StringTokenizer(reader.readLine());
        final int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return arr;
    }
}
