package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int len = Integer.parseInt(reader.readLine());
            final int[] seq = readArr(reader, len);

            final int[][] dp = new int[seq.length + 1][seq.length + 1];
            dp[0][0] = 1;
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    if (seq[i - 1] < seq[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
            final StringBuilder builder1 = new StringBuilder();
            int i = len, j = len;
            while (dp[i][j] != 0) {
                if (seq[i - 1] == seq[j - 1]) {
                    builder1.insert(0, i  + " ");
                    i--;
                    j--;
                    continue;
                }
                if (i > 0 && dp[i][j] == dp[i - 1][j]) {
                    i--;
                    continue;
                }
                j--;
            }
            return dp[len][len] + "\n" + builder1;
        }
    }

    private static int[] readArr(BufferedReader reader, int len) throws IOException {
        final int[] seq = new int[len];
        final StringTokenizer tokenizer1 = new StringTokenizer(reader.readLine());
        for (int i = 0; i < len; i++) {
            seq[i] = Integer.parseInt(tokenizer1.nextToken());
        }
        return seq;
    }
}
