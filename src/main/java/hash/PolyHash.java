package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PolyHash {
    public static long polyHash(int a, int mod, String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            final char ch = str.charAt(i);
            hash = (hash * a + ch) % mod;
        }
        return (hash % mod);
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int a = Integer.parseInt(reader.readLine());
            final int m = Integer.parseInt(reader.readLine());
            final String str = reader.readLine();
            System.out.println(polyHash(a, m, str));
        }
    }
}