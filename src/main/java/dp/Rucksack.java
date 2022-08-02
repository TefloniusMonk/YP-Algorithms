package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Rucksack {
    public static void main(String[] args) throws IOException {
        System.out.println(wrap(System.in));
    }

    public static String wrap(InputStream is) throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final String[] firstLine = reader.readLine().split(" ");
            final int itemCount = Integer.parseInt(firstLine[0]);
            final int bagCapacity = Integer.parseInt(firstLine[1]);
            if (bagCapacity == 0) {
                return "0";
            }
            final int[] costs = new int[itemCount];
            final int[] weights = new int[itemCount];
            for (int i = 0; i < itemCount; i++) {
                final String[] line = reader.readLine().split(" ");
                weights[i] = Integer.parseInt(line[0]);
                costs[i] = Integer.parseInt(line[1]);
            }
            final int[][] dp = new int[itemCount + 1][bagCapacity + 1];
            for (int i = 1; i < dp.length; i++) {
                for (int curCapacity = 1; curCapacity < dp[i].length; curCapacity++) {
                    if (curCapacity >= weights[i - 1]) {
                        dp[i][curCapacity] = Math.max(
                                dp[i - 1][curCapacity],
                                costs[i - 1] + dp[i - 1][Math.max(0, curCapacity - weights[i - 1])]
                        );
                    } else {
                        dp[i][curCapacity] = dp[i - 1][curCapacity];
                    }
                }
            }
            final List<String> ans = new ArrayList<>();
            findAns(ans, dp, weights, itemCount, bagCapacity);

            return ans.size() + "\n" + String.join(" ", ans);
        }
    }

    private static void findAns(List<String> ans, int[][] dp, int[] weights, int i, int j) {
        if (dp[i][j] == 0) {
            return;
        }
        if (dp[i - 1][j] == dp[i][j]) {
            findAns(ans, dp, weights, i - 1, j);
        } else {
            findAns(ans, dp, weights, i - 1, j - weights[i - 1]);
            ans.add(0,String.valueOf(i));
        }
    }
}
