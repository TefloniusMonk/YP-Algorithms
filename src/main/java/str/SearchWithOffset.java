package str;

import java.io.*;
import java.util.StringTokenizer;

public class SearchWithOffset {
    private static final int SENTINEL = 10000000;

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int measuresCount = Integer.parseInt(reader.readLine());
            final int[] measures = readArr(reader, measuresCount);
            final int patternLen = Integer.parseInt(reader.readLine());
            final int[] pattern = readArr(reader, patternLen);

            final var measureDiffs = getDiffs(measures);
            final var patternDiffs = getDiffs(pattern);
            final int[] concat = concat(measureDiffs, patternDiffs);
            final var prefix = computePrefix(concat);
            final var resultBuilder = new StringBuilder();
            for (int i = 0; i < prefix.length; i++) {
                if (prefix[i] == patternDiffs.length) {
                    resultBuilder
                            .append(i - 2 * patternDiffs.length + 1) // +1 because 1-indexed arr,
                            // minus pattern.length because prefix computed for concated arr
                            // and another minus pattern.length for first index
                            // => (-2 * pattern.length) + 1
                            .append(" ");
                }
            }
            return resultBuilder.toString();
        }
    }

    private static int[] concat(int[] measureDiffs, int[] patternDiffs) {
        final var concat = new int[measureDiffs.length + patternDiffs.length + 1];
        int j = 0;
        for (int i = 0; i < patternDiffs.length; i++) {
            concat[j++] = patternDiffs[i];
        }
        concat[j++] = SENTINEL;
        for (int i = 0; i < measureDiffs.length; i++) {
            concat[j++] = measureDiffs[i];
        }
        return concat;
    }

    //3 9 1 2 5 10 9 1 7 -> -6 8 -1 -3 -5 1 8 -6
    //4 10               -> -6
    // -6 # -6 8 -1 -3 -5 1 8 -6
    // 0 0 1 0 0 0 0 0 0 1
    private static int[] computePrefix(int[] arr) {
        final int[] prefix = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            int k = prefix[i - 1];
            while (k > 0 && arr[i] != arr[k]) {
                k = prefix[k - 1];
            }
            if (arr[i] == arr[k]) {
                k++;
            }
            prefix[i] = k;
        }
        return prefix;
    }

    private static int[] getDiffs(int[] arr) {
        final int[] diffs = new int[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            diffs[i] = (arr[i] - arr[i + 1]);
        }
        return diffs;
    }

    private static int[] readArr(BufferedReader reader, int arrLen) throws IOException {
        final var tokenizer = new StringTokenizer(reader.readLine());
        final int[] arr = new int[arrLen];
        int i = 0;
        while (tokenizer.hasMoreElements()) {
            arr[i++] = Integer.parseInt(tokenizer.nextToken());
        }
        return arr;
    }
}
