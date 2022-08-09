package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class StringInsert {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String str = reader.readLine();
            final int strCount = Integer.parseInt(reader.readLine());
            final var lst = new ArrayList<Pair>(strCount);
            for (int i = 0; i < strCount; i++) {
                final var line = reader.readLine().split(" ");
                lst.add(new Pair(line[0], Integer.parseInt(line[1])));
            }
            lst.sort(Comparator.comparing(it -> it.idx));
            final var builder = new StringBuilder();
            final var lstIter = lst.iterator();
            var nextPair = lstIter.hasNext() ? lstIter.next() : null;
            int i = 0;
            while (i <= str.length()) {
                if (nextPair != null && i == nextPair.idx) {
                    builder.append(nextPair.str);
                    nextPair = lstIter.hasNext() ? lstIter.next() : null;
                    continue;
                }
                if (i == str.length()){
                    break;
                }
                final int nextIdx = nextPair == null ? str.length() : nextPair.idx;
                while (i < nextIdx) {
                    builder.append(str.charAt(i++));
                }
            }
            return builder.toString();
        }
    }

    private static class Pair {
        public final String str;
        public final int idx;

        private Pair(String str, int idx) {
            this.str = str;
            this.idx = idx;
        }
    }
}
