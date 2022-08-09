package str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Replace {
    private static final String SENTINEL = "#";

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final var str = reader.readLine();
            final var pattern = reader.readLine();
            final var replace = reader.readLine();
            return replace(str, pattern, replace);
        }
    }

    private static String replace(String str, String pattern, String replace) {
        final var idxs = find(str, pattern);
        final var builder = new StringBuilder();
        int i = 0;
        final var replaceIdxIter = idxs.iterator();
        int nextReplaceIdx = next(str, replaceIdxIter);
        while (i < str.length()) {
            if (i == nextReplaceIdx){
                builder.append(replace);
                i += pattern.length();
                nextReplaceIdx = next(str, replaceIdxIter);
            }
            while (i < str.length() && i != nextReplaceIdx){
                builder.append(str.charAt(i++));
            }
        }
        return builder.toString();
    }

    private static int next(String str, Iterator<Integer> replaceIdxIter) {
        return replaceIdxIter.hasNext() ? replaceIdxIter.next() : str.length();
    }

    private static List<Integer> find(String str, String pattern) {
        final var idxs = new LinkedList<Integer>();
        final var prefix = computePrefix(pattern + SENTINEL + str);
        for (int i = 1; i < prefix.length; i++) {
            if (prefix[i] == pattern.length()) {
                idxs.add(i - 2 * pattern.length()); // x2 because we need start of pattern and second because we computed prefix for concated string
            }
        }
        return idxs;
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
