package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Repeat {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final var str = reader.readLine();
            final var dividers = findDividers(str.length());
            int mult = str.length();
            for (var divider : dividers) {
                if (str.substring(0, divider)
                        .repeat(str.length() / divider)
                        .equals(str)) {
                    mult = divider;
                    break;
                }
            }
            return String.valueOf(str.length() / mult);
        }
    }

    private static List<Integer> findDividers(int length) {
        final var result = new ArrayList<Integer>();
        for (int i = 1; i <= length / 2; i++) {
            if (length % i == 0) {
                result.add(i);
            }
        }
        result.add(length);
        return result;
    }
}
