package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Jumps {
    private static final int MOD = (int) (Math.pow(10, 9) + 7);

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] line = reader.readLine().split(" ");
            final int step = Integer.parseInt(line[0]);
            final int k = Integer.parseInt(line[1]);

            final int[] dp = new int[step];
            dp[1] = 1;
            for (int i = 2; i < step; i++) {
                dp[i] = (int) (sum(dp, i, k) % MOD);
            }

            return String.valueOf(dp[step - 1]);
        }
    }

    private static long sum(int[] dp, int i, int k) {
        if (k >= i) {
            return new BigInteger("2")
                    .pow(i -1)
                    .remainder(new BigInteger(String.valueOf(MOD)))
                    .longValue();
        }
        long sum = 0;
        for (int j = i - k; j <= i; j++) {
            sum = (sum + dp[j] + MOD) % MOD;
        }
        return sum;
    }
}
