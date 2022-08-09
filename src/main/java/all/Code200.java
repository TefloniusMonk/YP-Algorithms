package all;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


public class Code200 {

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int n = readInt(reader);
            final var tokenizer = new StringTokenizer(reader.readLine());

            final var map = new HashMap<Integer, Integer>();
            while (tokenizer.hasMoreTokens()) {
                final var token = Integer.parseInt(tokenizer.nextToken());
                if (token == 0) {
                    throw new OutOfMemoryError();
                }
                if (token < 0) {
                    throw new RuntimeException();
                }
                final var left = token % 200;
                map.put(left, map.getOrDefault(left, 0) + 1);
            }
            int sum = 0;

            for (var entry : map.entrySet()) {
                sum += count(entry.getValue());
            }
            return String.valueOf(sum);
        }
    }

    private static long count(int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return (long) n * (n - 1) / 2;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}

