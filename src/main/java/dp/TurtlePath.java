package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TurtlePath {
    private static final short ONE = 1;
    private static final short ZERO = 0;

    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int rows = Integer.parseInt(firstLine[0]);
            final int cols = Integer.parseInt(firstLine[1]);
            final short[][] field = new short[rows][cols];
            for (int i = 0; i < rows; i++) {
                final var line = reader.readLine();
                for (int j = 0; j < cols; j++) {
                    field[i][j] = line.charAt(j) == '0' ? ZERO : ONE;
                }
            }
            final int[][] dp = new int[rows][cols];
            dp[rows - 1][0] = field[rows - 1][0];
            initLastRow(rows, cols, field, dp);
            initFirstColumn(rows, field, dp);

            for (int row = rows - 2; row >= 0; row--) {
                for (int col = 1; col < cols; col++) {
                    dp[row][col] = field[row][col] + Math.max(dp[row + 1][col], dp[row][col - 1]);
                }
            }
            final var builder = new StringBuilder();
            int col = cols-1;
            int row = 0;
            int count = field[rows-1][0];
            while (!(col == 0 && row == rows-1)) {
                count += field[row][col];
                final int below = getBelow(dp, col, row);
                final int left = getLeft(dp, col, row);
                if (left >=below) {
                    builder.insert(0, 'R');
                    col--;
                } else {
                    builder.insert(0, 'U');
                    row++;
                }
            }
            return builder.insert(0, count + "\n").toString();
        }
    }

    private static int getLeft(int[][] dp, int col, int row) {
        if (col <= 0) {
            return -1;
        }
        return dp[row][col - 1];
    }

    private static int getBelow(int[][] dp, int col, int row) {
        if (row >= dp.length - 1) {
            return -1;
        }
        return dp[row+1][col];
    }

    private static void initFirstColumn(int rows, short[][] field, int[][] dp) {
        for (int i = rows - 2; i >= 0; i--) {
            dp[i][0] = field[i][0] + dp[i + 1][0];
        }
    }

    private static void initLastRow(int rows, int cols, short[][] field, int[][] dp) {
        for (int i = 1; i < cols; i++) {
            dp[rows - 1][i] = field[rows - 1][i] + dp[rows - 1][i - 1];
        }
    }
}
