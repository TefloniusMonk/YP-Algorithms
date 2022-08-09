package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Prefix {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final var str = reader.readLine();
            final var prefix = computePrefix(str);
            return Arrays.stream(prefix).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        }
    }

    private static int[] computePrefix(String str) {
        final int[] prefix = new int[str.length()];
        for (int i = 1; i < prefix.length; i++) {
            int k = prefix[i - 1];
            while (k > 0 && str.charAt(k) != str.charAt(i)) {
                k = prefix[k - 1];
            }
            if (str.charAt(k) == str.charAt(i)) {
                k += 1;
            }
            prefix[i] = k;
        }
        return prefix;
    }
}
