package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {
    private static final int INF = 10000;
    private static final int MIN_SUM = 501;

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int days = Integer.parseInt(reader.readLine());
            final int[] prices = new int[days + 1];
            for (int i = 1; i <= days; i++) {
                prices[i] = Integer.parseInt(reader.readLine());
            }
            final long[][] dp = new long[days + 2][days + 1]; //dp[i][j] -> i - num of coupons, j - days
            for (int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], INF);
            }
            dp[0][0] = 0;
            for (int j = 1; j <= days; j++) {
                for (int i = 0; i <= days; i++) {
                    if (prices[j] >= MIN_SUM) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                        if (i != 0) {
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + prices[j]);
                        }
                    } else {
                        dp[i][j] = Math.min(dp[i][j - 1] + prices[j], dp[i + 1][j - 1]);
                    }
                }
            }

            final int minIdx = min(dp, days);
            final long min = dp[minIdx][days];
            final var coupons = countCoupons(prices, dp, minIdx, days);
            return String.valueOf(String.format("%s %s\n%s", min, coupons.size(), coupons.stream().map(String::valueOf).collect(Collectors.joining(" "))));
        }
    }

    private static List<Integer> countCoupons(int[] prices, long[][] dp, int i, int j) {
        final var lst = new LinkedList<Integer>();
        while (j != 0) {
            if (dp[i][j] == dp[i + 1][j - 1]) {
                lst.add(j);
                i++;
            } else {
                if (prices[j] >= MIN_SUM) {
                    i--;
                }
            }
            j--;
        }
        Collections.reverse(lst);
        return lst;
    }

    private static int min(long[][] dp, int column) {
        long min = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i][column] < min) {
                min = dp[i][column];
                minIdx = i;
            }
        }
        return minIdx;
    }

    private static void repeat(int nTimes, Runnable fn) {
        for (int i = 0; i < nTimes; i++) {
            fn.run();
        }
    }
}
