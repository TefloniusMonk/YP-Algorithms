package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EndlessATM {
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
            Arrays.sort(denominations);
            final Integer[] dp = new Integer[sum + 1];
            Arrays.fill(dp, -1);
            for (short denomination : denominations) {
                int multiplier = 1;
                int result = multiplier * denomination;
                while (result <= sum) {
                    dp[result] = multiplier;
                    result = ++multiplier * denomination;
                }
            }
            for (int i = denominations[0]; i < dp.length; i++) {
                if (dp[i] == -1){
                    continue;
                }
                for (int j = i + 1; j < dp.length; j++) {
                    if (i + j > sum) {
                        break;
                    }
                    if (dp[j] == -1) {
                        continue;
                    }
                    if (dp[i + j] == -1) {
                        dp[i + j] = dp[i] + dp[j];
                    } else {
                        dp[i + j] = Math.min(dp[i + j], dp[i] + dp[j]);
                    }
                }
            }
            return String.valueOf(dp[sum] == null ? -1 : dp[sum]);
        }
    }
}
