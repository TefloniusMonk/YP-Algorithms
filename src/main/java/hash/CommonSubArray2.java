package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CommonSubArray2 {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream inputStream) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final var size1 = Integer.parseInt(reader.readLine());
            final var arr1 = readList(reader, size1);
            final var size2 = Integer.parseInt(reader.readLine());
            final var arr2 = readList(reader, size2);
            if (arr1.length > arr2.length) {
                return String.valueOf(find(arr2, arr1));
            } else {
                return String.valueOf(find(arr1, arr2));
            }
        }
    }

    private static String find(int[] a, int[] b) {
        int max = 0;
        int counter = 0;
        final Map<Integer, Integer> seconds = new HashMap<>();
        for (int k = 0; k < b.length; k++) {
            seconds.putIfAbsent(b[k], k);
        }
        for (int i = 0; i < a.length; i++) {
            final int start = i;
            int j = seconds.get(a[i]) == null ? b.length + 1 : seconds.get(a[i]);
            while (i < a.length && j < b.length) {
                counter++;
                if (a[i] == b[j]) {
                    max = Math.max(max, i - start + 1);
                    i++;
                    j++;
                } else {
                    i = start;
                    j++;
                }
            }
        }
        System.out.println(counter);

        return String.valueOf(max);
    }

    private static int[] readList(BufferedReader reader, int size) throws IOException {
        final var lst = new int[size];
        final var tokenizer = new StringTokenizer(reader.readLine());
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            final var token = tokenizer.nextToken();
            lst[i++] = Integer.parseInt(token);
        }
        return lst;
    }
}
