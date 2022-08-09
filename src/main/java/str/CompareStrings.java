package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CompareStrings {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final var s1 = reader.readLine();
            final var s2 = reader.readLine();
            final var filtered1 = filter(s1);
            final var filtered2 = filter(s2);
            return String.valueOf(
                    filtered1.equals(filtered2) ? 0 : filtered1.compareTo(filtered2) < 0 ? -1 : 1);
        }
    }

    private static String filter(String s) {
        final var builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) % 2 == 0) {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
