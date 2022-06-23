import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class PolyHashSubStrings {

    private static long[] pows(int a, int mod, String str) {
        final long[] pows = new long[str.length() + 1];
        pows[0] = 1;
        for (int i = 1; i < str.length() + 1; i++) {
            pows[i] = pows[i - 1] * a % mod;
        }
        return pows;
    }

    private static long[] computePrefixes(int a, int mod, String str) {
        final long[] hashes = new long[str.length() + 1];
        hashes[0] = 0;
        for (int i = 1; i <= str.length(); i++) {
            hashes[i] = hashes[i - 1] * a % mod + str.charAt(i - 1) % mod;
        }
        return hashes;
    }

    public static List<String> polyHashSubstrings(int a, int mod, String str, List<Range> ranges) {
        final var pows = pows(a, mod, str);
        final long[] prefixes = computePrefixes(a, mod, str);
        final var result = new ArrayList<String>(ranges.size());
        for (var range : ranges) {
            result.add(String.valueOf(
                    (prefixes[range.end] - prefixes[range.start - 1] * pows[range.end - range.start + 1] % mod + mod) % mod
            ));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        final var result = inputWrapper(System.in);
        System.out.println(result);
    }

    public static String inputWrapper(InputStream inputStream) throws IOException {
        try (final var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            final int a = Integer.parseInt(reader.readLine());
            final int m = Integer.parseInt(reader.readLine());
            final String str = reader.readLine();
            final int size = Integer.parseInt(reader.readLine());
            final var lst = new ArrayList<Range>(size);
            for (int i = 0; i < size; i++) {
                final var slice = reader.readLine().split(" ");
                final int end = Integer.parseInt(slice[1]);
                final int start = Integer.parseInt(slice[0]);
                lst.add(new Range(start, end));
            }
            return String.join("\n", polyHashSubstrings(a, m, str, lst));
        }
    }
}

class Range {
    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public final int start;
    public final int end; //included
}