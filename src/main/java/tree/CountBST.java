package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CountBST {
    public static final long[] catalans = new long[]{
            1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700, 1767263190
    };

    public static long countBST(int n) {
        return catalans[n];
    }


    public static void main(String[] args) throws IOException {
        final var result = inputWrapper(System.in);
        System.out.println(result);
    }

    public static Long inputWrapper(InputStream inputStream) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final var n = Integer.parseInt(reader.readLine());
            return countBST(n);
        }
    }
}
