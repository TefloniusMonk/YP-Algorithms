import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class MnogoGosha {
    private static final long Q = (long) Math.pow(10, 9) + 7;
    private static final long MOD = (long) Math.pow(2, 32);

    private static long pow(int n) {
        long pow = 1;
        for (int i = 1; i < n; i++) {
            pow = pow * Q % MOD;
        }
        return pow;
    }

    private static long hash(String str) {
        long hash = 0;
        for (int i = 1; i <= str.length(); i++) {
            hash = hash * Q % MOD + str.charAt(i - 1) % MOD;
        }
        return hash;
    }

    public static List<String> firstIdxs(String str, int len, int count) {
        if (len > str.length()) {
            return Collections.emptyList();
        }
        final long pow = pow(len);
        long hash = hash(str.substring(0, len));
        final var map = new LinkedHashMap<Long, Tuple>();
        map.put(hash, new Tuple(0).inc());
        for (int i = 1; i < str.length() - len; i++) {
            final var prevCh = str.charAt(i - 1);
            final var nextCh = str.charAt(i + len - 1);
            hash = (((hash - (prevCh * pow % MOD) + MOD) % MOD) * Q % MOD + nextCh + MOD) % MOD;
            if (map.containsKey(hash)) {
                if (eq(str, len, map.get(hash).firstIdx, i)) {
                    map.put(hash, map.get(hash).inc());
                }
            } else {
                map.put(hash, new Tuple(i).inc());
            }
        }

        return map.values().stream()
                .filter(tuple -> tuple.count() >= count)
                .map(tuple -> String.valueOf(tuple.firstIdx))
                .collect(Collectors.toList());
    }

    private static boolean eq(String str, int len, int firstIdx, int i) {
        int j = 0;
        while (j < len) {
            if (str.charAt(firstIdx) != str.charAt(i)) {
                return false;
            }
            firstIdx++;
            i++;
            j++;
        }
        return true;
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
