package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LCS {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final int len1 = Integer.parseInt(reader.readLine());
            final int[] seq1 = readArr(reader, len1);
            final int len2 = Integer.parseInt(reader.readLine());
            final int[] seq2 = readArr(reader, len2);

            final int[][] dp = new int[seq1.length + 1][seq2.length + 1];
            Arrays.fill(dp[0], 0); // first row
            fillColumn(dp, 0, 0); // first column
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    if (seq1[i - 1] == seq2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
            final StringBuilder builder1 = new StringBuilder();
            final StringBuilder builder2 = new StringBuilder();
            int i = len1, j = len2;
            while (dp[i][j] != 0) {
                if (seq1[i - 1] == seq2[j - 1]) {
                    builder1.insert(0, i  + " ");
                    builder2.insert(0, j  + " ");
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
            return dp[len1][len2] + "\n" + builder1 + "\n" + builder2;
        }
    }

    private static void fillColumn(int[][] dp, int column, int value) {
        for (int i = 0; i < dp.length; i++) {
            dp[i][column] = value;
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
