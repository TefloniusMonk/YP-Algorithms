package dp;

import jdk.jshell.spi.SPIResolutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Restaurant {
    private static final int INF = 10000000;
    private static final int MIN_SUM = 501;

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int days = Integer.parseInt(reader.readLine());
            final short[] prices = new short[days + 1];
            for (int i = 1; i <= days; i++) {
                prices[i] = Short.parseShort(reader.readLine());
            }
            final int[][] dp = new int[days + 1][days + 2];
            Arrays.fill(dp[0], INF);
            dp[0][0] = 0;
            for (int i = 1; i <= days; i++) {
                for (int j = 1; j <= days; j++) {
                    if (prices[i] < MIN_SUM) {
                        dp[i][j] = Math.min(dp[i - 1][j] + prices[i], dp[i - 1][j + 1]);
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1] + prices[i], dp[i - 1][j + 1]);
                    }
                }
            }
            return "";
        }
    }
}
