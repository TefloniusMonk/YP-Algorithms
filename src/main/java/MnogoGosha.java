import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class MnogoGosha {
    private static final long Q1 = 31;
    private static final long Q2 = 37;
    private static final long MOD = (long) Math.pow(10, 9) + 7;

    private static long pow(long q, int n) {
        long pow = 1;
        for (int i = 1; i < n; i++) {
            pow = pow * q % MOD;
        }
        return pow;
    }

    private static long hash(long q, String str) {
        long hash = 0;
        for (int i = 1; i <= str.length(); i++) {
            hash = hash * q % MOD + str.charAt(i - 1) % MOD;
        }
        return hash;
    }

    public static List<String> firstIdxs(String str, int len, int count) {
        if (len > str.length()) {
            return Collections.emptyList();
        }

        final var substr = str.substring(0, len);
        // Use two hashes to prevent collisions
        final long pow1 = pow(Q1, len);
        long hash1 = hash(Q1, substr);
        final long pow2 = pow(Q2, len);
        long hash2 = hash(Q2, substr);

        final var map = new LinkedHashMap<Long, Tuple>();
        map.put(fullHash(hash1, hash2), new Tuple(0).inc());
        for (int i = 1; i < str.length() - len; i++) {
            final var prevCh = str.charAt(i - 1);
            final var nextCh = str.charAt(i + len - 1);

            hash1 = (((hash1 - (prevCh * pow1 % MOD) + MOD) % MOD) * Q1 % MOD + nextCh + MOD) % MOD;
            hash2 = (((hash2 - (prevCh * pow2 % MOD) + MOD) % MOD) * Q2 % MOD + nextCh + MOD) % MOD;

            map.put(fullHash(hash1, hash2), map.getOrDefault(fullHash(hash1, hash2), new Tuple(i)).inc());
        }

        return map.values().stream()
                .filter(tuple -> tuple.count() >= count)
                .map(tuple -> String.valueOf(tuple.firstIdx))
                .collect(Collectors.toList());
    }

    private static long fullHash(long hash1, long hash2) {
        return (hash1 + hash2) % MOD;
    }

    public static void main(String[] args) throws IOException {
        final var result = inputWrapper(System.in);
        System.out.println(result);
    }

    public static String inputWrapper(InputStream inputStream) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final var firstLine = reader.readLine().split(" ");
            final int len = Integer.parseInt(firstLine[0]);
            final int count = Integer.parseInt(firstLine[1]);
            final String str = reader.readLine();

            return String.join(" ", firstIdxs(str, len, count));
        }
    }

    private static class Tuple {
        public final int firstIdx;
        private int count;

        public Tuple(int idx) {
            this.firstIdx = idx;
            this.count = 0;
        }

        public Tuple inc() {
            count++;
            return this;
        }

        public int count() {
            return this.count;
        }
    }
}
