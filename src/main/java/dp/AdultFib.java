package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AdultFib {
    private static final int MOD = (int) (Math.pow(10, 9) + 7);

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int n = Integer.parseInt(reader.readLine());

            long secondLast = 1;
            long last = 1;
            long num = n - 2;

            while(num >= 0){
                final long next = (last + secondLast) % MOD;
                secondLast = last;
                last = next;
                num--;
            }
            return String.valueOf(last);
        }
    }

}
