package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EndlessATM2 {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int sum = Integer.parseInt(reader.readLine());
            final int denominationCount = Integer.parseInt(reader.readLine());
            final short[] denominations = new short[denominationCount];
            final StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < denominationCount; i++) {
                denominations[i] = Short.parseShort(tokenizer.nextToken());
            }
            final int[] dp = new int[sum + 1];
            dp[0] = 1;
            for (int denomination : denominations) {
                for (int i = denomination; i <= sum; i++) {
                    dp[i] += dp[i - denomination];
                }
            }
            return String.valueOf(dp[sum]);
        }
    }
}
