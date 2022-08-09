package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MostCommonWord {

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int strCount = Integer.parseInt(reader.readLine());
            final Map<String, Integer> map = new HashMap<>(strCount);
            int max = 0;
            for (int i = 0; i < strCount; i++) {
                final var line = reader.readLine();
                final var count = map.getOrDefault(line, 0) + 1;
                map.put(line, count);
                max = Math.max(max, count);
            }
            Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.comparingByValue(Comparator.reverseOrder());
            comparator = comparator.thenComparing(Map.Entry.comparingByKey());
            return map.entrySet().stream()
                    .sorted(comparator)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse("");

        }
    }
}
