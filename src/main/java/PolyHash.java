import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PolyHash {
    private static Long lastPow = 1L;
    private static final long MAX_MOD = (long) Math.pow(10, 10);

    private static long pow(int num, int pow) {
        if (pow == 0) return 1;
        long res = (num * lastPow) % MAX_MOD;
        lastPow = res;
        return res;

    }

    public static int polyHash(int a, int mod, String str) {
        lastPow = 1L;
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(str.length() - i - 1);
            long pow = (((long) ch) * pow(a, i)) % MAX_MOD;
            hash = ((hash + pow) % MAX_MOD);
        }
        return (int) (hash % mod);
    }

    public static void main(String[] args) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            String str = reader.readLine();
            System.out.println(polyHash(a, m, str));
        }
    }
}