package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Adventure {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int len = Integer.parseInt(reader.readLine());
            final int[] rating = new int[len];
            final StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < len; i++) {
                rating[i] = Integer.parseInt(tokenizer.nextToken());
            }
            final int[] sortedRating = Arrays.stream(Arrays.copyOf(rating, rating.length)).sorted().distinct().toArray();
            final int sortedLen = sortedRating.length;
            final int[][] dp = new int[len + 1][sortedLen + 1];
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j <= sortedLen; j++) {
                    if (rating[i - 1] == sortedRating[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            final StringBuilder builder = new StringBuilder();
            int i = len, j = sortedLen;
            while (dp[i][j] != 0) {
                if (rating[i - 1] == sortedRating[j - 1]) {
                    builder.insert(0, i + " ");
                    i--;
                    j--;
                    continue;
                }
                if (dp[i][j] == dp[i][j- 1]) {
                    j--;
                    continue;
                }
                i--;
            }
            return dp[len][sortedLen] + "\n" + builder;
        }
    }
}
/*
3 6 10 1 9 3 10 -> 3 6 9 10



       -1 | 3 | 6 | 10 | 1 | 9 | 3 | 10
-1  |   1 | 0 | 0 | 0  | 0 | 0 | 0 | 0
3   |   0 | 1 | 2 | 3  | 3 | 3 | 3 | 3
6   |   0 | 0 | 1 | 2  | 2 | 2 | 2 | 2
10  |   0 | 0 | 0 |
1   |   0
9   |   0
3   |   0
10  |   0



 */
