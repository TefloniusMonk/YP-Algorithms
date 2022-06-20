import java.io.*;

public class PolyHashSubStrings {
    private static long[] hashes;

    public static long polyHash(int a, int mod, String str) {
        hashes = new long[str.length() + 1];
        long hash = 0;
        hashes[0] = 0;
        for (int i = 0; i < str.length(); i++) {
            final char ch = str.charAt(i);
            hash = (hash * a + ch) % mod;
            hashes[i + 1] = hash;
        }
        return (hash % mod);
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(
                                             "")))) {
            final int a = Integer.parseInt(reader.readLine());
            final int m = Integer.parseInt(reader.readLine());
            final String str = reader.readLine();
            polyHash(a, m, str);
            final int size = Integer.parseInt(reader.readLine());
            for (int i = 0; i < size; i++) {
                final var slice = reader.readLine().split(" ");
                final int end = Integer.parseInt(slice[1]);
                final int start = Integer.parseInt(slice[0]);
                if (end == start) {
                    System.out.println(hashes[start]);
                } else {
                    System.out.println(hashes[end] - hashes[start - 1]);
                }
            }
        }
    }
}
